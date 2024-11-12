package com.enifl33fi.lab1.api.service;

import com.enifl33fi.lab1.api.dto.request.LoginRequestDto;
import com.enifl33fi.lab1.api.dto.request.RegisterRequestDto;
import com.enifl33fi.lab1.api.dto.response.AuthenticationResponse;
import com.enifl33fi.lab1.api.exception.RefreshTokenException;
import com.enifl33fi.lab1.api.exception.UsernameNotUniqueException;
import com.enifl33fi.lab1.api.mapper.UserMapper;
import com.enifl33fi.lab1.api.model.security.RefreshToken;
import com.enifl33fi.lab1.api.model.user.AdminRegistrationRequest;
import com.enifl33fi.lab1.api.model.user.Role;
import com.enifl33fi.lab1.api.model.user.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService {

  private final UserService userService;
  private final UserMapper userMapper;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final RefreshTokenService refreshTokenService;
  private final ValidatingService validatingService;

  public Optional<AuthenticationResponse> register(RegisterRequestDto userDto) {
    validatingService.validateEntity(userDto);
    if (!userService.isUsernameUnique(userDto.getUsername())) {
      throw new UsernameNotUniqueException(userDto.getUsername());
    }

    if (userDto.getRole() == Role.USER || userService.getAdmins().isEmpty()) {
      User user = userMapper.mapUserFromRegistrationDto(userDto);
      userService.saveUser(user);
      return Optional.of(getResponseByUser(user));
    }
    userService.saveRequest(userMapper.mapRegisterDtoToAdminRegistrationRequest(userDto));
    return Optional.empty();
  }

  public AuthenticationResponse login(LoginRequestDto userDto) {
    validatingService.validateEntity(userDto);

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
    User user = (User) authentication.getPrincipal();
    return getResponseByUser(user);
  }

  @Transactional
  public void approveRequest(String username) {
    AdminRegistrationRequest request = userService.getRequest(username);
    userService.deleteRequest(request);

    userService.saveUser(
        User.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .role(Role.ADMIN)
            .build());
  }

  @Transactional
  public void rejectRequest(String username) {
    AdminRegistrationRequest request = userService.getRequest(username);
    userService.deleteRequest(request);
  }

  public AuthenticationResponse getTokens(String refreshToken) {
    if (jwtService.validateRefreshToken(refreshToken)) {
      RefreshToken token = refreshTokenService.getByToken(refreshToken);
      User user = token.getUser();
      return getResponseByUser(user);
    }
    refreshTokenService.deleteByToken(refreshToken);
    throw new RefreshTokenException("Invalid token");
  }

  public Boolean isUserUnique(String username) {
    return userService.isUsernameUnique(username);
  }

  private AuthenticationResponse getResponseByUser(User user) {
    String accessToken = jwtService.generateAccessToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);
    refreshTokenService.saveToken(refreshToken, user);
    return AuthenticationResponse.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }
}

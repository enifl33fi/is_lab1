package com.enifl33fi.lab1.api.mapper;

import com.enifl33fi.lab1.api.dto.request.RegisterRequestDto;
import com.enifl33fi.lab1.api.dto.response.AdminRegistrationRequestResponseDto;
import com.enifl33fi.lab1.api.model.user.AdminRegistrationRequest;
import com.enifl33fi.lab1.api.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

  private final PasswordEncoder passwordEncoder;

  public User mapUserFromRegistrationDto(RegisterRequestDto userDto) {
    return User.builder()
        .username(userDto.getUsername())
        .password(passwordEncoder.encode(userDto.getPassword()))
        .role(userDto.getRole())
        .build();
  }

  public AdminRegistrationRequest mapRegisterDtoToAdminRegistrationRequest(
      RegisterRequestDto userDto) {
    return AdminRegistrationRequest.builder()
        .username(userDto.getUsername())
        .password(passwordEncoder.encode(userDto.getPassword()))
        .build();
  }

  public AdminRegistrationRequestResponseDto
      mapAdminRegistrationRequestToAdminRegistrationResponseDto(
          AdminRegistrationRequest adminRegistrationRequest) {
    return AdminRegistrationRequestResponseDto.builder()
        .username(adminRegistrationRequest.getUsername())
        .build();
  }
}

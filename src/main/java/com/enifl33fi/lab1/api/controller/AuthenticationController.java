package com.enifl33fi.lab1.api.controller;

import com.enifl33fi.lab1.api.dto.request.LoginRequestDto;
import com.enifl33fi.lab1.api.dto.request.RefreshJwtRequestDto;
import com.enifl33fi.lab1.api.dto.request.RegisterRequestDto;
import com.enifl33fi.lab1.api.dto.response.AuthenticationResponseDto;
import com.enifl33fi.lab1.api.service.AuthenticationService;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  @ResponseBody
  public ResponseEntity<?> register(@RequestBody RegisterRequestDto userDto) {
    Optional<AuthenticationResponseDto> resOpt = authenticationService.register(userDto);

    if (resOpt.isPresent()) {
      return ResponseEntity.ok(resOpt.get());
    } else {
      return ResponseEntity.ok().build();
    }
  }

  @PostMapping("/login")
  @ResponseBody
  public ResponseEntity<AuthenticationResponseDto> login(@RequestBody LoginRequestDto userDto) {
    return ResponseEntity.ok(authenticationService.login(userDto));
  }

  @PostMapping("/refresh")
  @ResponseBody
  public ResponseEntity<AuthenticationResponseDto> getTokens(
      @RequestBody RefreshJwtRequestDto request) {
    return ResponseEntity.ok(authenticationService.getTokens(request.getRefreshToken()));
  }

  @GetMapping("/unique")
  @ResponseBody
  public ResponseEntity<Map<String, Boolean>> isUserUnique(
      @RequestParam("username") String username) {
    Boolean isUnique = authenticationService.isUserUnique(username);
    return ResponseEntity.ok(Collections.singletonMap("unique", isUnique));
  }
}

package com.enifl33fi.lab1.api.controller;

import com.enifl33fi.lab1.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
  private final AuthenticationService authenticationService;

  @GetMapping("/requests")
  @ResponseBody
  public ResponseEntity<?> getAllRequests() {
    return ResponseEntity.ok(authenticationService.getRequests());
  }

  @PostMapping("/request/{username}/approve")
  @ResponseBody
  public ResponseEntity<?> approveRequest(@PathVariable String username) {
    authenticationService.approveRequest(username);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/request/{username}/reject")
  @ResponseBody
  public ResponseEntity<?> rejectRequest(@PathVariable String username) {
    authenticationService.rejectRequest(username);
    return ResponseEntity.ok().build();
  }
}

package com.enifl33fi.lab1.api.service;

import com.enifl33fi.lab1.api.model.user.AdminRegistrationRequest;
import com.enifl33fi.lab1.api.model.user.Role;
import com.enifl33fi.lab1.api.model.user.User;
import com.enifl33fi.lab1.api.repository.AdminRegistrationRequestRepository;
import com.enifl33fi.lab1.api.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final EventPublisher eventPublisher;
  private final AdminRegistrationRequestRepository adminRegistrationRequestRepository;

  @Override
  public User loadUserByUsername(String username) {
    return userRepository
        .findById(username)
        .orElseThrow(() -> new UsernameNotFoundException("User with provided username not found."));
  }

  public AdminRegistrationRequest getRequest(String username) {
    return adminRegistrationRequestRepository
        .findById(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("Request with provided username not found."));
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }

  public void saveRequest(AdminRegistrationRequest request) {
    adminRegistrationRequestRepository.save(request);
    eventPublisher.publishAdminRequestChangedEvent();
  }

  public void deleteRequest(AdminRegistrationRequest request) {
    adminRegistrationRequestRepository.delete(request);
    eventPublisher.publishAdminRequestChangedEvent();
  }

  public List<AdminRegistrationRequest> getRequests() {
    return adminRegistrationRequestRepository.findAll();
  }

  public boolean isUsernameUnique(String username) {
    Optional<User> userFromDB = userRepository.findById(username);
    Optional<AdminRegistrationRequest> requestFromDB =
        adminRegistrationRequestRepository.findById(username);
    return userFromDB.isEmpty() && requestFromDB.isEmpty();
  }

  public List<User> getAdmins() {
    return userRepository.findAllByRole(Role.ADMIN);
  }
}

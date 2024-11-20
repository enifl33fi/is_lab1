package com.enifl33fi.lab1.api.service;

import com.enifl33fi.lab1.api.model.user.User;
import com.enifl33fi.lab1.api.model.utils.OwnedEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccessService {
  public boolean checkAccess(OwnedEntity entity) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();

    if (principal instanceof User) {
      return entity.getUser().getUsername().equals(((User) principal).getUsername())
          || (authentication.getAuthorities().stream()
                  .anyMatch(a -> a.getAuthority().equals("ADMIN"))
              && entity.isAdminPermission());
    }

    return false;
  }
}

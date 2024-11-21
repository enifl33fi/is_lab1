package com.enifl33fi.lab1.api.model.utils;

import com.enifl33fi.lab1.api.model.user.User;
import com.enifl33fi.lab1.api.service.AccessService;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnedEntityHandler {
  private final AccessService accessService;

  public void setOwner(OwnedEntity entity) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    entity.setUser((User) authentication.getPrincipal());
    entity.setCreationDate(new Date());
  }

  public void validateAccess(OwnedEntity entity) {
    if (!accessService.checkAccess(entity)) {
      throw new AccessDeniedException("Permission denied");
    }
  }
}

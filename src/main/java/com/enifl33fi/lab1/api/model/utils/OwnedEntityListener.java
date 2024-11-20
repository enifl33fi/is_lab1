package com.enifl33fi.lab1.api.model.utils;

import com.enifl33fi.lab1.api.model.user.User;
import com.enifl33fi.lab1.api.service.AccessService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnedEntityListener {
  private final AccessService accessService;

  @PrePersist
  public void prePersist(Object obj) {
    if (obj instanceof OwnedEntity entity) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      entity.setUser((User) authentication.getPrincipal());
      entity.setCreationDate(new Date());
    }
  }

  @PreUpdate
  public void preUpdate(Object obj) {
    if (obj instanceof OwnedEntity entity) {
      validateAccess(entity);
    }
  }

  @PreRemove
  public void preRemove(Object obj) {
    if (obj instanceof OwnedEntity entity) {
      validateAccess(entity);
    }
  }

  public void validateAccess(OwnedEntity entity) {
    if (accessService.checkAccess(entity)) return;
    throw new AccessDeniedException("Permission denied");
  }
}

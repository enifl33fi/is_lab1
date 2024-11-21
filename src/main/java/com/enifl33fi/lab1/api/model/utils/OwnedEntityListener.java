package com.enifl33fi.lab1.api.model.utils;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

public class OwnedEntityListener {
  private static OwnedEntityHandler handler;

  public static void setHandler(OwnedEntityHandler handlerBean) {
    OwnedEntityListener.handler = handlerBean;
  }

  @PrePersist
  public void prePersist(Object obj) {
    if (obj instanceof OwnedEntity entity) {
      handler.setOwner(entity);
    }
  }

  @PreUpdate
  public void preUpdate(Object obj) {
    if (obj instanceof OwnedEntity entity) {
      handler.validateAccess(entity);
    }
  }

  @PreRemove
  public void preRemove(Object obj) {
    if (obj instanceof OwnedEntity entity) {
      handler.validateAccess(entity);
    }
  }
}

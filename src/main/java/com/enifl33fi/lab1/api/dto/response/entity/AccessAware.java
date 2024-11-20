package com.enifl33fi.lab1.api.dto.response.entity;

public interface AccessAware {
  boolean isHasAccess();

  void setHasAccess(boolean hasAccess);
}

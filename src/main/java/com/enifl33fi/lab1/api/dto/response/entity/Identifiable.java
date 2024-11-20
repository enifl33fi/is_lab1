package com.enifl33fi.lab1.api.dto.response.entity;

public interface Identifiable<T> {
  T getId();

  void setId(T id);
}

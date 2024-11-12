package com.enifl33fi.lab1.api.exception;

public class UsernameNotUniqueException extends RuntimeException {
  public UsernameNotUniqueException(String username) {
    super(String.format("Username '%s' already exists", username));
  }
}

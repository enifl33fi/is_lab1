package com.enifl33fi.lab1.api.exception;

public class XAResourceIsNotReadyException extends RuntimeException {
  public final String resource;

  public XAResourceIsNotReadyException(String resource) {
    super(String.format("Resource %s is not ready", resource));

    this.resource = resource;
  }
}

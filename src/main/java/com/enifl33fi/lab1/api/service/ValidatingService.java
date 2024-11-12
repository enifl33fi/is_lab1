package com.enifl33fi.lab1.api.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidatingService {

  private final Validator validator;

  public void validateEntity(Object entity) {
    Set<ConstraintViolation<Object>> violations = validator.validate(entity);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}

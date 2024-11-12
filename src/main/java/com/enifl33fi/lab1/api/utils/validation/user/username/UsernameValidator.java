package com.enifl33fi.lab1.api.utils.validation.user.username;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

  @Value("${validation.user.username.regexp}")
  private String usernameRegExp;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && value.matches(usernameRegExp);
  }
}

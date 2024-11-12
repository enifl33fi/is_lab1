package com.enifl33fi.lab1.api.utils.validation.user.username;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {UsernameValidator.class})
public @interface ValidUsername {
  String message() default "{validation.user.username.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

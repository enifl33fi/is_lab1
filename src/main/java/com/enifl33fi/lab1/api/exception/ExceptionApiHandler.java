package com.enifl33fi.lab1.api.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ResponseBody
  @ExceptionHandler({
    UsernameNotFoundException.class,
    UsernameNotUniqueException.class,
    ConstraintViolationException.class,
    MethodArgumentNotValidException.class,
    NotFoundException.class
  })
  public ResponseEntity<?> badRequestHandler() {
    return ResponseEntity.badRequest().build();
  }

  @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
  @ResponseBody
  @ExceptionHandler({BadCredentialsException.class})
  public ResponseEntity<?> unauthorizedHandler() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
  @ResponseBody
  @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
  public ResponseEntity<?> methodNotAllowedHandler() {
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
  }

  @ResponseStatus(code = HttpStatus.FORBIDDEN)
  @ResponseBody
  @ExceptionHandler({RefreshTokenException.class, AccessDeniedException.class})
  public ResponseEntity<?> forbiddenHandler() {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }
}

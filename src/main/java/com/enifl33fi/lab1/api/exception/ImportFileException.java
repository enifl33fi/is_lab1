package com.enifl33fi.lab1.api.exception;

public class ImportFileException extends RuntimeException {
  public ImportFileException() {
    super("Ошибка в импортируемом файле");
  }
}

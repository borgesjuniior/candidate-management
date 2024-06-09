package br.com.blue.manager.exceptions;

public class UserFoundException extends RuntimeException {
  public UserFoundException(String message) {
    super(message);
  }
}

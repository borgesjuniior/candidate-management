package br.com.blue.manager.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ExeceptionHandlerController {
  private MessageSource messageSource;

  public ExeceptionHandlerController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    List<ErrorMessageDTO> errorMessage = new ArrayList<>();

    e.getBindingResult().getFieldErrors().forEach(err -> {
      String message = this.messageSource.getMessage(err, LocaleContextHolder.getLocale());
      ErrorMessageDTO error = new ErrorMessageDTO(message, err.getField());
      errorMessage.add(error);
    });

    return new ResponseEntity<List<ErrorMessageDTO>>(errorMessage, HttpStatus.OK);
  }
}
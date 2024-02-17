package io.github.cafeteru.profile.common.adapter.api;

import io.github.cafeteru.profile.common.adapter.api.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDto> catchException(IllegalArgumentException exception) {
        var error = ErrorDto.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

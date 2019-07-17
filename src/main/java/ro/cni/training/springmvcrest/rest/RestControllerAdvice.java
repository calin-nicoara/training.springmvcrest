package ro.cni.training.springmvcrest.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> notFound(NotFoundException nfe) {
        return ResponseEntity.notFound().build();
    }
}

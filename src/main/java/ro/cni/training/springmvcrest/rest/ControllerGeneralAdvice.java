package ro.cni.training.springmvcrest.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerGeneralAdvice {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> returnErrorMEssage(RuntimeException re) {
        return ResponseEntity.ok("Sorry! I don't have that product.");
    }
}

package ro.cni.training.springmvcrest.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModel {
    private String errorField;
    private String errorCode;
    private String errorMessage;
}

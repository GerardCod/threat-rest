package dev.gerardo.threatrest.apirest.exceptions.handlers;

import dev.gerardo.threatrest.apirest.exceptions.IpAlreadyBannedException;
import dev.gerardo.threatrest.apirest.exceptions.IpBannedInvalidException;
import dev.gerardo.threatrest.apirest.models.dto.FieldValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleNull(NullPointerException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        return response;
    }

    @ExceptionHandler(IpAlreadyBannedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIpAlreadyBanned(IpAlreadyBannedException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        return response;
    }

    @ExceptionHandler(IpBannedInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleIpBannedInvalid(IpBannedInvalidException e) {
        List<FieldValidationError> errors = new ArrayList<>();
        e.getErrors().forEach(err -> {
           FieldValidationError error = new FieldValidationError();
           error.setField(err.getField());
           error.setRejectedValue(err.getRejectedValue().toString());
           error.setMessage(err.getDefaultMessage());
           errors.add(error);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("message", "there are some errors in your input");
        response.put("errors", errors);
        return response;
    }

}

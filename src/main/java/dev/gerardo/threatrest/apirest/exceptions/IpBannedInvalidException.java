package dev.gerardo.threatrest.apirest.exceptions;

import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
public class IpBannedInvalidException extends RuntimeException {

    private static final long serialVersionUID = 987654321L;

    private List<FieldError> errors;

    public IpBannedInvalidException(String message) {
        super(message);
    }

    public IpBannedInvalidException(List<FieldError> errors) {
        this.errors = errors;
    }

}

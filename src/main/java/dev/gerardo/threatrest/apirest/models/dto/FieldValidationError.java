package dev.gerardo.threatrest.apirest.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldValidationError {
    private String field;
    private String rejectedValue;
    private String message;
}

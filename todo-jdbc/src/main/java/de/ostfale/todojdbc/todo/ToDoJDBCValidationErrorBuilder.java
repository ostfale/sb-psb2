package de.ostfale.todojdbc.todo;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Factory which helps to create a validation error
 * Created :  22.09.2019
 *
 * @author : Uwe Sauerbrei
 */
public class ToDoJDBCValidationErrorBuilder {

    public static ToDoJDBCValidationError fromBindingErrors(Errors errors) {
        ToDoJDBCValidationError error = new ToDoJDBCValidationError("Validation failed. " + errors.getErrorCount() + " error(s)!");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }
}

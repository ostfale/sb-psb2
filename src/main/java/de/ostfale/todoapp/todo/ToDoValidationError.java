package de.ostfale.todoapp.todo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds any errors which arise with any request. It uses an extra @{@link com.fasterxml.jackson.annotation.JsonInclude} which says
 * that even if the errors field is empty, it must be included.
 * Created :  22.09.2019
 *
 * @author : Uwe Sauerbrei
 */
public class ToDoValidationError {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> errors = new ArrayList<>();

    private final String errorMessage;

    public ToDoValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addValidationError(String errorMessage) {
        errors.add(errorMessage);
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

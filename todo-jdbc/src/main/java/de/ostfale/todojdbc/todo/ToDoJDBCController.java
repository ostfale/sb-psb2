package de.ostfale.todojdbc.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Controller class
 * Created :  22.09.2019
 *
 * @author : Uwe Sauerbrei
 */
@RestController
@RequestMapping("/api")
public class ToDoJDBCController {

    private CommonRepository<ToDoJDBC> repository;

    @Autowired
    public ToDoJDBCController(CommonRepository<ToDoJDBC> repository) {
        this.repository = repository;
    }

    @GetMapping("/todo")
    public ResponseEntity<Iterable<ToDoJDBC>> getToDos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDoJDBC> getToDoById(@PathVariable String id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @PatchMapping("/todo/{id}")
    public ResponseEntity<ToDoJDBC> setCompleted(@PathVariable String id) {
        ToDoJDBC result = repository.findById(id);
        result.setCompleted(true);
        repository.save(result);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(result.getId()).toUri();
        return ResponseEntity.ok().header("Location", location.toString()).build();
    }

    @RequestMapping(value = "/todo", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> createToDo(@Valid @RequestBody ToDoJDBC toDoJDBC, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ToDoJDBCValidationErrorBuilder.fromBindingErrors(errors));
        }
        ToDoJDBC result = repository.save(toDoJDBC);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ToDoJDBC> deleteToDo(@PathVariable String id) {
        repository.delete(ToDoJDBCBuilder.create().withId(id).build());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todo")
    public ResponseEntity<ToDoJDBC> deleteToDo(@RequestBody ToDoJDBC toDoJDBC) {
        repository.delete(toDoJDBC);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoJDBCValidationError handleException(Exception exception) {
        return new ToDoJDBCValidationError(exception.getMessage());
    }
}

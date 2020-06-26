package com.ahmad.springboot.todos;

import com.ahmad.springboot.security.AppUser;
import com.ahmad.springboot.security.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/todos")
public class TodoController extends BaseController {

    @Autowired
    private TodoService todoService;


    // Will return the below as JSON formatted
    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<Todo>> getAllTodos() {

        List<Todo> result = todoService.findByUser(getCurrentUser().getId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable String id) {

        Todo result = todoService.getByUserIdAndId(getCurrentUser().getId(), id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Todo> createNewTodo(@Valid @RequestBody Todo todo) {

        todo.setUserId(getCurrentUser().getId());
        Todo result = todoService.save(todo);
        return new ResponseEntity<Todo>(result, HttpStatus.CREATED);
    }

    @PutMapping(value = {"", "/"})
    public ResponseEntity<Todo> editTodo(@Valid @RequestBody Todo todo) {

        todo.setUserId(getCurrentUser().getId());
        Todo result = todoService.edit(todo);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id) {

        todoService.delete(getCurrentUser().getId(), id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public AppUser getCurrentUser() {
        return super.getCurrentUser();
    }
}

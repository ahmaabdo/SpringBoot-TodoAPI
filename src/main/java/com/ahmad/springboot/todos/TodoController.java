package com.ahmad.springboot.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;


    // Will return the below as JSON formatted
    @GetMapping(value = {"", "/"})
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable String id) {
        return todoService.getById(id);
    }

    @PostMapping(value = {"", "/"})
    public Todo createNewTodo(@RequestBody Todo todo) {
            return todoService.save(todo);
    }

    @PutMapping(value = {"", "/"})
    public Todo editTodo(@RequestBody Todo todo) {
        return todoService.edit(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id) {
        todoService.delete(id);
    }
}

package com.ahmad.springboot.todos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/todos")
public class TodoController {

    private List<Todo> data = Arrays.asList(
            new Todo(1, "First todo", "this is my first task"),
            new Todo(2, "Second todo", "this is my second task"),
            new Todo(3, "Third todo", "this is my third task"),
            new Todo(4, "Forth todo", "this is my forth task"),
            new Todo(5, "Fifth todo", "this is my fifth task"),
            new Todo(6, "Sixth todo", "this is my sixth task")
    );


    // Will return the below as JSON formatted
    @GetMapping(value = {"", "/"})
    public List<Todo> listTodos() {

        return data;
    }


}

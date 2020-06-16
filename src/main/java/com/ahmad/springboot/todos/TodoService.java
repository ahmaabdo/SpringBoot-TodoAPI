package com.ahmad.springboot.todos;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TodoService {

    private List<Todo> data = new ArrayList<>(Arrays.asList(
            new Todo(1, "First todo", "this is my first task"),
            new Todo(2, "Second todo", "this is my second task"),
            new Todo(3, "Third todo", "this is my third task"),
            new Todo(4, "Forth todo", "this is my forth task"),
            new Todo(5, "Fifth todo", "this is my fifth task"),
            new Todo(6, "Sixth todo", "this is my sixth task")
    )
    );

    public List<Todo> findAll() {
        return data;
    }

    public Todo getById(int id) {
        for (Todo todo : data)
            if (todo.getId() == id)
                return todo;

        return null;
    }

    public boolean save(Todo todo) {
        return data.add(todo);
    }

    public void delete(int id) {
//        for (Todo todo : data)
//            if (todo.getId() == id)
//                data.remove(todo);

        data.removeIf(todo -> todo.getId() == id);
    }

    public Todo edit(Todo todo) {
        for (int i = 0; i < data.size(); i++)
            if (data.get(i).getId() == todo.getId()) {
                data.set(i, todo);
                return data.get(i);
            }
        return null;
    }
}

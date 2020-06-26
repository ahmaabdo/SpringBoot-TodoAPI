package com.ahmad.springboot.todos;

import com.ahmad.springboot.error.ConflictException;
import com.ahmad.springboot.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public List<Todo> findByUser(String id) {
        return todoRepository.findByUserId(id);
    }

    public Todo getById(String id) {
        try {
            return todoRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException(String.format("No Record with the id [%s] was found in the database", id));
        }
    }

    public Todo getByUserIdAndId(String userId, String id) {

        if (!todoRepository.existsByUserIdAndId(userId, id))
            throw new NotFoundException(String.format("No Record with the id [%s] was found in the database", id));

        return todoRepository.findByIdAndUserId(id, userId);
    }

    public Todo save(Todo todo) {
        if (todoRepository.findByTitle(todo.getTitle()) != null)
            throw new ConflictException("Another record with the same title exists");

        return todoRepository.insert(todo);
    }

    public void delete(String userId, String id) {
        if (!todoRepository.existsByUserIdAndId(userId, id))
            throw new NotAcceptableStatusException("Id is not correct");

        todoRepository.deleteById(id);
    }

    public Todo edit(Todo todo) {
        if (!todoRepository.existsById(todo.getId()))
            throw new NotFoundException(String.format("No Record with the id [%s] was found in the database", todo.getId()));

        if (!todoRepository.existsByUserIdAndId(todo.getUserId(), todo.getId()))
            throw new NotAcceptableStatusException("User id is not correct");

        if (todoRepository.findByTitleAndIdNot(todo.getTitle(), todo.getId()) != null)
            throw new ConflictException("Another record with the same title exists");

        return todoRepository.save(todo);
    }

}

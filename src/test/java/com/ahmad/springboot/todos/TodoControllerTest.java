package com.ahmad.springboot.todos;

import com.ahmad.springboot.AbstractTodoAppTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.BDDMockito.given;

public class TodoControllerTest extends AbstractTodoAppTest {


    @MockBean
    private TodoService todoService;

    @Test
    public void whenGetAllTodos_ThenReturnJsonArray() throws Exception {

        //Mockup
        Todo todo1 = new Todo("1", "Todo 1", "Todo 1");
        Todo todo2 = new Todo("2", "Todo 2", "Todo 2");

        List<Todo> data = Arrays.asList(todo1, todo2);

        given(todoService.findByUser(anyString())).willReturn(data);

        mockMvc.perform(doGet("/api/v1/todos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", equalTo(todo1.getTitle())));
    }


    @Test
    public void whenPostTodo_ThenCreateTodo() throws Exception {
        Todo todo = new Todo();
        todo.setTitle("Title of Todo");
        todo.setDescription("Description of Todo");

        given(todoService.save(Mockito.any(Todo.class))).willReturn(todo);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(
                doPost("/api/v1/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(todo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(todo.getTitle())));

    }

}

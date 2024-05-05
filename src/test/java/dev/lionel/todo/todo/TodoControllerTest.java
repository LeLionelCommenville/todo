package dev.lionel.todo.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TodoRepository repository;

    private final List<Todo> todos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        todos.add(new Todo(1,
                "todo 1",
                "todo 1 content",
                20,
                false,
                null));
    }

    @Test
    void shouldFindAllTodos() throws Exception {
        when(repository.findAll()).thenReturn(todos);
        mvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(todos.size())));
    }

    @Test
    void shouldReturnNotFoundWithInvalidId() throws Exception {
        mvc.perform(get("/api/todos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewTodo() throws Exception {
        var todo = new Todo(null,"new todo", "new todo content", 20,false, null);
        mvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todo))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateTodo() throws Exception {
        var todo = new Todo(null,"new todo", "new todo content", 20,false, null);
        mvc.perform(put("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todo))
                )
                .andExpect(status().isNoContent());
    }
}
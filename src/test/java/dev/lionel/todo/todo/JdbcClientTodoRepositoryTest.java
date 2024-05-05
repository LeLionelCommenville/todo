package dev.lionel.todo.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JdbcClientTodoRepository.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class JdbcClientTodoRepositoryTest {

    @Autowired
    JdbcClientTodoRepository repository;

    @BeforeEach
    void setUp() {
        repository.create(new Todo(1,
                "todo 1",
                "todo 1 content",
                20,
                false,
                null));
        repository.create(new Todo(2,
                "todo 2",
                "todo 2 content",
                30,
                false,
                null));

        repository.create(new Todo(3,
                "todo 3",
                "todo 3 content",
                40,
                false,
                null));
    }

    @Test
    void shouldFindAllRuns() {
        List<Todo> todos = repository.findAll();
        assertEquals(3, todos.size());
    }

    @Test
    void shouldFindTodoWithValidId() {
        var todo = repository.findById(2).get();
        assertEquals("todo 2", todo.title());
        assertEquals(30, todo.completion());
    }

    @Test
    void shouldNotFindRunWithInvalidId() {
        var todo = repository.findById(4);
        assertTrue(todo.isEmpty());
    }

    @Test
    void shouldCreateNewRun() {
        repository.create(new Todo(4,
                "todo 4",
                "todo content 4",
                30,
                false,
                null
                ));
        List<Todo> todos = repository.findAll();
        assertEquals(4, todos.size());
    }

    @Test
    void shouldUpdateTodo() {
        repository.update(new Todo(1,
                "todo 1 updated",
                "todo 1 content updated",
                20,
                true,
                null), 1);
        var todo = repository.findById(1).get();
        assertEquals("todo 1 updated", todo.title());
        assertEquals(20, todo.completion());
        assertEquals(true, todo.done());
    }

    @Test
    void shouldDeleteTodo() {
        repository.delete(1);
        List<Todo> todos = repository.findAll();
        assertEquals(2, todos.size());
    }


}
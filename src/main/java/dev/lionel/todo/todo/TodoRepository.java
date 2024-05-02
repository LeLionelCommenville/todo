package dev.lionel.todo.todo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {
    private List<Todo> todos = new ArrayList<>();

    List<Todo> findAll() {
        return todos;
    }

    Optional<Todo> findById(Integer id) {
        return todos.stream()
                .filter(todo -> todo.id() == id)
                .findFirst();
    }

    @PostConstruct
    private void init() {
        todos.add(new Todo(
                1,
                "Create a Todo API",
                "We need a Todo Service for managing the todo list with some functions",
                50,
                 false
        ));
        todos.add(new Todo(
                2,
                "Create a Todo API PART2",
                "We need a Todo Service for managing the todo list with some functions part 2",
                50,
                false
        ));
        todos.add(new Todo(
                3,
                "Create a Todo API PART3",
                "We need a Todo Service for managing the todo list with some functions part 2",
                20,
                true
        ));
    }

}

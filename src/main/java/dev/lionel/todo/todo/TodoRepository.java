package dev.lionel.todo.todo;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TodoRepository extends ListCrudRepository<Todo, Integer> {
    List<Todo> findAllByDone(Boolean done);
}

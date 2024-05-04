package dev.lionel.todo.todo;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientTodoRepository {

    private final JdbcClient jdbcClient;

    public JdbcClientTodoRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Todo> findAll() {
        return jdbcClient.sql("select * from TODO")
                .query(Todo.class).list();
    }

    public Optional<Todo> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM TODO WHERE id = :id")
                .param("id", id)
                .query(Todo.class)
                .optional();
    }

    public void create(Todo todo) {
        var updated = jdbcClient.sql("INSERT INTO TODO(id, title, content, completion, done) values(?,?,?,?,?)")
                .params(List.of(todo.id(), todo.title(), todo.content(), todo.completion(), todo.done()))
                .update();

        Assert.state(updated == 1, "failled to create a new todo " + todo.title());
    }

    public void update(Todo todo, Integer id) {
        var updated = jdbcClient.sql("update todo set title =  ?, content = ?, completion = ?, done = ? where id = ? ")
                .params(List.of(todo.title(), todo.content(), todo.completion(), todo.done(), id))
                .update();

        Assert.state(updated == 1, "failled to update the todo " + todo.title());

    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from TODO where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "failled delete todo " + id);

    }

    public void saveAll(List<Todo> todos) {
        todos.stream().forEach(this::create);
    }

    public int count() { return jdbcClient.sql("select * from TODO").query().listOfRows().size(); }


}

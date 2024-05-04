package dev.lionel.todo.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class TodoJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TodoJsonDataLoader.class);
    private final JdbcClientTodoRepository todoRepository;
    private final ObjectMapper objectMapper;


    public TodoJsonDataLoader(JdbcClientTodoRepository todoRepository, ObjectMapper objectMapper) {
        this.todoRepository = todoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(todoRepository.count() == 0) {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/todos.json")) {
                Todos allTodos = objectMapper.readValue(inputStream, Todos.class);
                log.info("Reading {} runs from JSON data and saving it in a database", allTodos.todos().size());
                todoRepository.saveAll(allTodos.todos());
            } catch (IOException e) {
                throw new RuntimeException("Faied to read JSON data", e);
            }
        } else {
            log.info("Not loading Todos from JSON data beause the collection contains data.");
        }


    }

}

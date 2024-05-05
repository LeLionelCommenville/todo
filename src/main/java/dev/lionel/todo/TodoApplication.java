package dev.lionel.todo;

import dev.lionel.todo.todo.JdbcClientTodoRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {

	private static final Logger log = LoggerFactory.getLogger(TodoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
		log.info("Todo application started good");
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			log.info("welcome back");
		};
	}

}

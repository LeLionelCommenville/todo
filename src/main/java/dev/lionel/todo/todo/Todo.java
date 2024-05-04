package dev.lionel.todo.todo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Todo(
        @Positive
        @Id
        Integer id,
        @NotEmpty
        String title,
        @NotEmpty
        String content,
        @Positive
        Integer completion,
        Boolean done,
        @Version
        Integer version
) {
        public Todo {
                if(completion > 100) {
                        throw new IllegalArgumentException("Completion can't be superior to one hundred");
                }
        }

}

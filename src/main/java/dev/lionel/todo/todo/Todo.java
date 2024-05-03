package dev.lionel.todo.todo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Todo(
        @Positive
        Integer id,
        @NotEmpty
        String title,
        @NotEmpty
        String content,
        @Positive
        Integer completion,
        Boolean done
) {
        public Todo {
                if(completion > 100) {
                        throw new IllegalArgumentException("Completion can't be superior to one hundred");
                }
        }

}

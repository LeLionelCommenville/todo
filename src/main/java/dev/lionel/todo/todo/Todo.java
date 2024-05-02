package dev.lionel.todo.todo;

public record Todo(
        Integer id,
        String title,
        String content,
        Integer completion,
        Boolean done
) {}

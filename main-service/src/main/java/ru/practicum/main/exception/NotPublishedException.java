package ru.practicum.main.exception;

public class NotPublishedException extends RuntimeException {
    public NotPublishedException(String message) {
        super(message);
    }
}

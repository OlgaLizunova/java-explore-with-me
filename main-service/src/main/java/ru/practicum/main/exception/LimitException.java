package ru.practicum.main.exception;

public class LimitException extends RuntimeException {
    public LimitException(String message) {
        super(message);
    }
}

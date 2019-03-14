package ru.homework.exceptions;

public class NotUniqueIdException extends DbException {

    public NotUniqueIdException(String message) {
        super(message);
    }

    public NotUniqueIdException(String message, Throwable cause) {
        super(message, cause);
    }
}

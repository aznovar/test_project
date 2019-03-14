package ru.homework.exceptions;

public class NoSuchIdException extends DbException{

    public NoSuchIdException(String message) {
        super(message);
    }

    public NoSuchIdException(String message, Throwable cause) {
        super(message, cause);
    }
}

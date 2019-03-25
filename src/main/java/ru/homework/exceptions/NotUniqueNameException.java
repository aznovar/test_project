package ru.homework.exceptions;

public class NotUniqueNameException extends DbException {

    public NotUniqueNameException(String message) {
        super(message);
    }
}

package ru.homework.exceptions;

public class NoSuchNameException extends DbException{

    public NoSuchNameException(String message) {
        super(message);
    }
}

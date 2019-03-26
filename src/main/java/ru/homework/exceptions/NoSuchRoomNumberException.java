package ru.homework.exceptions;

public class NoSuchRoomNumberException extends DbException {

    public NoSuchRoomNumberException(String message) {
        super(message);
    }
}

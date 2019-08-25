package ru.votingsystem.util.exception;

public class VoteRepeatException extends RuntimeException {
    public VoteRepeatException(String message) {
        super(message);
    }
}

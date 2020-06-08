package com.weymanator.messages_app.db.exceptions;

public class NoUserFoundException extends Exception {
    public NoUserFoundException(String errorMessage) {
        super (errorMessage);
    }
}

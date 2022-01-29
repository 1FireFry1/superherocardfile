package com.firefry.superherocardfile.exception;

import java.io.IOException;

public class NotExistDirectoryException extends IOException {
    public NotExistDirectoryException(String message){
        super(message);
    }
}

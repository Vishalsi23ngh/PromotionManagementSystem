package com.example.Promotion.Management.System.Exceptions;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException(String message){
        super(message);
    }
}

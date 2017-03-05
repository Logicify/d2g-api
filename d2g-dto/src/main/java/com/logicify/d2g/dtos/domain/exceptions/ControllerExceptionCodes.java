package com.logicify.d2g.dtos.domain.exceptions;

/**
 * Created by jadencorr on 05.03.17.
 */
public enum ControllerExceptionCodes {

    ALL_CORRECT(0, null),
    UNCORRECTED_PASSWORD(1, "This password not allowed"),
    USER_NOT_EXIST(2, "User not exist");

    private final int id;
    private final String message;

    ControllerExceptionCodes(int id, String message){
        this.id = id;
        this.message = message;
    }

    public int getId(){
        return id;
    }

    public String getMessage(){
        return message;
    }

}

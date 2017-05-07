package com.logicify.d2g.exceptions;

/**
 * Created by jadencorr on 05.03.17.
 */
public enum D2GBaseExceptionCodes {

    ALL_CORRECT(0, null),
    SOMETHING_WENT_WRONG(1, "Something went wrong"),
    UNCORRECTED_PASSWORD(2, "This password not allowed"),
    USER_NOT_EXIST(3, "User not exist"),
    WRONG_DATA(4,""),
    WRONG_STATUS(5, "Wrong user status"),
    USER_ALREADY_EXIST(6, "User with this email already exist");



    private final int id;
    private final String message;

    D2GBaseExceptionCodes(int id, String message){
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

package com.logicify.d2g.dtos.domain.exceptions;

/**
 * Created by jadencorr on 05.03.17.
 */
public enum ControllerExceptionCodes {

    ALL_CORRECT(0, null),
    UNCORRECTED_PASSWORD(1, "This password not allowed"),
    USER_NOT_EXIST(2, "User not exist"),
    EMAIL_TO_LONG(3, "Email size is to long"),
    AVATAR_URL_TO_LONG(4, "Avatar URL is to long"),
    FIRST_NAME_TO_LONG(5, "First name is to long"),
    LAST_NAME_TO_LONG(6, "Last name is to long"),
    WRONG_STATUS(7, "Wrong user status");



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

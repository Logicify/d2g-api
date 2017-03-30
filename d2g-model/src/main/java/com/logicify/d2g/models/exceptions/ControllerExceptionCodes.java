package com.logicify.d2g.models.exceptions;

/**
 * Created by jadencorr on 05.03.17.
 */
public enum ControllerExceptionCodes {

    ALL_CORRECT(0, null),
    UNCORRECTED_PASSWORD(1, "This password not allowed"),
    USER_NOT_EXIST(2, "User not exist"),
    EMAIL_TO_LONG(3, "Email size is too long"),
    AVATAR_URL_TO_LONG(4, "Avatar URL is too long"),
    FIRST_NAME_TO_LONG(5, "First name is too long"),
    LAST_NAME_TO_LONG(6, "Last name is too long"),
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

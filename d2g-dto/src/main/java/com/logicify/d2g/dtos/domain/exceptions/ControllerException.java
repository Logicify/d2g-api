package com.logicify.d2g.dtos.domain.exceptions;

/**
 * Created by jadencorr on 03.03.17.
 */
public class ControllerException extends Exception {

    private int errorCode;
    private String errorMessage;

    public ControllerException(ControllerExceptionCodes code){
        this.errorCode = code.getId();
        this.errorMessage = code.getMessage();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

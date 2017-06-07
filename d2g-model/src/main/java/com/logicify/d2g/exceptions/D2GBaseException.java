package com.logicify.d2g.exceptions;

/**
 * Created by jadencorr on 03.03.17.
 */
public class D2GBaseException extends Exception {

    private int errorCode;
    private String errorMessage;

    public D2GBaseException(NewD2GBaseExceptionCodes errorCode) {
        this.errorCode=errorCode.getId();
        this.errorMessage=errorCode.getErrorMessage();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

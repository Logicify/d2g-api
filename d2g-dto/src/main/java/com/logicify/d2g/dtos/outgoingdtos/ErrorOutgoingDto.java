package com.logicify.d2g.dtos.outgoingdtos;

import com.logicify.d2g.dtos.DtosDomains.OutgoingDto;

/**
 * Created by jadencorr on 23.02.17.
 */
public class ErrorOutgoingDto implements OutgoingDto
{
    protected boolean hasError;

    protected String errorMessage;

    public ErrorOutgoingDto(String errorMessage){
        this.hasError = true;
        this.errorMessage = errorMessage;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

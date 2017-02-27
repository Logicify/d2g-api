package com.logicify.d2g.dtos.outgoingdtos;

import com.logicify.d2g.dtos.DtosDomains.OutgoingDto;

/**
 * Created by jadencorr on 27.02.17.
 */
public class MessageOutgoingDto implements OutgoingDto {

    protected String successMessage;

    protected boolean hasError;

    public MessageOutgoingDto(String successMessage) {
        this.hasError = false;
        this.successMessage = successMessage;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}

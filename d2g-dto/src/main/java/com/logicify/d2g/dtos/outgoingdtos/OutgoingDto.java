package com.logicify.d2g.dtos.outgoingdtos;

/**
 * Created by jadencorr on 23.02.17.
 */
public class OutgoingDto {

    protected boolean hasError;

    protected String errorMessage;

    protected OutgoingDto (){
        hasError=false;
        errorMessage=null;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}

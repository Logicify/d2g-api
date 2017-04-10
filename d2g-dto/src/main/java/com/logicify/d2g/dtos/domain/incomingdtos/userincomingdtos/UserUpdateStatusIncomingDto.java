package com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;

import javax.validation.constraints.NotNull;

/**
 * Created by jadencorr on 27.02.17.
 */
public class UserUpdateStatusIncomingDto implements IncomingDto {

    @NotNull(message = "Data is empty")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

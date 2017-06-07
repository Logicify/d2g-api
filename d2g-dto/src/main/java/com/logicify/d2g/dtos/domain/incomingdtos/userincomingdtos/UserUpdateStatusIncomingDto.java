package com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;
import com.logicify.d2g.dtos.domain.dtosvalidation.annotation.Enum;
import com.logicify.d2g.interfaces.UserStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by jadencorr on 27.02.17.
 */
public class UserUpdateStatusIncomingDto implements IncomingDto {

    @NotNull(message = "WRONG_STATUS")
    @Enum(enumClass = UserStatus.class, message = "WRONG_STATUS")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

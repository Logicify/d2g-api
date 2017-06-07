package com.logicify.d2g.dtos.domain.incomingdtos.securitysincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;
import com.logicify.d2g.exceptions.NewD2GBaseExceptionCodes;
import com.logicify.d2g.interfaces.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.logicify.d2g.exceptions.NewD2GBaseExceptionCodes.NO_EMAIL_OR_PASSWORD;

/**
 * Created by olegchigirin on 18.04.17.
 */
public class UserLoginIncomingDto implements IncomingDto {

    @NotNull(message = "NO_EMAIL_OR_PASSWORD")
    private String email;

    @NotNull(message = "NO_EMAIL_OR_PASSWORD")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

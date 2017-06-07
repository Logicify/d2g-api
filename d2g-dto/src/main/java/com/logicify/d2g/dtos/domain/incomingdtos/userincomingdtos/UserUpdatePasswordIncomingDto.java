package com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;
import com.logicify.d2g.interfaces.User;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by twilight on 14.05.17.
 */
public class UserUpdatePasswordIncomingDto implements IncomingDto {

    private String oldPassword;

    @Pattern.List(value = {@Pattern(regexp = "^[\\w\\p{Punct}]+$", message = "UNCORRECTED_PASSWORD"),
            @Pattern(regexp = "^.*[A-Z].*$", message = "UNSECURED_PASSWORD"),
            @Pattern(regexp = "^.*[0-9].*$", message = "UNSECURED_PASSWORD")})
    @Size(max = User.PASSWORD_MAX_LENGTH, min = User.PASSWORD_MIN_LENGTH, message = "UNCORRECTED_PASSWORD")
    private String newPassword;

    @Pattern.List(value = {@Pattern(regexp = "^[\\w\\p{Punct}]+$", message = "UNCORRECTED_PASSWORD"),
            @Pattern(regexp = "^.*[A-Z].*$", message = "UNSECURED_PASSWORD"),
            @Pattern(regexp = "^.*[0-9].*$", message = "UNSECURED_PASSWORD")})
    @Size(max = User.PASSWORD_MAX_LENGTH, min = User.PASSWORD_MIN_LENGTH, message = "UNCORRECTED_PASSWORD")
    private String repeatPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}

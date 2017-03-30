package com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;
import com.logicify.d2g.models.interfaces.usermodel.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author knorr
 */
public class UserCreateIncomingDto implements IncomingDto {

    @NotNull(message = "First name is empty")
    @Size(max = User.MAX_NAME_LENGTH, message = "First name is too long")
    @Valid
    private String firstName;

    @NotNull(message = "Last name is empty")
    @Size(max = User.MAX_NAME_LENGTH, message = "First name is too long")
    private String lastName;

    @NotNull(message = "Email is empty")
    @Size(max = User.MAX_EMAIL_LENGTH, message = "Email is too long")
    private String email;

    @NotNull(message = "Password is empty")
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

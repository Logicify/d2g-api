package com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;
import com.logicify.d2g.interfaces.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author knorr
 */
public class UserCreateIncomingDto implements IncomingDto {

    @NotNull(message = "FIRST_NAME_IS_NULL")
    @Size(max = User.FIRST_NAME_MAX_LENGTH,min = User.FIRST_NAME_MIN_LENGTH,message = "WRONG_FIRST_NAME")
    @Pattern(regexp = "^[A-Za-z]+([A-Za-z\\s'-]*[A-Za-z]+)?$", message = "WRONG_FIRST_NAME")
    private String firstName;

    @NotNull(message = "LAST_NAME_IS_NULL")
    @Size(max = User.LAST_NAME_MAX_LENGTH, min = User.LAST_NAME_MIN_LENGTH, message = "WRONG_LAST_NAME")
    @Pattern(regexp = "^[A-z]+([A-z\\s'-]*[A-z]+)?$", message = "WRONG_LAST_NAME")
    private String lastName;

    @NotNull(message = "EMAIL_IS_NULL")
    @Pattern(regexp="^[-!#$%&'*+/0-9=?A-Z^_a-z{|}~](\\.?[-!#$%&'*+/0-9=?A-Z^_a-z{|}~])*@[a-zA-Z](-?[a-zA-Z0-9])*(\\.[a-zA-Z](-?[a-zA-Z0-9])*)+$", message= "INVALID_EMAIL")
    @Size(max = User.EMAIL_MAX_LENGTH, message = "INVALID_EMAIL")
    private String email;

    @NotNull(message = "PASSWORD_IS_NULL")
    @Pattern.List(value = {@Pattern(regexp = "^[\\w\\p{Punct}]+$", message = "UNCORRECTED_PASSWORD"),
            @Pattern(regexp = "^.*[A-Z].*$", message = "UNSECURED_PASSWORD"),
            @Pattern(regexp = "^.*[0-9].*$", message = "UNSECURED_PASSWORD")})
    @Size(max = User.PASSWORD_MAX_LENGTH, min = User.PASSWORD_MIN_LENGTH, message = "UNCORRECTED_PASSWORD")
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

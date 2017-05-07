package com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;
import com.logicify.d2g.interfaces.User;

import javax.validation.constraints.Size;

/**
 * Created by coi on 24.02.2017.
 */
public class UserUpdateIncomingDto implements IncomingDto {

    @Size(max = User.MAX_NAME_LENGTH, message = "First name is too long")
    private String firstName;

    @Size(max = User.MAX_NAME_LENGTH, message = "Last name is too long")
    private String lastName;

    private String password;

    @Size(max = User.AVATAR_URL_LENGTH, message = "Avatar Url is too long")
    private String avatarUrl;

    @Size(max = User.MAX_EMAIL_LENGTH, message = "Email is too long")
    private String email;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

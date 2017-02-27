package com.logicify.d2g.dtos.incomingdtos;

import com.logicify.d2g.dtos.DtosDomains.IncomingDto;

/**
 * Created by coi on 24.02.2017.
 */
public class UserUpdateIncomingDto implements IncomingDto {

    String firstName;

    String lastName;

    String password;

    String avatarUrl;

    String email;

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

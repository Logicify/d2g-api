package com.logicify.d2g.dtos.userdto.incomingdto;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;

/**
 * @author knorr
 */
public class UserCreateIncomingDto implements IncomingDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String avatarUrl;

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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}

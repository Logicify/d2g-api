package com.logicify.d2g.dtos.domain.outgoingdtos.userpayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.UUID;

/**
 * Created by jadencorr on 23.02.17.
 */
public class UserPayload implements Payload {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String avatarUrl;

    private String status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

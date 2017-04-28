package com.logicify.d2g.models.implementations;

import com.logicify.d2g.interfaces.User;
import com.logicify.d2g.interfaces.UserStatus;

import javax.persistence.*;

/**
 * @author knorr
 */
@Entity
@Table(name = "_users", schema = "public")
public class UserImpl extends BaseIdentifiable implements User {

    private String firstName;

    private String lastName;

    private String email;

    private String passwordHash;

    private UserStatus status;

    private String avatarUrl;

    @Column(name = "first_name", length = MAX_NAME_LENGTH, nullable = false)
    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", length = MAX_NAME_LENGTH, nullable = false)
    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email", length = MAX_EMAIL_LENGTH, nullable = false, unique = true)
    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password_hash", length = 512, nullable = false)
    @Override
    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Override
    public UserStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Column(name = "avatar_url", length = AVATAR_URL_LENGTH)
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}

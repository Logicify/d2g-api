package com.logicify.d2g.models;

import com.logicify.d2g.domain.User;
import com.logicify.d2g.domain.UserStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author knorr
 */
@Entity
@Table(name = "users")
public class UserImpl extends BaseIdentifiableAuditable implements User {

    private String firstName;

    private String lastName;

    private String email;

    private String passwordHash;

    private UserStatus status;

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

    @Column(name = "email", length = MAX_EMAIL_LENGTH, nullable = false)
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

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}

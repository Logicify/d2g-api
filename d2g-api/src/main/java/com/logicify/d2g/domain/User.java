package com.logicify.d2g.domain;

/**
 * @author knorr
 */
public interface User extends Identifiable {

    int MAX_NAME_LENGTH = 128;

    int MAX_EMAIL_LENGTH = 512;

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getPasswordHash();

    void setPasswordHash(String passwordHash);

    UserStatus getStatus();

    void setStatus(UserStatus status);

}

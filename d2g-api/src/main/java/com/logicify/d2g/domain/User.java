package com.logicify.d2g.domain;

/**
 * @author knorr
 */
public interface User extends Auditable {

    int MAX_NAME_LENGTH = 128;

    int MAX_EMAIL_LENGTH = 512;

    int AVATAR_URL_LENGTH = 1000;

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setAvatarUrl(String avatarUrl);

    String getAvatarUrl();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getPasswordHash();

    void setPasswordHash(String passwordHash);

    UserStatus getStatus();

    void setStatus(UserStatus status);
}

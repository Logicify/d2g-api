package com.logicify.d2g.interfaces;

import java.time.ZonedDateTime;

/**
 * @author knorr
 */
public interface User extends Identifiable {

    int EMAIL_MAX_LENGTH = 255;

    int PASSWORD_MIN_LENGTH = 8;

    int PASSWORD_MAX_LENGTH = 20;

    int FIRST_NAME_MIN_LENGTH = 2;

    int FIRST_NAME_MAX_LENGTH = 50;

    int LAST_NAME_MIN_LENGTH = 2;

    int LAST_NAME_MAX_LENGTH = 50;

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

    Role getRole();

    void setRole(Role role);

    ZonedDateTime getCreatedDate();

    void setCreatedDate(ZonedDateTime createdDate);

    Currency getCurrency();

    void setCurrency(Currency currency);
}

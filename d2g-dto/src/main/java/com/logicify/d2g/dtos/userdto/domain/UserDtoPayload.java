package com.logicify.d2g.dtos.userdto.domain;

import com.logicify.d2g.dtos.domain.dtos.Payload;

/**
 * Created by jadencorr on 06.03.17.
 */
public class UserDtoPayload implements Payload {

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    UserDto user;

    public UserDtoPayload(UserDto user){
        this.user=user;
    }
}

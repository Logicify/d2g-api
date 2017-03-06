package com.logicify.d2g.dtos.userdto.domain;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.List;

/**
 * Created by jadencorr on 06.03.17.
 */
public class UserListDtoPayload implements Payload {

    public List<UserDto> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UserDto> usersList) {
        this.usersList = usersList;
    }

    List<UserDto> usersList;

    public UserListDtoPayload(List<UserDto> usersList){
        this.usersList = usersList;
    }
}

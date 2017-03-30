package com.logicify.d2g.dtos.domain.outgoingdtos.userpayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.List;

/**
 * Created by jadencorr on 27.02.17.
 */
public class UsersListPayload implements Payload {

    private List<UserPayload> usersList;

    public List<UserPayload> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UserPayload> usersList) {
        this.usersList = usersList;
    }

}

package com.logicify.d2g.dtos.userdto.outcomingdto;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformationDto;
import com.logicify.d2g.dtos.userdto.domain.UserDto;

import java.util.List;

/**
 * Created by jadencorr on 27.02.17.
 */
public class UsersListOutgoingDto implements OutgoingDto {

    private List<UserDto> userList;

    private ServiceInformationDto service;

    public List<UserDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDto> userList) {
        this.userList = userList;
    }

    public ServiceInformationDto getService() {
        return service;
    }

    public void setService(ServiceInformationDto service) {
        this.service = service;
    }
}

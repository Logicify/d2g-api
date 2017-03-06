package com.logicify.d2g.dtos.userdto.outcomingdto;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformationDto;
import com.logicify.d2g.dtos.userdto.domain.UserDto;

/**
 * Created by jadencorr on 23.02.17.
 */
public class UserOutgoingDto implements OutgoingDto {

    private UserDto user;

    private ServiceInformationDto service;

    public ServiceInformationDto getService() {
        return service;
    }

    public void setService(ServiceInformationDto service) {
        this.service = service;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}

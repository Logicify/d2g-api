package com.logicify.d2g.dtos.userdto.outcomingdto;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformationDto;
import com.logicify.d2g.dtos.userdto.domain.UserDto;

/**
 * Created by jadencorr on 23.02.17.
 */
public class UserOutgoingDto implements OutgoingDto {

    private UserDto userDto;

    private ServiceInformationDto service;

    public ServiceInformationDto getService() {
        return service;
    }

    public void setService(ServiceInformationDto service) {
        this.service = service;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}

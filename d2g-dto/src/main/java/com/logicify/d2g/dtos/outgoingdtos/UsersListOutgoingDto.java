package com.logicify.d2g.dtos.outgoingdtos;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformationDto;
import com.logicify.d2g.dtos.domain.dtos.UserDto;

import java.util.List;

/**
 * Created by jadencorr on 27.02.17.
 */
public class UsersListOutgoingDto implements OutgoingDto {

    private List<UserDto> userDtoList;

    private ServiceInformationDto service;

    public List<UserDto> getUserDtoList() {
        return userDtoList;
    }

    public void setUserDtoList(List<UserDto> userDtoList) {
        this.userDtoList = userDtoList;
    }

    public ServiceInformationDto getService() {
        return service;
    }

    public void setService(ServiceInformationDto service) {
        this.service = service;
    }
}

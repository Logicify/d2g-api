package com.logicify.d2g.dtos.outgoingdtos;

import com.logicify.d2g.dtos.DtosDomains.OutgoingDto;

import java.util.List;

/**
 * Created by jadencorr on 27.02.17.
 */
public class UsersListOutgoingDto implements OutgoingDto {

    List<UserOutgoingDto> userOutgoingDtoList;

    public List<UserOutgoingDto> getUserOutgoingDtoList() {
        return userOutgoingDtoList;
    }

    public void setUserOutgoingDtoList(List<UserOutgoingDto> userOutgoingDtoList) {
        this.userOutgoingDtoList = userOutgoingDtoList;
    }
}

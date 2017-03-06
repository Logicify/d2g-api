package com.logicify.d2g.dtos.userdto.outcomingdto;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformationDto;
import com.logicify.d2g.dtos.userdto.domain.UserListDtoPayload;

/**
 * Created by jadencorr on 27.02.17.
 */
public class UsersListOutgoingDto implements OutgoingDto {

    private ServiceInformationDto service;

    public UserListDtoPayload getPayload() {
        return payload;
    }

    public void setPayload(UserListDtoPayload payload) {
        this.payload = payload;
    }

    private UserListDtoPayload payload;

    public ServiceInformationDto getService() {
        return service;
    }

    public void setService(ServiceInformationDto service) {
        this.service = service;
    }
}

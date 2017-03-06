package com.logicify.d2g.dtos.userdto.outcomingdto;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformationDto;
import com.logicify.d2g.dtos.userdto.domain.UserDtoPayload;

/**
 * Created by jadencorr on 23.02.17.
 */
public class UserOutgoingDto implements OutgoingDto {

    public UserDtoPayload getPayload() {
        return payload;
    }

    public void setPayload(UserDtoPayload payload) {
        this.payload = payload;
    }

    private UserDtoPayload payload;

    private ServiceInformationDto service;

    public ServiceInformationDto getService() {
        return service;
    }

    public void setService(ServiceInformationDto service) {
        this.service = service;
    }

}

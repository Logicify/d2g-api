package com.logicify.d2g.dtos.outgoingdtos;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformationDto;

/**
 * Created by jadencorr on 27.02.17.
 */
public class SuccessOutgoingDto implements OutgoingDto {

    private ServiceInformationDto service;


    public ServiceInformationDto getService() {
        return service;
    }

    public void setService(ServiceInformationDto service) {
        this.service = service;
    }
}

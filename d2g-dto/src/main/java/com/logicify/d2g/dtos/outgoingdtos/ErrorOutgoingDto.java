package com.logicify.d2g.dtos.outgoingdtos;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformationDto;

/**
 * Created by jadencorr on 23.02.17.
 */
public class ErrorOutgoingDto implements OutgoingDto
{
    private ServiceInformationDto service;


    public ServiceInformationDto getService() {
        return service;
    }

    public void setService(ServiceInformationDto service) {
        this.service = service;
    }
}

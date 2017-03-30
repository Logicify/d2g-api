package com.logicify.d2g.dtos.domain.outgoingdtos;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.Payload;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformation;

/**
 * Created by jadencorr on 30.03.17.
 */
public class ResponseDto extends OutgoingDto {

    Payload payload;

    ServiceInformation service;

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public ServiceInformation getService() {
        return service;
    }

    public void setService(ServiceInformation service) {
        this.service = service;
    }
}

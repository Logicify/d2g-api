package com.logicify.d2g.dtos.domain.outgoingdtos;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.Payload;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformation;
import com.logicify.d2g.dtos.domain.outgoingdtos.ResponseDto;

/**
 * Created by twilight on 09.05.17.
 */
public class ResponseDtoCreator {

    public static OutgoingDto getResponseDto(Payload payload, ServiceInformation service){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setService(service);
        responseDto.setPayload(payload);
        return responseDto;
    }

    public static OutgoingDto getResponseDto(ServiceInformation service){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setService(service);
        return responseDto;
    }
}

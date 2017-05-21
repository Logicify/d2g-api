package com.logicify.d2g.dtos.domain.dtos;

import com.logicify.d2g.dtos.domain.outgoingdtos.ResponseDto;
import com.logicify.d2g.exceptions.D2GBaseException;

/**
 * Created by twilight on 09.05.17.
 */
public class ResponseDtoBuilder {

    public static OutgoingDto getResponseDto(D2GBaseException d2g){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setService(new ServiceInformation(d2g));
        return responseDto;
    }

    public static OutgoingDto getResponseDto(Exception e){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setService(new ServiceInformation(e));
        return responseDto;
    }

    public static OutgoingDto getResponseDto(){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setService(new ServiceInformation());
        return responseDto;
    }

    public static OutgoingDto getResponseDto(Payload payload){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setService(new ServiceInformation());
        responseDto.setPayload(payload);
        return responseDto;
    }
}

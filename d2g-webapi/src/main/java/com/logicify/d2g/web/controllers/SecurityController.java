package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.ServiceInformation;
import com.logicify.d2g.dtos.domain.outgoingdtos.ResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by jadencorr on 06.03.17.
 */

@RestController
public class SecurityController {

    @RequestMapping(path = "/restore-session", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDto getAuthUserData(Principal user) {

        //TODO catch D2GBaseException
        try {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation());
            return response;
        } catch (Exception e) {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation(e));
            return response;
        }
    }
}
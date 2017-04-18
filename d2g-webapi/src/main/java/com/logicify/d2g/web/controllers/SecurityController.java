package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformation;
import com.logicify.d2g.dtos.domain.incomingdtos.securitysincomingdtos.UserLoginIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.ResponseDto;
import com.logicify.d2g.models.exceptions.D2GBaseException;
import com.logicify.d2g.services.UserDetailsServiceImpl;
import com.logicify.d2g.utils.DtoValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by jadencorr on 06.03.17.
 */

@RestController
public class SecurityController {

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(path = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    private OutgoingDto UserLogin(@RequestBody UserLoginIncomingDto dto){
        try {
            DtoValidator.validate(dto);
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword());
            context.setAuthentication(authentication);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setService(new ServiceInformation());
            return responseDto;
        } catch (D2GBaseException e) {
            e.printStackTrace();
            return new ResponseDto();
        }
    }

    @RequestMapping(path = "/user/logout", method = RequestMethod.GET)
    @ResponseBody
    private OutgoingDto UserLogout(){
        return null;
    }

    @RequestMapping(path = "/user/session", method = RequestMethod.GET)
    @ResponseBody
    private OutgoingDto UserRestoreSession(){
        return null;

    }
}

package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformation;
import com.logicify.d2g.dtos.domain.incomingdtos.securitysincomingdtos.UserLoginIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.ResponseDto;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.services.UserService;
import com.logicify.d2g.utils.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by jadencorr on 06.03.17.
 */

@RestController
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(path = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public OutgoingDto userLogin(@RequestBody UserLoginIncomingDto dto) {
        try {
            DtoValidator.validate(dto);
            Authentication authentication = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
            Authentication auth = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(auth);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setService(new ServiceInformation());
            //TODO: refactor method's name
            responseDto.setPayload(userService.findUserDTO(dto.getEmail()));
            return responseDto;
        } catch (D2GBaseException e) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setService(new ServiceInformation(e));
            return responseDto;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/user/logout", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto userLogout() {
        SecurityContextHolder.clearContext();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setService(new ServiceInformation());
        return responseDto;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/user/session", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto userRestoreSession(Principal principal) {
        String login = principal.getName();
        ResponseDto responseDto = new ResponseDto();
        responseDto.setService(new ServiceInformation());
        responseDto.setPayload(userService.findUserDTO(principal.getName()));
        return responseDto;
    }
}

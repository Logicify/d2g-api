package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ResponseDtoBuilder;
import com.logicify.d2g.dtos.domain.incomingdtos.securitysincomingdtos.UserLoginIncomingDto;
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
            return ResponseDtoBuilder.getResponseDto(userService.findUserDTO(dto.getEmail()));
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/user/logout", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto userLogout() {
        try {
            SecurityContextHolder.clearContext();
            return ResponseDtoBuilder.getResponseDto();
        }
        catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/user/session", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto userRestoreSession(Principal principal) {
        try {
            String login = principal.getName();
            return ResponseDtoBuilder.getResponseDto(userService.findUserDTO(principal.getName()));
        }
        catch (D2GBaseException d2g){
            return ResponseDtoBuilder.getResponseDto(d2g);
        }
        catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }
}

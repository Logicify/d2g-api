package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserUpdatePasswordIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserUpdateStatusIncomingDto;
import com.logicify.d2g.dtos.domain.dtos.ResponseDtoBuilder;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.services.UserService;
import com.logicify.d2g.utils.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

/**
 * @author knorr
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseBody
    public OutgoingDto createUser(@RequestBody UserCreateIncomingDto userCreateIncomingDto) {
        try {
            DtoValidator.validate(userCreateIncomingDto);
            userService.createUser(userCreateIncomingDto);
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getAllUsers() {
        try {
            return ResponseDtoBuilder.getResponseDto(userService.findAll());
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getUserById(@PathVariable("id") UUID id) {
        try {
            return ResponseDtoBuilder.getResponseDto(userService.findOne(id));
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public OutgoingDto deleteUser(@PathVariable("id") UUID id) {
        try {
            userService.delete(id);
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto updateUser(@PathVariable("id") UUID id,
                                  @RequestBody UserUpdateIncomingDto userUpdateIncomingDto,
                                  Principal principal) {
        try {
            DtoValidator.validate(userUpdateIncomingDto);
            userService.updateUser(id, userUpdateIncomingDto, principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/user/{id}/status", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto updateUsetStatus(@PathVariable("id") UUID id,
                                        @RequestBody UserUpdateStatusIncomingDto userUpdateStatusIncomingDto) {
        try {
            DtoValidator.validate(userUpdateStatusIncomingDto);
            userService.updateStatus(id, userUpdateStatusIncomingDto);
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/user/current", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto updateCurrentUser(@RequestBody UserUpdateIncomingDto incomingDto,
                                         Principal principal) {
        try {
            userService.updateCurrentUser(incomingDto, principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        }
        catch (D2GBaseException d2g){
            return ResponseDtoBuilder.getResponseDto(d2g);
        }catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/user/current/password", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto updateCurrentUserPassword(@RequestBody UserUpdatePasswordIncomingDto incomingDto,
                                                 Principal principal){
        try {
            DtoValidator.validate(incomingDto);
            userService.updateCurrentUserPassword(incomingDto,principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        }
        catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }
}



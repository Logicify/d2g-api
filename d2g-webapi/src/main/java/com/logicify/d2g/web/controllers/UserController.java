package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformationDto;
import com.logicify.d2g.dtos.domain.exceptions.ControllerException;
import com.logicify.d2g.dtos.incomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.dtos.incomingdtos.UserUpdateIncomingDto;
import com.logicify.d2g.dtos.incomingdtos.UserUpdateStatusIncomingDto;
import com.logicify.d2g.dtos.outgoingdtos.ErrorOutgoingDto;
import com.logicify.d2g.dtos.outgoingdtos.SuccessOutgoingDto;
import com.logicify.d2g.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author knorr
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseBody
    private OutgoingDto createUser(@RequestBody UserCreateIncomingDto userCreateIncomingDto) {
        try {
            userService.createUser(userCreateIncomingDto);
            SuccessOutgoingDto successOutgoingDto = new SuccessOutgoingDto();
            successOutgoingDto.setService(new ServiceInformationDto());
            return successOutgoingDto;
        } catch (ControllerException e) {
            ErrorOutgoingDto errorOutgoingDto = new ErrorOutgoingDto();
            errorOutgoingDto.setService(new ServiceInformationDto(e));
            return errorOutgoingDto;
        }
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    @ResponseBody
    private OutgoingDto findAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    private OutgoingDto FindUserById(@PathVariable("id") UUID id) {
        try {
            return userService.findOne(id);
        } catch (ControllerException e) {
            ErrorOutgoingDto errorOutgoingDto = new ErrorOutgoingDto();
            errorOutgoingDto.setService(new ServiceInformationDto(e));
            return errorOutgoingDto;
        }
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    private OutgoingDto deleteUser(@PathVariable("id") UUID id) {
        try {
            userService.delete(id);
            SuccessOutgoingDto successOutgoingDto = new SuccessOutgoingDto();
            successOutgoingDto.setService(new ServiceInformationDto());
            return successOutgoingDto;
        } catch (ControllerException e) {
            ErrorOutgoingDto errorOutgoingDto = new ErrorOutgoingDto();
            errorOutgoingDto.setService(new ServiceInformationDto(e));
            return errorOutgoingDto;
        }

    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    private OutgoingDto updateUser(@PathVariable("id") UUID id,
                                   @RequestBody UserUpdateIncomingDto userUpdateIncomingDto) {
        try {
            userService.updateUser(id, userUpdateIncomingDto);
            SuccessOutgoingDto successOutgoingDto = new SuccessOutgoingDto();
            successOutgoingDto.setService(new ServiceInformationDto());
            return successOutgoingDto;
        } catch (ControllerException e) {
            ErrorOutgoingDto errorOutgoingDto = new ErrorOutgoingDto();
            errorOutgoingDto.setService(new ServiceInformationDto(e));
            return errorOutgoingDto;
        }
    }

    @RequestMapping(path = "/user/{id}/status", method = RequestMethod.PUT)
    @ResponseBody
    private OutgoingDto UpdateUsetStatus(@PathVariable("id") UUID id,
                                         @RequestBody UserUpdateStatusIncomingDto userUpdateStatusIncomingDto) {
        userService.updateStatus(id,userUpdateStatusIncomingDto);
        return new SuccessOutgoingDto();
    }


}



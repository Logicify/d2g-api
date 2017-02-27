package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.DtosDomains.OutgoingDto;
import com.logicify.d2g.dtos.incomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.dtos.incomingdtos.UserUpdateIncomingDto;
import com.logicify.d2g.dtos.incomingdtos.UserUpdateStatusIncomingDto;
import com.logicify.d2g.dtos.outgoingdtos.ErrorOutgoingDto;
import com.logicify.d2g.dtos.outgoingdtos.MessageOutgoingDto;
import com.logicify.d2g.services.UserService;
import com.logicify.d2g.utils.PasswordStorage;
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
    //TODO: Should we use ResponseEntity<Dto> here instead of just Dto?
    private OutgoingDto createUser(@RequestBody UserCreateIncomingDto userCreateIncomingDto) {
        try {
            userService.createUser(userCreateIncomingDto);
            return new MessageOutgoingDto("User created successfully");
            //TODO: Use custom exception for expected errors
        } catch (PasswordStorage.CannotPerformOperationException e) {
            return new ErrorOutgoingDto("Wrong Password");
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
        return userService.findOne(id);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    private OutgoingDto deleteUser(@PathVariable("id") UUID id) {
        userService.delete(id);
        return new MessageOutgoingDto("User deleted successfully");
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    private OutgoingDto updateUser(@PathVariable("id") UUID id,
                                   @RequestBody UserUpdateIncomingDto userUpdateIncomingDto) {
        try {
            userService.updateUser(id, userUpdateIncomingDto);
        } catch (PasswordStorage.CannotPerformOperationException e) {
            return new ErrorOutgoingDto("Wrong Password");
        }
        return new MessageOutgoingDto(String.format("User with %s updated successfully", id.toString()));
    }

    @RequestMapping(path = "/user/{id}/status", method = RequestMethod.PUT)
    @ResponseBody
    private OutgoingDto UpdateUsetStatus(@PathVariable("id") UUID id,
                                         @RequestBody UserUpdateStatusIncomingDto userUpdateStatusIncomingDto) {
        userService.updateStatus(id,userUpdateStatusIncomingDto);
        return new MessageOutgoingDto("Status updated successfully");
    }


}



package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.incomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.dtos.incomingdtos.UserUpdateIncomingDto;
import com.logicify.d2g.dtos.outgoingdtos.OutgoingDto;
import com.logicify.d2g.services.UserService;
import com.logicify.d2g.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
    private OutgoingDto createUser(@RequestBody UserCreateIncomingDto userCreateIncomingDto){
        try {
            userService.createUser(userCreateIncomingDto);
            //TODO: Use custom exception for expected errors
        } catch (PasswordStorage.CannotPerformOperationException e) {
            return new OutgoingDto(); //TODO: change to dto
        }
        catch (Exception e){
            return new OutgoingDto(); //TODO: change to dto
        }
        return new OutgoingDto(); //TODO: change to dto
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    @ResponseBody
    private List<OutgoingDto> findAllUsers(){
        return new ArrayList<>();
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    @ResponseBody
    private OutgoingDto FindUserById(@PathVariable("id") UUID id){
        return new OutgoingDto();
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    private OutgoingDto deleteUser(@PathVariable("id") UUID id){
        return new OutgoingDto();
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    private OutgoingDto updateUser(@PathVariable("id")  UUID id, @RequestBody UserUpdateIncomingDto userUpdateIncomingDto){
        return new OutgoingDto();
    }

    @RequestMapping(path = "/user/{id}/status", method = RequestMethod.PUT)
    @ResponseBody
    private OutgoingDto UpdateUsetStatus(@PathVariable("id") UUID id, @RequestBody UserUpdateIncomingDto userUpdateIncomingDto){
       return new OutgoingDto();
    }




}



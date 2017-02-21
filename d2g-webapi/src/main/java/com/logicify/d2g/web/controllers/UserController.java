package com.logicify.d2g.web.controllers;

import com.logicify.d2g.domain.User;
import com.logicify.d2g.domain.UserStatus;
import com.logicify.d2g.models.UserImpl;
import com.logicify.d2g.services.UserService;
import com.logicify.d2g.utils.PasswordStorage;
import com.logicify.dtos.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author knorr
 */
@RestController
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    @ResponseBody
    public List<UserImpl> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("id") UUID id) {
        return userService.findById(id);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUserByID(id);
        return "User successfully deleted";
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String createUser(@RequestBody UserDto userDto) {
        try {
            userDto.setPasswordhash(PasswordStorage.createHash(userDto.getPassword()));
        } catch (PasswordStorage.CannotPerformOperationException e) {
            e.printStackTrace();
        }
        UserImpl user = modelMapper.map(userDto, UserImpl.class);
        user.setCreatedBy(user);
        user.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        userService.createUser(user);
        return "User successfully created";
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String updateUser(@RequestBody UserDto userDto, @PathVariable("id") UUID id) {
        try {
            userDto.setPasswordhash(PasswordStorage.createHash(userDto.getPassword()));
        UserImpl user = userService.findById(id);
        user.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        user.setUpdatedBy(user);
        userService.saveUser(user);
        return "User updated successfully";
        } catch (PasswordStorage.CannotPerformOperationException e) {
            e.printStackTrace();
            return "User updated unsuccessfully";
        }
    }

    @RequestMapping(path = "/user/{id}/status")
    @ResponseBody
    public String updateUserStatus(@PathVariable("id") UUID id,@RequestBody UserStatus status){
        UserImpl user = userService.findById(id);
        user.setStatus(status);
        user.setUpdatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        user.setUpdatedBy(user);
        userService.saveUser(user);
        return "Status updated successfully";
    }




}

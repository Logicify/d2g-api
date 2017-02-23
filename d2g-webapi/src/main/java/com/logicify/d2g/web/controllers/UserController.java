package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.incomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.services.UserService;
import com.logicify.d2g.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private String createUser(@RequestBody UserCreateIncomingDto userCreateIncomingDto){
        try {
            userService.createUser(userCreateIncomingDto);
            //TODO: Use custom exception for expected errors
        } catch (PasswordStorage.CannotPerformOperationException e) {
            return "Password denied"; //TODO: change to dto
        }
        catch (Exception e){
            return "Something went wrong"; //TODO: change to dto
        }
        return "User created successfully"; //TODO: change to dto
    }


}



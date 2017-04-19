package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformation;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserUpdateStatusIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.ResponseDto;
import com.logicify.d2g.models.exceptions.D2GBaseException;
import com.logicify.d2g.services.UserService;
import com.logicify.d2g.utils.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    private OutgoingDto createUser(@RequestBody UserCreateIncomingDto userCreateIncomingDto) {
        try {
            DtoValidator.validate(userCreateIncomingDto);
            userService.createUser(userCreateIncomingDto);
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation());
            return response;
        } catch (D2GBaseException e) {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation(e));
            return response;
        } catch (Exception e) {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation(e));
            return response;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto findAllUsers() {
        try {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation());
            response.setPayload(userService.findAll());
            return response;
        } catch (Exception e) {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation(e));
            return response;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto FindUserById(@PathVariable("id") UUID id) {
        try {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation());
            response.setPayload(userService.findOne(id));
            return response;
        } catch (D2GBaseException e) {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation(e));
            return response;
        } catch (Exception e) {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation(e));
            return response;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public OutgoingDto deleteUser(@PathVariable("id") UUID id) {
        try {
            userService.delete(id);
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation());
            return response;
        } catch (D2GBaseException e) {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation(e));
            return response;
        } catch (Exception e) {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation(e));
            return response;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto updateUser(@PathVariable("id") UUID id,
                                   @RequestBody UserUpdateIncomingDto userUpdateIncomingDto) {
        try {
            DtoValidator.validate(userUpdateIncomingDto);
            userService.updateUser(id, userUpdateIncomingDto);
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation());
            return response;
        } catch (D2GBaseException e) {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation(e));
            return response;
        } catch (Exception e) {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation(e));
            return response;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/user/{id}/status", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto UpdateUsetStatus(@PathVariable("id") UUID id,
                                         @RequestBody UserUpdateStatusIncomingDto userUpdateStatusIncomingDto) {
        try {
            DtoValidator.validate(userUpdateStatusIncomingDto);
            userService.updateStatus(id, userUpdateStatusIncomingDto);
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation());
            return response;
        } catch (D2GBaseException e) {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation(e));
            return response;
        } catch (Exception e) {
            ResponseDto response = new ResponseDto();
            response.setService(new ServiceInformation(e));
            return response;
        }
    }
}



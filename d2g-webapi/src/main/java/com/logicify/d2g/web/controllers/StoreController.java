package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ServiceInformation;
import com.logicify.d2g.dtos.domain.incomingdtos.storeincomingdtos.StoreCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.storeincomingdtos.StoreUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.userincomingdtos.UserCreateIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.ResponseDtoCreator;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.services.StoreService;
import com.logicify.d2g.utils.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

/**
 * Created by twilight on 09.05.17.
 */

@RestController
@CrossOrigin
public class StoreController {

    @Autowired
    private
    StoreService storeService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/store", method = RequestMethod.POST)
    @ResponseBody
    public OutgoingDto createStore(@RequestBody StoreCreateIncomingDto incomingDto, Principal principal){
        try {
            DtoValidator.validate(incomingDto);
            storeService.createStore(incomingDto,principal.getName());
            return ResponseDtoCreator.getResponseDto(new ServiceInformation());
        }
        catch (D2GBaseException d2g){
            return ResponseDtoCreator.getResponseDto(new ServiceInformation(d2g));
        }
        catch (Exception e){
            return ResponseDtoCreator.getResponseDto(new ServiceInformation(e));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/store", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getAllStores(){
        try{
            return ResponseDtoCreator.getResponseDto(storeService.getStores(),new ServiceInformation());
        } catch (Exception e){
            return ResponseDtoCreator.getResponseDto(new ServiceInformation(e));
        }

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/store/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getStoreById(@PathVariable("id")UUID id){
        try{
            return ResponseDtoCreator.getResponseDto(storeService.getStore(id),new ServiceInformation());
        }
        catch (D2GBaseException d2g){
            return ResponseDtoCreator.getResponseDto(new ServiceInformation(d2g));
        }
        catch (Exception e){
            return ResponseDtoCreator.getResponseDto(new ServiceInformation(e));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/store/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto updateStore(@RequestBody StoreUpdateIncomingDto incomingDto,
                                   @PathVariable("id") UUID id,
                                   Principal principal){
        try{
            DtoValidator.validate(incomingDto);
            storeService.updateStore(incomingDto,id,principal.getName());
            return ResponseDtoCreator.getResponseDto(new ServiceInformation());
        }
        catch (D2GBaseException d2g){
            return ResponseDtoCreator.getResponseDto(new ServiceInformation(d2g));
        }
        catch (Exception e){
            return ResponseDtoCreator.getResponseDto(new ServiceInformation(e));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/store/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public OutgoingDto deleteStore(@PathVariable("id") UUID id){
        try{
            storeService.deleteStore(id);
            return ResponseDtoCreator.getResponseDto(new ServiceInformation());
        }
        catch (D2GBaseException d2g){
            return ResponseDtoCreator.getResponseDto(new ServiceInformation(d2g));
        }
        catch (Exception e){
            return ResponseDtoCreator.getResponseDto(new ServiceInformation(e));
        }

    }
}

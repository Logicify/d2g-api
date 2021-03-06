package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.storeincomingdtos.StoreCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.storeincomingdtos.StoreUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.dtos.ResponseDtoBuilder;
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
    public OutgoingDto createStore(@RequestBody StoreCreateIncomingDto incomingDto, Principal principal) {
        try {
            DtoValidator.validate(incomingDto);
            storeService.createStore(incomingDto, principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/store", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getAllStores() {
        try {
            return ResponseDtoBuilder.getResponseDto(storeService.getStores());
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/store/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getStoreById(@PathVariable("id") UUID id) {
        try {
            return ResponseDtoBuilder.getResponseDto(storeService.getStoreDtoById(id));
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/store/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto updateStore(@RequestBody StoreUpdateIncomingDto incomingDto,
                                   @PathVariable("id") UUID id,
                                   Principal principal) {
        try {
            DtoValidator.validate(incomingDto);
            storeService.updateStore(incomingDto, id, principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/store/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public OutgoingDto deleteStore(@PathVariable("id") UUID id) {
        try {
            storeService.deleteStore(id);
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/store/by-name", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto findStoresByName(@RequestParam("name") String name){
        try {
            return ResponseDtoBuilder.getResponseDto(storeService.findByNameContain(name));
        }
        catch (D2GBaseException d2g){
            return ResponseDtoBuilder.getResponseDto(d2g);
        }
        catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }
}

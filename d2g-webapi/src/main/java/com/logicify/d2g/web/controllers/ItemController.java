package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ResponseDtoBuilder;
import com.logicify.d2g.dtos.domain.incomingdtos.itemincomingdtos.ItemCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.itemincomingdtos.ItemUpdateIncomingDto;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.services.ItemService;
import com.logicify.d2g.utils.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

/**
 * Created by twilight on 11.05.17.
 */
@RestController
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/item", method = RequestMethod.POST)
    @ResponseBody
    public OutgoingDto createItem(@RequestBody ItemCreateIncomingDto incomingDto, Principal principal) {
        try {
            DtoValidator.validate(incomingDto);
            itemService.createItem(incomingDto, principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/item", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getAllItems() {
        try {
            return ResponseDtoBuilder.getResponseDto(itemService.getAllItems());
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/item/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getItemById(@PathVariable("id") UUID id) {
        try {
            return ResponseDtoBuilder.getResponseDto(itemService.getItemDtoById(id));
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/item/by-name", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getItemByName(@RequestParam("name") String name) {
        try {
            return ResponseDtoBuilder.getResponseDto(itemService.getItemsByName(name));
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/item/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto updateItemById(@PathVariable("id") UUID id,
                                      @RequestBody ItemUpdateIncomingDto incomingDto,
                                      Principal principal) {
        try {
            itemService.updateItemById(incomingDto, id, principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "item/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public OutgoingDto deleteItemById(@PathVariable("id") UUID id) {
        try {
            itemService.deleteItemById(id);
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

}

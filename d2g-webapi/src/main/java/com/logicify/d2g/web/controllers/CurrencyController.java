package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ResponseDtoBuilder;
import com.logicify.d2g.dtos.domain.incomingdtos.currencyincomingdtos.CurrencyUpdateIncomingDto;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.services.CurrencyService;
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
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/currency", method = RequestMethod.POST)
    @ResponseBody
    public OutgoingDto createCurrency(@RequestBody CurrencyUpdateIncomingDto incomingDto,
                                      Principal principal) {
        try {
            DtoValidator.validate(incomingDto);
            currencyService.createCurrency(incomingDto, principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/currency", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getAllCurrency() {
        try {
            return ResponseDtoBuilder.getResponseDto(currencyService.getAllCurrencies());
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/currency/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getCurrencyById(@PathVariable("id") UUID id) {
        try {
            return ResponseDtoBuilder.getResponseDto(currencyService.getCurrencyDtoById(id));
        }
        catch (D2GBaseException d2g){
            return ResponseDtoBuilder.getResponseDto(d2g);
        }catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/currency/by-name", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getCurrencyByName(@RequestParam(value = "name") String name) {
        try {
            return ResponseDtoBuilder.getResponseDto(currencyService.getCurrenciesByName(name));
        }
        catch (D2GBaseException d2g){
            return ResponseDtoBuilder.getResponseDto(d2g);
        }
        catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/currency/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto updateCurrencyById(@RequestBody CurrencyUpdateIncomingDto incomingDto,
                                          @PathVariable("id") UUID id,
                                          Principal principal) {
        try {
            DtoValidator.validate(incomingDto);
            currencyService.updateCurrencyById(incomingDto,id,principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        }
        catch (D2GBaseException d2g){
            return ResponseDtoBuilder.getResponseDto(d2g);
        }
        catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/currency/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public OutgoingDto deleteCurrencyById(@PathVariable("id") UUID id) {
        try{
            currencyService.deleteCurrencyById(id);
            return ResponseDtoBuilder.getResponseDto();
        }
        catch (D2GBaseException d2g){
            return ResponseDtoBuilder.getResponseDto(d2g);
        }
        catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

}

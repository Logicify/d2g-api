package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ResponseDtoBuilder;
import com.logicify.d2g.dtos.domain.incomingdtos.purchaseditemincomingdtos.PurchasedItemCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.purchaseditemincomingdtos.PurchasedItemUpdateIncomingDto;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.services.PurchasedItemService;
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
public class PurchasedItemController {

    @Autowired
    private PurchasedItemService purchasedItemService;

    @PreAuthorize("isAuthenticated()")
        @RequestMapping(path = "/purchased", method = RequestMethod.POST)
    @ResponseBody
    public OutgoingDto createPurchasedItem(@RequestBody PurchasedItemCreateIncomingDto incomingDto, Principal principal) {
        try {
            purchasedItemService.createPurchasedItem(incomingDto, principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/purchased", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getPurchasedItems() {
        try {
            return ResponseDtoBuilder.getResponseDto(purchasedItemService.getPurchasedItems());
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/purchased/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getPurchasedItemsById(@PathVariable("id") UUID id) {
        try {
            return ResponseDtoBuilder.getResponseDto(purchasedItemService.getPurchasedItemDtoById(id));
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/purchased/current", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getCurrentUserPurchasedItems(Principal principal) {
        try {
            return ResponseDtoBuilder.getResponseDto(purchasedItemService.getCurrentUserPurchasedItems(principal.getName()));
        } catch (D2GBaseException e) {
            return ResponseDtoBuilder.getResponseDto(e);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/purchased/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto updatePurchasedItemById(@PathVariable("id") UUID id,
                                               @RequestBody PurchasedItemUpdateIncomingDto incomingDto,
                                               Principal principal) {
        try {
            DtoValidator.validate(incomingDto);
            purchasedItemService.updatePurchasedItemById(id, incomingDto, principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException e) {
            return ResponseDtoBuilder.getResponseDto(e);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/purchased/current/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto updateCurrentUserPurchasedItemById(@PathVariable("id") UUID id,
                                                          @RequestBody PurchasedItemUpdateIncomingDto incomingDto,
                                                          Principal principal) {
        try {
            DtoValidator.validate(incomingDto);
            purchasedItemService.updateCurrentUserPurchasedItemById(id, incomingDto, principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException e) {
            return ResponseDtoBuilder.getResponseDto(e);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/purchased/{id}")
    @ResponseBody
    public OutgoingDto deletePurchasedItemById(@PathVariable("id") UUID id) {
        try {
            purchasedItemService.deletePurchasedItemById(id);
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g){
            return ResponseDtoBuilder.getResponseDto(d2g);
        }
        catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/purchased/current/by-item", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto findCurrentUserPurchasedItemByItem(@RequestParam(name = "item") UUID item,Principal principal){
        try {
            return ResponseDtoBuilder.getResponseDto(purchasedItemService.findAllPurchasedItemByItem(item,principal.getName()));
        } catch (D2GBaseException e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
        catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/purchased/current/by-last-month", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto findCurrentUserPurchasedItemsForLastMonth(Principal principal){
        try {
            return ResponseDtoBuilder.getResponseDto(purchasedItemService.findCurrentUserPurchasedItemsForLastMonth(principal.getName()));
        } catch (D2GBaseException e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
        catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/purchased/current/expenses", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto findCurrentUserExpenses(Principal principal){
        try {
            return ResponseDtoBuilder.getResponseDto(purchasedItemService.getCurrentUserExpenses(principal.getName()));
        } catch (D2GBaseException e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
        catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/purchased/current/expenses/by-last-month", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto findCurrentUserExpensesForLastMonth(Principal principal){
        try {
            return ResponseDtoBuilder.getResponseDto(purchasedItemService.getCurrentUserExpensesForLastMonth(principal.getName()));
        } catch (D2GBaseException e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
        catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }
}

package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.purchaseditemincomingdtos.PurchasedItemCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.purchaseditemincomingdtos.PurchasedItemUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.purchaseditempayload.PurchasedItemPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.purchaseditempayload.PurchasedItemsExpensesPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.purchaseditempayload.PurchasedItemsListPayload;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.interfaces.PurchasedItem;

import java.util.UUID;

/**
 * Created by twilight on 11.05.17.
 */
public interface PurchasedItemService {

    void createPurchasedItem(PurchasedItemCreateIncomingDto incomingDto, String email) throws D2GBaseException;

    PurchasedItemsListPayload getPurchasedItems() throws D2GBaseException;

    PurchasedItemPayload getPurchasedItemDtoById(UUID id) throws D2GBaseException;

    PurchasedItemsListPayload getCurrentUserPurchasedItems(String name) throws D2GBaseException;

    void updatePurchasedItemById(UUID id, PurchasedItemUpdateIncomingDto incomingDto, String email) throws D2GBaseException;

    PurchasedItem getPurchasedItemById(UUID id) throws D2GBaseException;

    void updateCurrentUserPurchasedItemById(UUID id, PurchasedItemUpdateIncomingDto incomingDto, String email) throws D2GBaseException;

    void deletePurchasedItemById(UUID id) throws D2GBaseException;

    PurchasedItemsListPayload findAllPurchasedItemByItem(UUID item, String name) throws D2GBaseException;

    PurchasedItemsListPayload findCurrentUserPurchasedItemsForLastMonth(String email) throws D2GBaseException;

    PurchasedItemsExpensesPayload getCurrentUserExpenses(String email) throws D2GBaseException;

    PurchasedItemsExpensesPayload getCurrentUserExpensesForLastMonth(String email) throws D2GBaseException;
}

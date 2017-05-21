package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.itemincomingdtos.ItemCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.itemincomingdtos.ItemUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.itempayload.ItemPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.itempayload.ItemsListPayload;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.interfaces.Item;

import java.util.UUID;

/**
 * Created by twilight on 11.05.17.
 */
public interface ItemService {

    void createItem(ItemCreateIncomingDto incomingDto, String email) throws D2GBaseException;

    ItemsListPayload getAllItems();

    ItemPayload getItemDtoById(UUID id) throws D2GBaseException;

    ItemsListPayload getItemsByName(String name) throws D2GBaseException;

    void updateItemById(ItemUpdateIncomingDto incomingDto, UUID id, String email) throws D2GBaseException;

    void deleteItemById(UUID id) throws D2GBaseException;

    Item getItemById(UUID id) throws D2GBaseException;
}

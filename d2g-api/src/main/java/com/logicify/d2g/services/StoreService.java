package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.storeincomingdtos.StoreCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.storeincomingdtos.StoreUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.storepayload.StorePayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.storepayload.StoresListPayload;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.interfaces.Store;

import java.util.UUID;

/**
 * Created by twilight on 09.05.17.
 */
public interface StoreService {

    public void createStore(StoreCreateIncomingDto incomingDto, String email) throws D2GBaseException;

    public StoresListPayload getStores();

    StorePayload getStoreDtoById(UUID id) throws D2GBaseException;

    void updateStore(StoreUpdateIncomingDto incomingDto, UUID id, String email) throws D2GBaseException;

    void deleteStore(UUID id) throws D2GBaseException;

    StoresListPayload findByNameContain(String name) throws D2GBaseException;

    Store getStoreById(UUID store) throws D2GBaseException;
}

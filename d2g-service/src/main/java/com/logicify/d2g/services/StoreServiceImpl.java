package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.storeincomingdtos.StoreCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.storeincomingdtos.StoreUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.storepayload.StorePayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.storepayload.StoresListPayload;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.exceptions.NewD2GBaseExceptionCodes;
import com.logicify.d2g.interfaces.Store;
import com.logicify.d2g.interfaces.User;
import com.logicify.d2g.models.implementations.StoreImpl;
import com.logicify.d2g.repositories.StoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by twilight on 09.05.17.
 */

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void createStore(StoreCreateIncomingDto incomingDto, String email) throws D2GBaseException {
        User user = userService.findUserByEmail(email);
        if (user == null) throw new D2GBaseException(NewD2GBaseExceptionCodes.USER_NOT_EXIST);
        StoreImpl store = modelMapper.map(incomingDto,StoreImpl.class);
        store.setCreatedBy(user);
        store.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        storeRepository.save(store);
    }

    @Override
    public StoresListPayload getStores() {
        List<StorePayload> storePayload = new ArrayList<>();
        List<StoreImpl> stores = new ArrayList<>();
        storeRepository.findAll().forEach(stores::add);
        stores.forEach(store -> storePayload.add(modelMapper.map(store,StorePayload.class)));
        StoresListPayload storesListPayload = new StoresListPayload();
        storesListPayload.setStoresList(storePayload);
        return storesListPayload;
    }

    @Override
    public StorePayload getStoreDtoById(UUID id) throws D2GBaseException {
        if (!storeRepository.exists(id)) throw new D2GBaseException(NewD2GBaseExceptionCodes.STORE_NOT_EXIST);
        return modelMapper.map(storeRepository.findOne(id),StorePayload.class);
    }

    @Override
    @Transactional
    public void updateStore(StoreUpdateIncomingDto incomingDto, UUID id, String email) throws D2GBaseException {
        if (!storeRepository.exists(id)) throw new D2GBaseException(NewD2GBaseExceptionCodes.STORE_NOT_EXIST);
        User user = userService.findUserByEmail(email);
        if (user == null) throw new D2GBaseException(NewD2GBaseExceptionCodes.USER_NOT_EXIST);
        StoreImpl store = storeRepository.findOne(id);
        if (incomingDto.getName()!=null) store.setName(incomingDto.getName());
        if (incomingDto.getLocation()!=null) store.setLocation(incomingDto.getLocation());
        store.setVersion(store.getVersion()+1);
        store.setUpdatedBy(user);
        store.setUpdatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        storeRepository.save(store);
    }

    @Override
    public void deleteStore(UUID id) throws D2GBaseException {
        if (!storeRepository.exists(id)) throw new D2GBaseException(NewD2GBaseExceptionCodes.STORE_NOT_EXIST);
        storeRepository.delete(id);
    }

    @Override
    public StoresListPayload findByNameContain(String name) throws D2GBaseException {
        List<StoreImpl> stores = storeRepository.findByNameContaining(name);
        if (stores.isEmpty()) throw new D2GBaseException(NewD2GBaseExceptionCodes.STORE_WITH_THIS_NAME_NOT_EXIST);
        List<StorePayload> storePayloads = new ArrayList<>();
        stores.forEach(store -> storePayloads.add(modelMapper.map(store,StorePayload.class)));
        StoresListPayload response = new StoresListPayload();
        response.setStoresList(storePayloads);
        return response;
    }

    @Override
    public Store getStoreById(UUID store) throws D2GBaseException {
        if (!storeRepository.exists(store)) throw new D2GBaseException(NewD2GBaseExceptionCodes.STORE_NOT_EXIST);
        return storeRepository.findOne(store);
    }
}

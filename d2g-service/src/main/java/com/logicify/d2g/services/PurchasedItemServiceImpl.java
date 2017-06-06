package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.purchaseditemincomingdtos.PurchasedItemCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.purchaseditemincomingdtos.PurchasedItemUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.currencypayload.CurrencyPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.purchaseditempayload.PurchasedItemPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.purchaseditempayload.PurchasedItemsExpensesPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.purchaseditempayload.PurchasedItemsListPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.storepayload.StorePayload;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.exceptions.D2GBaseExceptionCodes;
import com.logicify.d2g.interfaces.Item;
import com.logicify.d2g.interfaces.PurchasedItem;
import com.logicify.d2g.interfaces.User;
import com.logicify.d2g.models.implementations.PurchasedItemImpl;
import com.logicify.d2g.repositories.PurchasedItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by twilight on 11.05.17.
 */

@Service
public class PurchasedItemServiceImpl implements PurchasedItemService {

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PurchasedItemRepository purchasedItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    @Transactional
    public void createPurchasedItem(PurchasedItemCreateIncomingDto incomingDto, String email) throws D2GBaseException {

        User user = userService.findUserByEmail(email);
        PurchasedItemImpl purchasedItem = modelMapper.map(incomingDto, PurchasedItemImpl.class);
        purchasedItem.setItem(itemService.getItemById(incomingDto.getItem()));
        purchasedItem.setCurrency(currencyService.getCurrencyById(incomingDto.getCurrency()));
        purchasedItem.setStore(storeService.getStoreById(incomingDto.getStore()));
        purchasedItem.setOwner(user);
        purchasedItem.setCreatedBy(user);
        LocalDate localDate = LocalDate.parse(incomingDto.getDateOfPurchase(), dateTimeFormatter);
        purchasedItem.setDateOfPurchase(ZonedDateTime.of(localDate, LocalTime.MIDNIGHT, ZoneOffset.UTC));
        purchasedItem.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        purchasedItemRepository.save(purchasedItem);

    }

    @Override
    public PurchasedItemsListPayload getPurchasedItems() throws D2GBaseException {
        List<PurchasedItemImpl> purchasedItemList = new ArrayList<>();
        purchasedItemRepository.findAll().forEach(purchasedItemList::add);
        List<PurchasedItemPayload> itemPayloads = new ArrayList<>();
        for (PurchasedItemImpl purchasedItem : purchasedItemList) {
            PurchasedItemPayload payload = new PurchasedItemPayload();
            payload.setId(purchasedItem.getId());
            payload.setOwner(purchasedItem.getOwner().getId());
            payload.setAmount(purchasedItem.getAmount());
            payload.setPrice(purchasedItem.getPrice());
            payload.setCurrency(modelMapper.map(purchasedItem.getCurrency(), CurrencyPayload.class));
            payload.setStore(modelMapper.map(purchasedItem.getStore(), StorePayload.class));
            Item item = purchasedItem.getItem();
            payload.setItem(itemService.getItemDtoById(item.getId()));
            payload.setDateOfPurchase(purchasedItem.getDateOfPurchase().format(dateTimeFormatter));
            itemPayloads.add(payload);
        }
        PurchasedItemsListPayload response = new PurchasedItemsListPayload();
        response.setPurchasedItem(itemPayloads);
        return response;
    }

    @Override
    public PurchasedItemPayload getPurchasedItemDtoById(UUID id) throws D2GBaseException {
        if (!purchasedItemRepository.exists(id)) throw
                new D2GBaseException(D2GBaseExceptionCodes.PURCHASED_ITEM_WITH_THIS_ID_NOT_EXIST);
        PurchasedItemImpl purchasedItem = purchasedItemRepository.findOne(id);
        PurchasedItemPayload payload = modelMapper.map(purchasedItem, PurchasedItemPayload.class);
        payload.setItem(itemService.getItemDtoById(purchasedItem.getItem().getId()));
        payload.setStore(modelMapper.map(purchasedItem.getStore(), StorePayload.class));
        payload.setCurrency(modelMapper.map(purchasedItem.getCurrency(), CurrencyPayload.class));
        payload.setOwner(purchasedItem.getOwner().getId());
        payload.setDateOfPurchase(purchasedItem.getDateOfPurchase().format(dateTimeFormatter));
        return payload;
    }

    @Override
    public PurchasedItemsListPayload getCurrentUserPurchasedItems(String email) throws D2GBaseException {
        User user = userService.findUserByEmail(email);
        List<PurchasedItemImpl> purchasedItems = purchasedItemRepository.findByOwner(user);
        List<PurchasedItemPayload> payloads = new ArrayList<>();
        for (PurchasedItemImpl purchasedItem : purchasedItems) {
            PurchasedItemPayload payload;
            payload = modelMapper.map(purchasedItem, PurchasedItemPayload.class);
            payload.setItem(itemService.getItemDtoById(purchasedItem.getItem().getId()));
            payload.setStore(modelMapper.map(purchasedItem.getStore(), StorePayload.class));
            payload.setCurrency(modelMapper.map(purchasedItem.getCurrency(), CurrencyPayload.class));
            payload.setOwner(purchasedItem.getOwner().getId());
            payloads.add(payload);
        }
        PurchasedItemsListPayload response = new PurchasedItemsListPayload();
        response.setPurchasedItem(payloads);
        return response;

    }

    @Override
    @Transactional
    public void updatePurchasedItemById(UUID id,
                                        PurchasedItemUpdateIncomingDto incomingDto,
                                        String email) throws D2GBaseException {
        if (userService.findUserByEmail(email) == null)
            throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        User user = userService.findUserByEmail(email);
        if (!purchasedItemRepository.exists(id))
            throw new D2GBaseException(D2GBaseExceptionCodes.PURCHASED_ITEM_WITH_THIS_ID_NOT_EXIST);
        PurchasedItemImpl purchasedItem = purchasedItemRepository.findOne(id);
        if (incomingDto.getAmount() != null)
            purchasedItem.setAmount(incomingDto.getAmount());
        if (incomingDto.getCurrency() != null)
            purchasedItem.setCurrency(currencyService.getCurrencyById(incomingDto.getCurrency()));
        if (incomingDto.getPrice() != null)
            purchasedItem.setPrice(incomingDto.getPrice());
        if (incomingDto.getItem() != null)
            purchasedItem.setItem(itemService.getItemById(incomingDto.getItem()));
        if (incomingDto.getStore() != null)
            purchasedItem.setStore(storeService.getStoreById(incomingDto.getStore()));
        if (incomingDto.getDateOfPurchase() != null) {
            LocalDate localDate = LocalDate.parse(incomingDto.getDateOfPurchase(), dateTimeFormatter);
            purchasedItem.setDateOfPurchase(ZonedDateTime.of(localDate, LocalTime.MIDNIGHT, ZoneOffset.UTC));
        }
        purchasedItem.setUpdatedBy(user);
        purchasedItem.setUpdatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        purchasedItem.setVersion(purchasedItem.getVersion() + 1);
        purchasedItemRepository.save(purchasedItem);
    }

    @Override
    public PurchasedItem getPurchasedItemById(UUID id) throws D2GBaseException {
        if (!purchasedItemRepository.exists(id))
            throw new D2GBaseException(D2GBaseExceptionCodes.PURCHASED_ITEM_WITH_THIS_ID_NOT_EXIST);
        return purchasedItemRepository.findOne(id);
    }

    @Override
    @Transactional
    public void updateCurrentUserPurchasedItemById(UUID id, PurchasedItemUpdateIncomingDto incomingDto, String email) throws D2GBaseException {
        if (userService.findUserByEmail(email) == null)
            throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        User user = userService.findUserByEmail(email);
        if (!purchasedItemRepository.exists(id))
            throw new D2GBaseException(D2GBaseExceptionCodes.PURCHASED_ITEM_WITH_THIS_ID_NOT_EXIST);
        PurchasedItemImpl purchasedItem = purchasedItemRepository.findOne(id);
        if (!purchasedItem.getOwner().equals(user))
            throw new D2GBaseException(D2GBaseExceptionCodes.NOT_CURRENT_USER_ITEM);
        if (incomingDto.getAmount() != null)
            purchasedItem.setAmount(incomingDto.getAmount());
        if (incomingDto.getCurrency() != null)
            purchasedItem.setCurrency(currencyService.getCurrencyById(incomingDto.getCurrency()));
        if (incomingDto.getPrice() != null)
            purchasedItem.setPrice(incomingDto.getPrice());
        if (incomingDto.getItem() != null)
            purchasedItem.setItem(itemService.getItemById(incomingDto.getItem()));
        if (incomingDto.getStore() != null)
            purchasedItem.setStore(storeService.getStoreById(incomingDto.getStore()));
        if (incomingDto.getDateOfPurchase() != null) {
            LocalDate localDate = LocalDate.parse(incomingDto.getDateOfPurchase(), dateTimeFormatter);
            purchasedItem.setDateOfPurchase(ZonedDateTime.of(localDate, LocalTime.MIDNIGHT, ZoneOffset.UTC));
        }
        purchasedItem.setUpdatedBy(user);
        purchasedItem.setUpdatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        purchasedItem.setVersion(purchasedItem.getVersion() + 1);
        purchasedItemRepository.save(purchasedItem);
    }

    @Override
    public void deletePurchasedItemById(UUID id) throws D2GBaseException {
        if (!purchasedItemRepository.exists(id))
            throw new D2GBaseException(D2GBaseExceptionCodes.PURCHASED_ITEM_WITH_THIS_ID_NOT_EXIST);
        purchasedItemRepository.delete(id);
    }

    @Override
    public PurchasedItemsListPayload findAllPurchasedItemByItem(UUID item, String email) throws D2GBaseException {
        User user = userService.findUserByEmail(email);
        if (user == null) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        List<PurchasedItemImpl> purchasedItemList = purchasedItemRepository.findByOwnerAndItem(user, itemService.getItemById(item));
        List<PurchasedItemPayload> payloadsList = new ArrayList<>();
        for (PurchasedItemImpl purchasedItem : purchasedItemList) {
            PurchasedItemPayload payload;
            payload = modelMapper.map(purchasedItem,PurchasedItemPayload.class);
            payload.setItem(itemService.getItemDtoById(purchasedItem.getItem().getId()));
            payload.setStore(modelMapper.map(purchasedItem.getStore(), StorePayload.class));
            payload.setCurrency(modelMapper.map(purchasedItem.getCurrency(), CurrencyPayload.class));
            payload.setOwner(purchasedItem.getOwner().getId());
            payloadsList.add(payload);
        }
        PurchasedItemsListPayload response = new PurchasedItemsListPayload();
        response.setPurchasedItem(payloadsList);
        return response;
    }

    @Override
    public PurchasedItemsListPayload findCurrentUserPurchasedItemsForLastMonth(String email) throws D2GBaseException {
        User user = userService.findUserByEmail(email);
        if (user==null) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        ZonedDateTime monthAgo = ZonedDateTime.now(ZoneOffset.UTC).minusMonths(1);
        List<PurchasedItemImpl> purchasedItemList = purchasedItemRepository.findByOwnerAndDateOfPurchaseGreaterThan(user,monthAgo);
        List<PurchasedItemPayload> payloadsList = new ArrayList<>();
        for (PurchasedItemImpl purchasedItem : purchasedItemList) {
            PurchasedItemPayload payload;
            payload = modelMapper.map(purchasedItem,PurchasedItemPayload.class);
            payload.setItem(itemService.getItemDtoById(purchasedItem.getItem().getId()));
            payload.setStore(modelMapper.map(purchasedItem.getStore(), StorePayload.class));
            payload.setCurrency(modelMapper.map(purchasedItem.getCurrency(), CurrencyPayload.class));
            payload.setOwner(purchasedItem.getOwner().getId());
            payloadsList.add(payload);
        }
        PurchasedItemsListPayload response = new PurchasedItemsListPayload();
        response.setPurchasedItem(payloadsList);
        return response;
    }

    @Override
    public PurchasedItemsExpensesPayload getCurrentUserExpenses(String email) throws D2GBaseException {
        User owner = userService.findUserByEmail(email);
        if (owner==null) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        PurchasedItemsExpensesPayload payload = new PurchasedItemsExpensesPayload();
        payload.setExpenses(purchasedItemRepository.getOwnerExpenses(owner));
        return payload;
    }

    @Override
    public PurchasedItemsExpensesPayload getCurrentUserExpensesForLastMonth(String email) throws D2GBaseException {
        User owner = userService.findUserByEmail(email);
        if (owner==null) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        PurchasedItemsExpensesPayload payload = new PurchasedItemsExpensesPayload();
        ZonedDateTime date = ZonedDateTime.now(ZoneOffset.UTC).minusMonths(1);
        payload.setExpenses(purchasedItemRepository.getOwnerExpensesForLastMonth(owner,date));
        return payload;
    }
}

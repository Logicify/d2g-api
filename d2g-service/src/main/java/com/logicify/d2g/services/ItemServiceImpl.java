package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.itemincomingdtos.ItemCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.itemincomingdtos.ItemUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.itempayload.ItemPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.itempayload.ItemsListPayload;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.exceptions.D2GBaseExceptionCodes;
import com.logicify.d2g.interfaces.Category;
import com.logicify.d2g.interfaces.Item;
import com.logicify.d2g.interfaces.User;
import com.logicify.d2g.models.implementations.CategoryImpl;
import com.logicify.d2g.models.implementations.ItemImpl;
import com.logicify.d2g.repositories.ItemRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by twilight on 11.05.17.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    @Transactional
    public void createItem(ItemCreateIncomingDto incomingDto, String email) throws D2GBaseException {
        User user = userService.findUserByEmail(email);
        if (user == null) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        ItemImpl item = modelMapper.map(incomingDto,ItemImpl.class);
        Category category = categoryService.getCategoryById(incomingDto.getCategory());
        item.setCategory(category);
        item.setCreatedBy(user);
        item.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        itemRepository.save(item);
    }

    @Override
    public ItemsListPayload getAllItems() {
        List<ItemImpl> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);
        List<ItemPayload> itemPayloads = new ArrayList<>();
        items.forEach(item -> {
            ItemPayload itemPayload = modelMapper.map(item,ItemPayload.class);
            itemPayload.setCategory(item.getCategory().getId());
            itemPayloads.add(itemPayload);
        });
        ItemsListPayload response = new ItemsListPayload();
        response.setItems(itemPayloads);
        return response;
    }

    @Override
    public ItemPayload getItemDtoById(UUID id) throws D2GBaseException {
        if (!itemRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.ITEM_NOT_EXIST);
        ItemImpl item = itemRepository.findOne(id);
        ItemPayload payload = modelMapper.map(item,ItemPayload.class);
        payload.setCategory(item.getCategory().getId());
        return payload;
    }

    @Override
    public ItemsListPayload getItemsByName(String name) throws D2GBaseException {
        List<ItemImpl> items = itemRepository.findByNameContaining(name);
        if (items.isEmpty())
            throw new D2GBaseException(D2GBaseExceptionCodes.ITEMS_WITH_THIS_NAMES_DOES_NOT_EXIST);
        List<ItemPayload> itemPayloads = new ArrayList<>();
        items.forEach(item -> itemPayloads.add(modelMapper.map(item,ItemPayload.class)));
        ItemsListPayload response = new ItemsListPayload();
        response.setItems(itemPayloads);
        return response;
    }

    @Override
    @Transactional
    public void updateItemById(ItemUpdateIncomingDto incomingDto, UUID id, String email) throws D2GBaseException {
        User user = userService.findUserByEmail(email);
        if (user == null) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        if (!itemRepository.exists(id))
            throw new D2GBaseException(D2GBaseExceptionCodes.ITEM_NOT_EXIST);
        ItemImpl item = itemRepository.findOne(id);
        if (incomingDto.getName() != null) item.setName(incomingDto.getName());
        if (incomingDto.getCategory() != null) {
            item.setCategory(categoryService.getCategoryById(incomingDto.getCategory()));
        }
        item.setUpdatedBy(user);
        item.setUpdatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        item.setVersion(item.getVersion()+1);
        itemRepository.save(item);
    }

    @Override
    public void deleteItemById(UUID id) throws D2GBaseException {
        if (!itemRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.ITEM_NOT_EXIST);
        itemRepository.delete(id);
    }

    @Override
    public Item getItemById(UUID id) throws D2GBaseException {
        if (!itemRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.ITEM_NOT_EXIST);
        return itemRepository.findOne(id);
    }

}

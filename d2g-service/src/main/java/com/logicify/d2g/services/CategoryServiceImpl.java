package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.categoryincomingdtos.CategoryCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.categoryincomingdtos.CategoryUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.categorypayload.CategoriesListPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.categorypayload.CategoryPayload;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.exceptions.D2GBaseExceptionCodes;
import com.logicify.d2g.interfaces.User;
import com.logicify.d2g.models.implementations.CategoryImpl;
import com.logicify.d2g.repositories.CategoryRepository;
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
 * Created by twilight on 11.05.17.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void createCategory(CategoryCreateIncomingDto incomingDto, String email) {
        User user = userService.findUserByEmail(email);
        CategoryImpl category = modelMapper.map(incomingDto, CategoryImpl.class);
        category.setCreatedBy(user);
        category.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        categoryRepository.save(category);
    }

    @Override
    public CategoriesListPayload getAllCategories() {
        List<CategoryImpl> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        List<CategoryPayload> categoryPayloads = new ArrayList<>();
        categories.forEach(category -> categoryPayloads.add(modelMapper.map(category,CategoryPayload.class)));
        CategoriesListPayload response = new CategoriesListPayload();
        response.setCategories(categoryPayloads);
        return response;
    }

    @Override
    public CategoryPayload getCategoryDtoById(UUID id) throws D2GBaseException {
        if (!categoryRepository.exists(id)) throw
                new D2GBaseException(D2GBaseExceptionCodes.CATEGORY_NOT_EXIST);
        return modelMapper.map(categoryRepository.findOne(id),CategoryPayload.class);
    }

    @Override
    public CategoryImpl getCategoryById(UUID id) throws D2GBaseException {
        if (!categoryRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.CATEGORY_NOT_EXIST);
        return categoryRepository.findOne(id);
    }

    @Override
    public CategoriesListPayload getCategoriesByName(String name) throws D2GBaseException {
        List<CategoryImpl> categories = categoryRepository.findByNameContaining(name);
        if (categories.isEmpty()) throw
                new D2GBaseException(D2GBaseExceptionCodes.CATEGORY_NAME_NOT_EXIST);
        List<CategoryPayload> payloads = new ArrayList<>();
        categories.forEach(categoryConsumer -> payloads.add(modelMapper.map(categoryConsumer,CategoryPayload.class)));
        CategoriesListPayload response = new CategoriesListPayload();
        response.setCategories(payloads);
        return response;
    }

    @Override
    @Transactional
    public void updateCategoryById(CategoryUpdateIncomingDto incomingDto, UUID id, String email) throws D2GBaseException {
        if (!categoryRepository.exists(id)) throw
                new D2GBaseException(D2GBaseExceptionCodes.CATEGORY_NOT_EXIST);
        User user = userService.findUserByEmail(email);
        CategoryImpl category = categoryRepository.findOne(id);
        if (incomingDto.getName()!=null) category.setName(incomingDto.getName());
        category.setUpdatedBy(user);
        category.setUpdatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        category.setVersion(category.getVersion()+1);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(UUID id) throws D2GBaseException {
        if (!categoryRepository.exists(id)) throw
                new D2GBaseException(D2GBaseExceptionCodes.CATEGORY_NOT_EXIST);
        categoryRepository.delete(id);
    }
}

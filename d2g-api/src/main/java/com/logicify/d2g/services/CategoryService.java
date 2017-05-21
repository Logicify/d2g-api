package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.categoryincomingdtos.CategoryCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.categoryincomingdtos.CategoryUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.categorypayload.CategoriesListPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.categorypayload.CategoryPayload;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.models.implementations.CategoryImpl;

import java.util.UUID;

/**
 * Created by twilight on 11.05.17.
 */
public interface CategoryService {

    void createCategory(CategoryCreateIncomingDto incomingDto, String email);

    CategoriesListPayload getAllCategories();

    CategoryPayload getCategoryDtoById(UUID id) throws D2GBaseException;

    CategoryImpl getCategoryById(UUID id) throws D2GBaseException;

    CategoriesListPayload getCategoriesByName(String category) throws D2GBaseException;

    void updateCategoryById(CategoryUpdateIncomingDto incomingDto, UUID id, String email) throws D2GBaseException;

    void deleteCategoryById(UUID id) throws D2GBaseException;
}

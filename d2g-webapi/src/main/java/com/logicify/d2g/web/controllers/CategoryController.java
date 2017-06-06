package com.logicify.d2g.web.controllers;

import com.logicify.d2g.dtos.domain.dtos.OutgoingDto;
import com.logicify.d2g.dtos.domain.dtos.ResponseDtoBuilder;
import com.logicify.d2g.dtos.domain.incomingdtos.categoryincomingdtos.CategoryCreateIncomingDto;
import com.logicify.d2g.dtos.domain.incomingdtos.categoryincomingdtos.CategoryUpdateIncomingDto;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.services.CategoryService;
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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/category", method = RequestMethod.POST)
    @ResponseBody
    public OutgoingDto createCategory(@RequestBody CategoryCreateIncomingDto incomingDto, Principal principal) {
        try {
            DtoValidator.validate(incomingDto);
            categoryService.createCategory(incomingDto, principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/category", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getAllCategories() {
        try {
            return ResponseDtoBuilder.getResponseDto(categoryService.getAllCategories());
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/category/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getCategoryById(@PathVariable("id") UUID id) {
        try {
            return ResponseDtoBuilder.getResponseDto(categoryService.getCategoryDtoById(id));
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/category/by-name", method = RequestMethod.GET)
    @ResponseBody
    public OutgoingDto getCategoryByName(@RequestParam(value = "name") String name) {
        try {
            return ResponseDtoBuilder.getResponseDto(categoryService.getCategoriesByName(name));
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e) {
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/category/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public OutgoingDto updateCategoryById(@RequestBody CategoryUpdateIncomingDto incomingDto,
                                          @PathVariable("id") UUID id,
                                          Principal principal) {
        try {
            DtoValidator.validate(incomingDto);
            categoryService.updateCategoryById(incomingDto,id,principal.getName());
            return ResponseDtoBuilder.getResponseDto();
        } catch (D2GBaseException d2g) {
            return ResponseDtoBuilder.getResponseDto(d2g);
        } catch (Exception e){
            return ResponseDtoBuilder.getResponseDto(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/category/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public OutgoingDto deleteCategoryById(@PathVariable("id") UUID id) {
        try {
            categoryService.deleteCategoryById(id);
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

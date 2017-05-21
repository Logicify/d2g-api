package com.logicify.d2g.dtos.domain.outgoingdtos.categorypayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.List;

/**
 * Created by twilight on 11.05.17.
 */
public class CategoriesListPayload implements Payload {

    private List<CategoryPayload> categoryList;

    public List<CategoryPayload> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryPayload> categoryList) {
        this.categoryList = categoryList;
    }


}

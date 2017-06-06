package com.logicify.d2g.dtos.domain.outgoingdtos.categorypayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.List;

/**
 * Created by twilight on 11.05.17.
 */
public class CategoriesListPayload implements Payload {

    private List<CategoryPayload> categories;

    public List<CategoryPayload> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryPayload> categories) {
        this.categories = categories;
    }


}

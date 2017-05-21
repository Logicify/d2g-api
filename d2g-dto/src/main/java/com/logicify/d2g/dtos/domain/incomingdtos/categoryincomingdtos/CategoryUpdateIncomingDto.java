package com.logicify.d2g.dtos.domain.incomingdtos.categoryincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;

/**
 * Created by twilight on 11.05.17.
 */
public class CategoryUpdateIncomingDto implements IncomingDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String category) {
        this.name = category;
    }
}

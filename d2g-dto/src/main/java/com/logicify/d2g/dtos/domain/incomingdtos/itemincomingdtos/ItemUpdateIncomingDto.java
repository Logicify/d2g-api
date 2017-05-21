package com.logicify.d2g.dtos.domain.incomingdtos.itemincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;

import java.util.UUID;

/**
 * Created by twilight on 14.05.17.
 */
public class ItemUpdateIncomingDto implements IncomingDto {

    private String name;

    private UUID category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

}

package com.logicify.d2g.dtos.domain.outgoingdtos.itempayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.UUID;

/**
 * Created by twilight on 14.05.17.
 */
public class ItemPayload implements Payload {

    private UUID id;

    private String name;

    private UUID category;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

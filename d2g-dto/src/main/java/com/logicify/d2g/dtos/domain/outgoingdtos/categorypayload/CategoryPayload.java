package com.logicify.d2g.dtos.domain.outgoingdtos.categorypayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.UUID;

/**
 * Created by twilight on 11.05.17.
 */
public class CategoryPayload implements Payload {

    private UUID id;

    private String name;

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

}

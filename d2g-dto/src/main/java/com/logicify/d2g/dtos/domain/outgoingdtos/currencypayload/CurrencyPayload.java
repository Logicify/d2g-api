package com.logicify.d2g.dtos.domain.outgoingdtos.currencypayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.UUID;

/**
 * Created by twilight on 14.05.17.
 */
public class CurrencyPayload implements Payload {

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

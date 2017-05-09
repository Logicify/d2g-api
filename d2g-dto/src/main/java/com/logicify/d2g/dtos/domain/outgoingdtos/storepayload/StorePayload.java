package com.logicify.d2g.dtos.domain.outgoingdtos.storepayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.UUID;

/**
 * Created by twilight on 09.05.17.
 */
public class StorePayload implements Payload {

    private String name;

    private String location;

    private UUID id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}

package com.logicify.d2g.dtos.domain.incomingdtos.storeincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;

/**
 * Created by twilight on 09.05.17.
 */
public class StoreUpdateIncomingDto implements IncomingDto {

    String name;

    String location;

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
}

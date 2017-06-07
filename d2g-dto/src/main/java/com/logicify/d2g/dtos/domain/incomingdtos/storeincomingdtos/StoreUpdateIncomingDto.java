package com.logicify.d2g.dtos.domain.incomingdtos.storeincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;
import com.logicify.d2g.interfaces.Store;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by twilight on 09.05.17.
 */
public class StoreUpdateIncomingDto implements IncomingDto {

    @Pattern(regexp = "^[\\w\\s-=\\[\\]\\\\';,./~!@#$%^&*()_+{}|:<>?]*$", message = "STORE_NAME_IS_INVALID")
    @Size(min = Store.STORE_NAME_MIN_LENGTH, max = Store.STORE_NAME_MAX_LENGTH, message = "STORE_NAME_IS_INVALID")
    private String name;

    @Pattern(regexp = "\"^[A-Za-z]+([A-Za-z\\\\s'-]*[A-Za-z]+)?$\"", message = "STORE_LOCATION_IS_INVALID")
    private String location;

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

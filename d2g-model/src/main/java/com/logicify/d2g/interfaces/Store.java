package com.logicify.d2g.interfaces;

import java.util.UUID;

/**
 * Created by twilight on 09.05.17.
 */
public interface Store extends Auditable {

    int STORE_NAME_MIN_LENGTH = 3;

    int STORE_NAME_MAX_LENGTH = 32;

    int STORE_LOCATION_MIN_LENGTH = 3;

    int STORE_LOCATION_MAX_LENGTH = 256;

    String getName();

    void setName(String name);

    String getLocation();

    void setLocation(String location);

}

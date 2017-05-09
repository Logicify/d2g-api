package com.logicify.d2g.interfaces;

import java.util.UUID;

/**
 * Created by twilight on 09.05.17.
 */
public interface Store extends Auditable {

    String getName();

    void setName(String name);

    String getLocation();

    void setLocation(String location);

}

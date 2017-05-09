package com.logicify.d2g.dtos.domain.outgoingdtos.storepayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.List;

/**
 * Created by twilight on 09.05.17.
 */
public class StoresListPayload implements Payload {

    private List<StorePayload> storesList;

    public List<StorePayload> getStoresList() {
        return storesList;
    }

    public void setStoresList(List<StorePayload> storesList) {
        this.storesList = storesList;
    }
}

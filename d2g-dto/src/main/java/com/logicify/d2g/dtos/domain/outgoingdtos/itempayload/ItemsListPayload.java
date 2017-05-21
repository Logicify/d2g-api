package com.logicify.d2g.dtos.domain.outgoingdtos.itempayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.List;

/**
 * Created by twilight on 14.05.17.
 */
public class ItemsListPayload implements Payload {

    private List<ItemPayload> items;

    public List<ItemPayload> getItems() {
        return items;
    }

    public void setItems(List<ItemPayload> items) {
        this.items = items;
    }
}

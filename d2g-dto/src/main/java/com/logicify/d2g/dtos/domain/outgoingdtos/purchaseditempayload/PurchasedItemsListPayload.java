package com.logicify.d2g.dtos.domain.outgoingdtos.purchaseditempayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.List;

/**
 * Created by twilight on 17.05.17.
 */
public class PurchasedItemsListPayload implements Payload {

    private List<PurchasedItemPayload> purchasedItems;

    public List<PurchasedItemPayload> getPurchasedItem() {
        return purchasedItems;
    }

    public void setPurchasedItem(List<PurchasedItemPayload> purchasedItem) {
        this.purchasedItems = purchasedItem;
    }
}

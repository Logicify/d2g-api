package com.logicify.d2g.dtos.domain.outgoingdtos.purchaseditempayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;
import com.logicify.d2g.dtos.domain.outgoingdtos.currencypayload.CurrencyPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.itempayload.ItemPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.storepayload.StorePayload;
import com.logicify.d2g.interfaces.Currency;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by twilight on 17.05.17.
 */
public class PurchasedItemPayload implements Payload {

    private UUID id;

    private ItemPayload item;

    private UUID owner;

    private BigDecimal amount;

    private BigDecimal price;

    private CurrencyPayload currency;

    private StorePayload store;

    private String dateOfPurchase;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ItemPayload getItem() {
        return item;
    }

    public void setItem(ItemPayload item) {
        this.item = item;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CurrencyPayload getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyPayload currency) {
        this.currency = currency;
    }

    public StorePayload getStore() {
        return store;
    }

    public void setStore(StorePayload store) {
        this.store = store;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}

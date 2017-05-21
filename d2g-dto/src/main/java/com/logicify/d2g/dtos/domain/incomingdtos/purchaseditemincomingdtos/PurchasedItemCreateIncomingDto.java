package com.logicify.d2g.dtos.domain.incomingdtos.purchaseditemincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by twilight on 17.05.17.
 */
public class PurchasedItemCreateIncomingDto implements IncomingDto {

    private UUID item;

    private UUID currency;

    private UUID store;

    private BigDecimal amount;

    private BigDecimal price;

    private String dateOfPurchase;

    public UUID getItem() {
        return item;
    }

    public void setItem(UUID item) {
        this.item = item;
    }

    public UUID getCurrency() {
        return currency;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }

    public UUID getStore() {
        return store;
    }

    public void setStore(UUID store) {
        this.store = store;
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

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}

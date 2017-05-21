package com.logicify.d2g.interfaces;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Created by twilight on 10.05.17.
 */
public interface PurchasedItem extends Auditable {

    Item getItem();

    void setItem(Item item);

    Store getStore();

    void setStore(Store store);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    Currency getCurrency();

    void setCurrency(Currency currency);

    ZonedDateTime getDateOfPurchase();

    void setDateOfPurchase(ZonedDateTime dateOfPurchase);

    User getOwner();

    void setOwner(User owner);
}

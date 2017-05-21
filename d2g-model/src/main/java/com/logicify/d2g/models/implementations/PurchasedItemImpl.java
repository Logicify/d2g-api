package com.logicify.d2g.models.implementations;

import com.logicify.d2g.interfaces.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Created by twilight on 10.05.17.
 */
@Entity
@Table(name = "_purchased_items", schema = "public",
        indexes = {@Index(name = "PURCHASED_ITEMS_OWNER_INDEX", columnList = "owner"),
                @Index(name = "PURCHASED_ITEMS_OWNER_AND_ITEM_INDEX", columnList = "owner,item")})
public class PurchasedItemImpl extends BaseIdentifiableAuditable implements PurchasedItem {


    private Item item;

    private Store store;

    private BigDecimal price;

    private BigDecimal amount;

    private Currency currency;

    private ZonedDateTime dateOfPurchase;

    private User owner;

    @JoinColumn(name = "item", nullable = false)
    @ManyToOne(targetEntity = ItemImpl.class, fetch = FetchType.LAZY)
    @Override
    public Item getItem() {
        return this.item;
    }

    @Override
    public void setItem(Item item) {
        this.item = item;
    }

    @JoinColumn(name = "store")
    @ManyToOne(targetEntity = StoreImpl.class, fetch = FetchType.LAZY)
    @Override
    public Store getStore() {
        return this.store;
    }

    @Override
    public void setStore(Store store) {
        this.store = store;
    }

    @Column(name = "price", precision = 20, scale = 4)
    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "amount", precision = 20, scale = 4, nullable = false)
    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @JoinColumn(name = "currency", nullable = false)
    @ManyToOne(targetEntity = CurrencyImpl.class, fetch = FetchType.LAZY)
    @Override
    public Currency getCurrency() {
        return this.currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Column(name = "date_of_purchase", nullable = false)
    @Override
    public ZonedDateTime getDateOfPurchase() {
        return this.dateOfPurchase;
    }

    @Override
    public void setDateOfPurchase(ZonedDateTime dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @JoinColumn(name = "owner", nullable = false)
    @ManyToOne(targetEntity = UserImpl.class, fetch = FetchType.LAZY)
    @Override
    public User getOwner() {
        return this.owner;
    }

    @Override
    public void setOwner(User owner) {
        this.owner = owner;
    }


}

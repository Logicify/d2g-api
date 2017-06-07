package com.logicify.d2g.dtos.domain.incomingdtos.purchaseditemincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by twilight on 21.05.17.
 */
public class PurchasedItemUpdateIncomingDto implements IncomingDto {

    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}+$", message = "WRONG_UUID_FORMAT")
    private UUID item;

    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}+$", message = "WRONG_UUID_FORMAT")
    private UUID currency;

    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}+$", message = "WRONG_UUID_FORMAT")
    private UUID store;

    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}+$", message = "WRONG_UUID_FORMAT")
    private BigDecimal amount;

    private BigDecimal price;

    @Pattern(regexp = "^(((0[1-9]|[12]\\d|3[01])/(0[13578]|1[02])/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)/(0[13456789]|1[012])/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])/02/((19|[2-9]\\d)\\d{2}))|(29/02/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$",
            message = "DATE_OF_PURCHASE_IS_INVALID")
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


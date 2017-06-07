package com.logicify.d2g.dtos.domain.incomingdtos.purchaseditemincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;
import com.logicify.d2g.exceptions.D2GBaseExceptionCodes;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.UUID;

import static com.logicify.d2g.exceptions.D2GBaseExceptionCodes.INVALID_AMOUNT;
import static com.logicify.d2g.exceptions.D2GBaseExceptionCodes.INVALID_PRICE;

/**
 * Created by twilight on 17.05.17.
 */
public class PurchasedItemCreateIncomingDto implements IncomingDto {

    @NotNull(message = "ITEM_NAME_IS_NULL")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}+$", message = "WRONG_UUID_FORMAT")
    private UUID item;

    @NotNull(message = "CURRENCY_NAME_IS_NULL")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}+$", message = "WRONG_UUID_FORMAT")
    private UUID currency;

    @NotNull(message = "STORE_IS_NULL")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}+$", message = "WRONG_UUID_FORMAT")
    private UUID store;

    @NotNull(message = "AMOUNT_IS_NULL")
    @Pattern(regexp = "^[0-9]{0,20}+\\.+[0-9]{0,4}", message = "INVALID_AMOUNT")
    private BigDecimal amount;

    @NotNull(message = "PRICE_IS_NULL")
    @Pattern(regexp = "^[0-9]{0,20}+\\.+[0-9]{0,4}", message = "INVALID_PRICE")
    private BigDecimal price;

    @NotNull(message = "DATE_OF_PURCHASE_IS_NULL")
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

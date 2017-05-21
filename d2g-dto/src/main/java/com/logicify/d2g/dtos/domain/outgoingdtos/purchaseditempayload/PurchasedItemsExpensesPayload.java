package com.logicify.d2g.dtos.domain.outgoingdtos.purchaseditempayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.math.BigDecimal;

/**
 * Created by twilight on 21.05.17.
 */
public class PurchasedItemsExpensesPayload implements Payload {

    private BigDecimal expenses;

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }
}

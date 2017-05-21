package com.logicify.d2g.dtos.domain.outgoingdtos.currencypayload;

import com.logicify.d2g.dtos.domain.dtos.Payload;

import java.util.List;

/**
 * Created by twilight on 14.05.17.
 */
public class CurrenciesListPayload implements Payload {

    private List<CurrencyPayload> currencies;

    public List<CurrencyPayload> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyPayload> currencies) {
        this.currencies = currencies;
    }
}

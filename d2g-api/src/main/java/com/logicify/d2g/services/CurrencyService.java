package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.currencyincomingdtos.CurrencyUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.currencypayload.CurrenciesListPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.currencypayload.CurrencyPayload;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.interfaces.Currency;

import java.util.UUID;

/**
 * Created by twilight on 11.05.17.
 */
public interface CurrencyService {
    
    void createCurrency(CurrencyUpdateIncomingDto incomingDto, String email) throws D2GBaseException;

    CurrenciesListPayload getAllCurrencies();

    CurrencyPayload getCurrencyDtoById(UUID id) throws D2GBaseException;

    CurrenciesListPayload getCurrenciesByName(String name) throws D2GBaseException;

    void updateCurrencyById(CurrencyUpdateIncomingDto incomingDto, UUID id, String email) throws D2GBaseException;

    void deleteCurrencyById(UUID id) throws D2GBaseException;

    Currency getCurrencyById(UUID id) throws D2GBaseException;


}

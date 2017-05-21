package com.logicify.d2g.services;

import com.logicify.d2g.dtos.domain.incomingdtos.currencyincomingdtos.CurrencyUpdateIncomingDto;
import com.logicify.d2g.dtos.domain.outgoingdtos.currencypayload.CurrenciesListPayload;
import com.logicify.d2g.dtos.domain.outgoingdtos.currencypayload.CurrencyPayload;
import com.logicify.d2g.exceptions.D2GBaseException;
import com.logicify.d2g.exceptions.D2GBaseExceptionCodes;
import com.logicify.d2g.interfaces.Currency;
import com.logicify.d2g.interfaces.User;
import com.logicify.d2g.models.implementations.CurrencyImpl;
import com.logicify.d2g.repositories.CurrencyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by twilight on 11.05.17.
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private UserService userService;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void createCurrency(CurrencyUpdateIncomingDto incomingDto, String email) throws D2GBaseException {
        User user = userService.findUserByEmail(email);
        if (user == null) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        CurrencyImpl currency = modelMapper.map(incomingDto, CurrencyImpl.class);
        currency.setCreatedBy(user);
        currency.setCreatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        currencyRepository.save(currency);
    }

    @Override
    public CurrenciesListPayload getAllCurrencies() {
        List<CurrencyImpl> currencies = new ArrayList<>();
        currencyRepository.findAll().forEach(currencies::add);
        List<CurrencyPayload> currencyPayloads = new ArrayList<>();
        currencies.forEach(currency -> currencyPayloads.add(modelMapper.map(currency, CurrencyPayload.class)));
        CurrenciesListPayload response = new CurrenciesListPayload();
        response.setCurrencies(currencyPayloads);
        return response;
    }

    @Override
    public CurrencyPayload getCurrencyDtoById(UUID id) throws D2GBaseException {
        if (!currencyRepository.exists(id))
            throw new D2GBaseException(D2GBaseExceptionCodes.CURRENCY_NOT_EXIST);
        return modelMapper.map(currencyRepository.findOne(id), CurrencyPayload.class);
    }

    @Override
    public CurrenciesListPayload getCurrenciesByName(String name) throws D2GBaseException {
        List<CurrencyImpl> currencies = currencyRepository.findByNameContaining(name);
        if (currencies.isEmpty()) throw new D2GBaseException(D2GBaseExceptionCodes.NO_CURRENCY_WITH_THIS_NAME);
        List<CurrencyPayload> currencyPayloads = new ArrayList<>();
        currencies.forEach(currency -> currencyPayloads.add(modelMapper.map(currencies, CurrencyPayload.class)));
        CurrenciesListPayload response = new CurrenciesListPayload();
        response.setCurrencies(currencyPayloads);
        return response;
    }

    @Override
    @Transactional
    public void updateCurrencyById(CurrencyUpdateIncomingDto incomingDto, UUID id, String email) throws D2GBaseException {
        User user = userService.findUserByEmail(email);
        if (user == null) throw new D2GBaseException(D2GBaseExceptionCodes.USER_NOT_EXIST);
        if (!currencyRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.CATEGORY_NOT_EXIST);
        CurrencyImpl currency = currencyRepository.findOne(id);
        if (incomingDto.getName() != null) currency.setName(incomingDto.getName());
        currency.setUpdatedBy(user);
        currency.setUpdatedOn(ZonedDateTime.now(ZoneOffset.UTC));
        currency.setVersion(currency.getVersion() + 1);
    }

    @Override
    public void deleteCurrencyById(UUID id) throws D2GBaseException {
        if (!currencyRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.CATEGORY_NOT_EXIST);
        currencyRepository.delete(id);
    }

    @Override
    public Currency getCurrencyById(UUID id) throws D2GBaseException {
        if (!currencyRepository.exists(id)) throw new D2GBaseException(D2GBaseExceptionCodes.CURRENCY_NOT_EXIST);
        return currencyRepository.findOne(id);
    }
}

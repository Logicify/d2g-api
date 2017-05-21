package com.logicify.d2g.dtos.domain.incomingdtos.currencyincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;

/**
 * Created by twilight on 14.05.17.
 */
public class CurrencyCreateIncomingDto implements IncomingDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

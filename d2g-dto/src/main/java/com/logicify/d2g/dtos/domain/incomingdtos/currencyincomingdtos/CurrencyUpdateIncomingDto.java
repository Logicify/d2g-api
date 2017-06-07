package com.logicify.d2g.dtos.domain.incomingdtos.currencyincomingdtos;

import com.logicify.d2g.dtos.domain.dtos.IncomingDto;

import javax.validation.constraints.Pattern;

/**
 * Created by twilight on 14.05.17.
 */
public class CurrencyUpdateIncomingDto implements IncomingDto {

    @Pattern(regexp = "^[\\p{L}{3}]*$", message = "CURRENCY_NAME_IS_INVALID")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

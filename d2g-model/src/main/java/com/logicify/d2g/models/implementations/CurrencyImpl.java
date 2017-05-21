package com.logicify.d2g.models.implementations;

import com.logicify.d2g.interfaces.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by twilight on 10.05.17.
 */

@Entity
@Table(name = "_currency", schema = "public",
        indexes = {@Index(name = "CURRENCY_NAME_INDEX",columnList = "name")})
public class CurrencyImpl extends BaseIdentifiableAuditable implements Currency {

    private String name;

    @Column(name = "name", nullable = false, unique = true)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }
}

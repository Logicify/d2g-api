package com.logicify.d2g.models.implementations;

import com.logicify.d2g.interfaces.Store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by twilight on 09.05.17.
 */
@Entity
@Table(name = "_stores", schema = "public")
public class StoreImpl extends BaseIdentifiableAuditable implements Store {

    private String name;

    private String location;

    @Column(name = "name", nullable = false)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Column(name = "location")
    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(String location) {
        this.location=location;
    }
}

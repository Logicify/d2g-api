package com.logicify.d2g.models.implementations;

import com.logicify.d2g.interfaces.Category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by twilight on 10.05.17.
 */

@Entity
@Table(name = "_category", schema = "public",
        indexes = {@Index(name = "CATEGORY_NAME_INDEX", columnList = "name")})
public class CategoryImpl extends BaseIdentifiableAuditable implements Category {

    private String name;

    @Column(name = "name", nullable = false, unique = true)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}

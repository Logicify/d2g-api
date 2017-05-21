package com.logicify.d2g.models.implementations;

import com.logicify.d2g.interfaces.Category;
import com.logicify.d2g.interfaces.Item;

import javax.persistence.*;

/**
 * Created by twilight on 10.05.17.
 */
@Entity
@Table(name = "_items", schema = "public",
        indexes = {@Index(name = "ITEMS_NAME_INDEX",columnList = "name")})
public class ItemImpl extends BaseIdentifiableAuditable implements Item {

    private String name;

    private Category category;

    @Column(name = "name", unique = true, nullable = false)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @JoinColumn(name = "category", nullable = false, unique = true)
    @ManyToOne(targetEntity = CategoryImpl.class,fetch = FetchType.LAZY)
    @Override
    public Category getCategory() {
        return this.category;
    }

    @Override
    public void setCategory(Category category) {
        this.category=category;
    }
}

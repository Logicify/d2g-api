package com.logicify.d2g.interfaces;

/**
 * Created by twilight on 10.05.17.
 */
public interface Item extends Auditable {

    String getName();

    void setName(String name);

    Category getCategory();

    void setCategory(Category category);
}

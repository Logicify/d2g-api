package com.logicify.d2g.interfaces;

/**
 * Created by twilight on 10.05.17.
 */
public interface Item extends Auditable {

    int ITEM_NAME_MIN_LENGTH = 3;

    int ITEM_NAME_MAX_LENGTH = 32;

    String getName();

    void setName(String name);

    Category getCategory();

    void setCategory(Category category);
}

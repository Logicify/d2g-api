package com.logicify.d2g.exceptions;

import com.logicify.d2g.interfaces.PurchasedItem;

/**
 * Created by jadencorr on 05.03.17.
 */
public enum D2GBaseExceptionCodes {

    ALL_CORRECT(0, null),
    SOMETHING_WENT_WRONG(1, "Something went wrong"),
    UNCORRECTED_PASSWORD(2, "This password not allowed"),
    USER_NOT_EXIST(3, "User not exist"),
    WRONG_DATA(4, ""),
    WRONG_STATUS(5, "Wrong user status"),
    USER_ALREADY_EXIST(6, "User with this email already exist"),
    STORE_NOT_EXIST(7, "Store with this id do not exist"),
    CATEGORY_NOT_EXIST(8, "Category with this id do not exist"),
    NO_CATEGORY_WITH_THIS_NAME(9, "Category with this name not exist in database"),
    STORE_WITH_THIS_NAME_NOT_EXIST(10, "Stores with this name not exist in database. Create new?"),
    PASSWORDS_NOT_EQUALS(11, "New password and repeat not the same"),
    CURRENCY_NOT_EXIST(12, "Currency with this ID not exist"),
    NO_CURRENCY_WITH_THIS_NAME(13, "Currencies with this name not exist. Create new?"),
    ITEM_NOT_EXIST(14, "Item with this ID not exist"),
    ITEMS_WITH_THIS_NAMES_DOES_NOT_EXIST(15, "Items with this name does not exist. Create new?"),
    PURCHASED_ITEM_WITH_THIS_ID_NOT_EXIST(16, "Purchased item with this id not exist in database"),
    NOT_CURRENT_USER_ITEM(17, "Not current user item");


    private final int id;
    private final String message;

    D2GBaseExceptionCodes(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

}

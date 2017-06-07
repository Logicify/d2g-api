package com.logicify.d2g.exceptions;

/**
 * Created by twilight on 06.06.17.
 */
public enum D2GBaseExceptionCodes {

    ALL_CORRECT(0, null),
    NO_INTERNET_CONNECTION(1, "No internet connection"),
    ACCOUNT_IS_LOCKED(2, "Your account has been locked. Please contact support."),
    WRONG_EMAIL_OR_PASSWORD(3, "Invalid email or password"),
    NO_EMAIL_OR_PASSWORD(4, "Email and password are required"),
    NEED_ACTIVATION(5, "Your account needs activation! Please check your email and activate the account."),
    SPACE_IN_EMAIL_OR_PASSWORD(6, "Email and password should not contain spaces"),
    EMAIL_ALREADY_IN_USE(7, "The email is already in use"),
    INVALID_EMAIL(8, "Invalid email address"),
    FIRST_NAME_IS_NULL(9, "First name is required"),
    LAST_NAME_IS_NULL(10, "Last name is required"),
    EMAIL_IS_NULL(11, "Email is required"),
    PASSWORD_IS_NULL(12, "Password is required"),
    PASSWORD_CONFIRMATION_IS_NULL(13, "Confirmation password is required"),
    WRONG_FIRST_NAME(14, "First name should contain minimum 3 characters and maximum 50 characters. It can only contain letters (Aa-Zz), hyphens, spaces, apostrophes followed by one or more letter"),
    WRONG_LAST_NAME(15, "Last name should contain minimum 3 characters and maximum 50 characters. It can only contain letters (Aa-Zz), hyphens, spaces, apostrophes followed by one or more letter"),
    UNSECURED_PASSWORD(16, "Password should be between 8-20 characters with at least 1 lowercase letter and 1 number"),
    UNCORRECTED_PASSWORD(17, "Password has invalid characters. It can only contain letters (Aa-Zz), digits and symbols (e.g. -=[]\\;,./~!@#$%^&*()_+{}|:<>?)"),
    PASSWORD_NOT_THE_SAME(18, "The confirmation password does not match the password you first entered"),
    VERIFICATION_LINK_WAS_EXPIRED(19, "The email verification link has expired. You may request another one by trying to log in to your account"),
    ALREADY_CONFIRMED(20, "This account has already been confirmed"),
    USER_NOT_EXIST(21, "User with this email was not found"),
    PASSWORD_CHANGE_LINK_WAS_EXPIRED(22, "The password reset link has expired. Please go to “Forgot password?” and begin the process again"),
    EXISTENT_PASSWORD_IS_INVALID(23, "Existent password is invalid"),
    FORMAT_IS_NOT_SUPPORTED(24, "Format is not supported. Please use PNG, JPG, or GIF"),
    IMAGE_TO_SMALL(25, "Image is too small. Image dimensions should be at least 200x200 pixels"),
    IMAGE_TO_LARGE(26, "Image is too large. File size cannot exceed 5 MB"),
    SELECT_IMAGE(27, "Please select an image"),
    PERIOD_TO_LARGE(28, "Period is more than allowed. You can generate transaction list for 90 days"),
    DATE_OF_PURCHASE_IS_INVALID(29, "Invalid date format. Please specify as dd/mm/yyyy"),
    START_DATE_BIGGER_THEN_END_DAY(30, "Start date can’t be later than end date"),
    NO_TRANSACTIONS_TO_DISPLAY(31, "No transactions to display"),
    SELECT_TRANSACTION_TO_DELETE(32, "Please select the transaction you want to delete"),
    DATE_OF_PURCHASE_REQUIRED(33, "Transaction date is required"),
    ITEM_IS_NULL(34, "Product name is required"),
    CATEGORY_IS_NULL(35, "Category is required"),
    PRICE_IS_NULL(36, "Unit price is required"),
    ITEM_NAME_IS_INVALID(37, "Product name should not be less than 3 characters and more than 32 characters. It can contain: a-z, A-Z, 0-9, spaces, symbols (e.g. -=[]\\;,./~#№$%&*()_+:)"),
    STORE_NAME_IS_INVALID(38, "Store location should not be less than 3 characters and more than 256 characters. It can contain: a-z, A-Z, 0-9, spaces, symbols (e.g. -=[]\\;,./~#№$%&*()_+:)"),
    TO_BIG_OR_SMALL_PRICE(39, "Price should not be less than 0.01 and more than 9 999 999 999.99"),
    TO_BIG_OR_SMALL_AMOUNT(40, "Amount should not be less than 0.01 and more than 9 999 999 999.99"),
    INVALID_PRICE(41, "Price can contain 0-9, space as the thousands separator, comma or period as the decimal separator"),
    INVALID_AMOUNT(43, "Amount can contain 0-9, space as the thousands separator, comma or period as the decimal separator"),
    SOMETHING_WENT_WRONG(42, "Something went wrong"),
    STORE_NOT_EXIST(44, "Store with this id not exist"),
    PURCHASED_ITEM_NOT_EXIST(45, "Purchased item with this id not exist"),
    CURRENCY_NOT_EXIST(46, "Currency with this id not exist"),
    ITEM_NOT_EXIST(47, "Item with this id not exist"),
    WRONG_STATUS(48, "Wrong user status"),
    CATEGORY_NOT_EXIST(49, "Category with this id not exist"),
    CATEGORY_NAME_NOT_EXIST(50, "Category with this name not exist in database"),
    STORE_NAME_NOT_EXIST(51, "Store with this name not exist in database"),
    CURRENCY_NAME_NOT_EXIST(52, "Currency with this name not exist in database"),
    ITEM_NAME_NOT_EXIST(53, "Item with this name not exist in database"),
    NOT_CURRENT_USER_ITEM(54, "Purchased item with this id not purchased by current user"),
    AVATAR_URL_TO_LONG(55, "Avatar URL is to long. Please use another link"),
    CATEGORY_NAME_IS_NULL(56, "Category name is required"),
    CATEGORY_NAME_IS_INVALID(57, "Category name is invalid. It should contain only letters(Aa-Zz), numbers and punctuation symbol"),
    CURRENCY_NAME_IS_NULL(58,"Currency name is required"),
    CURRENCY_NAME_IS_INVALID(59,"Currency name is invalid. It should contain only letter (Aa-Zz)"),
    ITEM_NAME_IS_NULL(60, "Item name is required"),
    STORE_NAME_IS_NULL(62, "Store name is required"),
    STORE_LOCATION_IS_NULL(64, "Store location is required"),
    STORE_LOCATION_IS_INVALID(65, "Store name is invalid. It should contain only numbers, letters (Aa-Zz), hyphens, spaces, apostrophes followed by one or more letter"),
    WRONG_UUID_FORMAT(66, "There is no uuid"),
    AMOUNT_IS_NULL(67, "Amount is required"),
    STORE_IS_NULL(68, "Store is required"),
    STORE_WITH_THIS_NAME_NOT_EXIST(69, "Store with this name not exist"),//TODO:Check for the same error message
    CURRENCY_WITH_THIS_NAME_NOT_EXIST(70, "Currency with this name not exist");

    private final int id;

    private final String errorMessage;

    D2GBaseExceptionCodes(int id, String errorMessage) {
        this.id = id;
        this.errorMessage = errorMessage;
    }

    public int getId() {
        return id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

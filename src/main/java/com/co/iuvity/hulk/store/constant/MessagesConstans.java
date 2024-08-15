package com.co.iuvity.hulk.store.constant;

public class MessagesConstans {

	public static final String NOT_FOUND_PRODUCTS = "there are no products.";
	public static final String NOT_FOUND_PRODUCT = "product with id ? not found.";
	
	public static final String REPLACE = "?";	
	public static final String MSN_FIELD_NUNABLE_ERROR = "The field '?' cant be null or empty.";
	public static final String MSN_PRODUCT_DELETED = "The product whit id '?' was deleted.";
		
	public static final String ERROR_FORMAT_DATE = "the field '?' can be valid date format.";
	public static final String ERROR_FORMAT_NUMBER = "the field '?' can bea number value";
	public static final String MSN_INVALID_ID_PRODUCT = "conflict when saving transaction, id field does not refer to any product." ;
    public static final String MSN_INSUFFICIENT_AMOUNT = "conflict when saving transaction, insufficient quantity in inventory of the product with id '?'.";
}

package com.fssa.veeblooms.model;

public interface ErrorMessages {
	public static final String INVALID_PLANT_NULL = "Plant object can't be null";
	public static final String INVALID_PLANT_NAME = "Plant name can't be null ";
	public static final String PLANT_NOT_EXISTS = "Plant doesn't exists";
	public static final String INVALID_PLANT_NAME_LENGTH = "The length of the plant name should be minimum of 2 characters";
	public static final String INVALID_PLANT_NAME_PATTERN = "Plant name shouldn't contain any numbers";
	public static final String INVALID_PLANT_PLANTIMAGESURL = "Plant image url can't be null";
	public static final String INVALID_PLANT_INVALIDPLANTIMAGESURL = "Plant image must have url can't be null";
	public static final String INVALID_PLANT_PRICE = "Plant price can't be null";
	public static final String INVALID_PLANT_RATING = "Plant rating can't be null";
	public static final String INVALID_PLANT_RATING_NULL = "Plant rating shouldn't be null";
	public static final String INVALID_PLANT_RATING_ZERO = "Plant rating shuldn't be zero";
	public static final String INVALID_PLANT_RATING_NEGATIVE = "Plant rating shouldn't be less than zero";
	public static final String INVALID_PLANT_TYPE = "Plant type can't be null";
	public static final String INVALID_PLANT_HEIGHT = "Plant height can't be null";
	public static final String INVALID_PLANTING_SEASON = "Planting season can't be null";
	public static final String INVALID_HYBRID = "Plant hybrid can't be null";
	public static final String INVALID_PLANTNAME_ALREADY_EXISTS = "Plant name already exists";
	public static final String INVALID_PLANT_CREATING = "Error creating plant";
	public static final String INVALID_PLANT_CHECKING_ERROR = "Error while checking whether the plant already exists";
	public static final String ERROR_GETTING_PLANTNAME = "Error getting id by plant name";
	public static final String ERROR_ADDING_IMAGEURL = "Error in adding plant image urls ";
	public static final String ERROR_GETTING_PLANTID = "Error in getting plant id";
	public static final String INVALID_USER_NULL = "User cannot be null.";
	public static final String INVALID_FIRST_NAME = "Invalid first name.";
	public static final String INVALID_LAST_NAME = "Invalid last name.";
	public static final String INVALID_EMAIL = "Invalid email address.";
	public static final String INVALID_PASSWORD = "Invalid password.";
	public static final String INVALID_MOBILE_NUMBER = "Invalid mobile number.";
	public static final String INVALID_ADDRESS = "Invalid address";
	public static final String INVALID_GENDER = "Invalid gender";
	public static final String INVALID_ORDER_NULL = "Order object can't be null";
	public static final String INVALID_PRODUCT_LIST = "Product list can't be null";
	public static final String INVALID_STATUS = "Invalid order status";
	public static final String INVALID_COMMENTSTATEMENT = "Comments must be at least 10 characters long";
	public static final String ORDER_DATE_IN_FUTURE = "Ordered date cannot be in the future";
	public static final String INVALID_TOTAL_AMOUNT = "Invalid total amount. Total amount must be a positive number";
	public static final String INVALID_ORDERED_DATE = "Invalid ordered date. Please provide a valid date";
	public static final String ORDER_ALREADY_EXISTS = "Order with ID already exists in the database";
	public static final String ORDER_CREATION_FAILED = "Failed to create the order in the database";
	public static final String ORDER_RETRIEVAL_FAILED = "Failed to retrieve order data from the database";
	public static final String ORDER_DELETION_FAILED = "Error occured in deleting order";

}

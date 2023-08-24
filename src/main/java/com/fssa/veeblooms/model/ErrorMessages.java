package com.fssa.veeblooms.model;


public interface ErrorMessages {
	public static final String INVALID_PLANT_NULL= "Plant object can't be null";
	public static final String INVALID_PLANT_NAME= "Plant name can't be null ";
	public static final String INVALID_PLANT_NAME_LENGTH= "The length of the plant name should be minimum of 2 characters";
	public static final String INVALID_PLANT_NAME_PATTERN="Plant name shouldn't contain any numbers";
	public static final String INVALID_PLANT_PLANTIMAGESURL= "Plant image url can't be null";
	public static final String INVALID_PLANT_INVALIDPLANTIMAGESURL= "Plant image must have url can't be null";
	public static final String INVALID_PLANT_PRICE= "Plant price can't be null";
	public static final String INVALID_PLANT_RATING= "Plant rating can't be null";
	public static final String INVALID_PLANT_RATING_NULL="Plant rating shouldn't be null";
	public static final String INVALID_PLANT_RATING_ZERO="Plant rating shuldn't be zero";
	public static final String INVALID_PLANT_RATING_NEGATIVE="Plant rating shouldn't be less than zero";
	public static final String INVALID_PLANT_TYPE= "Plant type can't be null";
	public static final String INVALID_PLANT_HEIGHT= "Plant height can't be null";
	public static final String INVALID_PLANTING_SEASON= "Planting season can't be null";
	public static final String INVALID_HYBRID= "Plant hybrid can't be null";
	public static final String INVALID_PLANTNAME_ALREADY_EXISTS= "Plant name already exists";
	public static final String INVALID_PLANT_CREATING= "Error creating plant";
	public static final String INVALID_PLANT_CHECKING_ERROR= "Error while checking whether the plant already exists";
	public static final String ERROR_GETTING_PLANTNAME= "Error getting id by plant name";
	public static final String ERROR_ADDING_IMAGEURL= "Error in adding plant image urls ";
	
}


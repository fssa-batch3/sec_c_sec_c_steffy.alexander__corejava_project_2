package com.fssa.veeblooms.validator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fssa.veeblooms.Enum.HybridEnum;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.model.ErrorMessages;
import com.fssa.veeblooms.model.Plant;


public class TestPlantValidator {

	@Test
	public void testValidatePlant_NullPlant() throws CustomException {
		Plant nullPlant = null;

		try {
			PlantValidator.validatePlant(nullPlant);
			Assertions.fail("Expected CustomException for invalid plant name, but no exception was thrown.");
		} catch (CustomException e) {
			Assertions.assertEquals("Plant object can't be null", e.getMessage());
		}
	}

	@Test
	public void testInvalidPlantObjectNull() throws CustomException{
//		List<String> images = new ArrayList<String>();
//		images.add("https://www.youtube.com/watch?v=55tCJ8Odjvw");
//		images.add("https://learn.facecampus.org/fn/fop-and-dsa-training/#curriculum");
//		images.add("https://app.facecampus.org/calendar/");
//		images.add("https://chat.openai.com/");
 
		Plant plantWithInvalidName =   new Plant(null);

		try {
			PlantValidator.validatePlant(plantWithInvalidName);
			Assertions.fail("Expected CustomException for invalid plant name, but no exception was thrown.");
		} catch (CustomException e) {
			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME, e.getMessage());
		}
	}

	@Test
	public void testValidatePlant_ValidPlant() throws CustomException {
		List<String> images = new ArrayList<String>();
		images.add("https://www.youtube.com/watch?v=55tCJ8Odjvw");
		images.add("https://learn.facecampus.org/fn/fop-and-dsa-training/#curriculum");
		images.add("https://app.facecampus.org/calendar/");
		images.add("https://chat.openai.com/");
		new Plant("rose");
		Plant validPlant = new Plant("Sunflower",images, 25.0, 4, "Flowering", 30, "Spring", HybridEnum.NO);
		
			boolean result = PlantValidator.validatePlant(validPlant);
			Assertions.assertTrue(result);
		
	}

	// testcase for plant name

	@Test
	public void testValidatePlantName_ValidName() {
		// Arrange
		String validPlantName = "Sunflower";
		try {
			PlantValidator.validatePlantName(validPlantName);
		} catch (CustomException e) {
			// Assert
			Assertions.fail("Unexpected exception thrown for a valid plant name.");
		}
	}

	@Test
	public void testValidatePlantName_NullName() {
		// Arrange
		String nullPlantName = null;
		// Act
		CustomException exception = null;
		try {
			PlantValidator.validatePlantName(nullPlantName);
		} catch (CustomException e) {
			exception = e;
		}

		// Assert
//		 "Expected CustomException for null plant name");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME, exception.getMessage());
	}

	@Test
	public void testValidatePlantName_EmptyName() {
		// Arrange
		String emptyPlantName = "";
		// Act
		CustomException exception = null;
		try {
			PlantValidator.validatePlantName(emptyPlantName);
		} catch (CustomException e) {
			exception = e;
		}


		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME, exception.getMessage());
	}

	@Test
	public void testValidatePlantName_ShortName() {
		// Arrange
		String shortPlantName = "AB";
		// Act
		CustomException exception = null;
		try {
			PlantValidator.validatePlantName(shortPlantName);
		} catch (CustomException e) {
			exception = e;
		}


		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME_LENGTH, exception.getMessage());
	}

//test case for plant images

	@Test
	public void testValidatePlantImagesUrl_Valid() {
		List<String> validUrls = new ArrayList<String>();
		validUrls.add("https://example.com/image1.jpg");
		validUrls.add("https://example.com/image2.jpg");

		try {
			boolean isValid = PlantValidator.validatePlantImagesUrl(validUrls);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail(ErrorMessages.INVALID_PLANT_PLANTIMAGESURL);
		}
	}

	@Test
	public void testValidatePlantImagesUrl_NullList() {
		List<String> nullList = null;
		try {
			PlantValidator.validatePlantImagesUrl(nullList);
			Assertions.fail("Should throw a CustomException for a null list.");
		} catch (CustomException e) {
			// Test passes if it catches the CustomException
			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_PLANTIMAGESURL, e.getMessage());
		}
	}

	@Test
	public void testValidatePlantImagesUrl_EmptyList() {
		List<String> emptyList = new ArrayList<String>();
		try {
			PlantValidator.validatePlantImagesUrl(emptyList);
			Assertions.fail("Should throw a CustomException for an empty list.");
		} catch (CustomException e) {
			// Test passes if it catches the CustomException
			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_PLANTIMAGESURL, e.getMessage());
		}
	}

	// testcases for plant price

	@Test
	public void testValidatePrice_Valid() {
		double validPrice = 25.99;
		try {
			boolean isValid = PlantValidator.validatePrice(validPrice);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail("Unexpected exception thrown for a valid price.");
		}
	}

	@Test
	public void testValidatePrice_ZeroPrice() {
		double zeroPrice = 0.0;
		CustomException exception = null;
		try {
			PlantValidator.validatePrice(zeroPrice);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for zero price");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_PRICE, exception.getMessage());
	}

	@Test
	public void testValidatePrice_NegativePrice() {
		double negativePrice = -10.50;
		CustomException exception = null;
		try {
			PlantValidator.validatePrice(negativePrice);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for negative price");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_PRICE, exception.getMessage());
	}

	// testcase for plant rating

	@Test
	public void testValidateRating_Valid() {
		int validRating = 4;
		try {
			boolean isValid = PlantValidator.validateRating(validRating);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail(ErrorMessages.INVALID_PLANT_RATING);
		}
	}

	@Test
	public void testValidateRating_ZeroRating() {
		int zeroRating = 0;
		try {
			PlantValidator.validateRating(zeroRating);
		} catch (CustomException e) {

			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_RATING_ZERO, e.getMessage());
		}

	}

	@Test
	public void testValidateRating_NegativeRating() {
		int negativeRating = -2;
		CustomException exception = null;
		try {
			PlantValidator.validateRating(negativeRating);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for negative rating");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_RATING, exception.getMessage());
	}

	// testcase for planttype

	@Test
	public void testValidatePlantType_Valid() {
		String validPlantType = "Shrub";
		try {
			boolean isValid = PlantValidator.validatePlantType(validPlantType);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail("Unexpected exception thrown for a valid plant type.");
		}
	}

	@Test
	public void testValidatePlantType_Null() {
		String nullPlantType = null;
		CustomException exception = null;
		try {
			PlantValidator.validatePlantType(nullPlantType);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for null plant type");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_TYPE, exception.getMessage());
	}

	@Test
	public void testValidatePlantType_Empty() {
		String emptyPlantType = "";
		CustomException exception = null;
		try {
			PlantValidator.validatePlantType(emptyPlantType);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for empty plant type");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_TYPE, exception.getMessage());
	}

	@Test
	public void testValidatePlantType_Short() {
		String shortPlantType = "A";
		CustomException exception = null;
		try {
			PlantValidator.validatePlantType(shortPlantType);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for short plant type");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_TYPE, exception.getMessage());
	}

	@Test
	public void testValidatePlantType_ContainsNumbers() {
		String invalidPlantType = "Plant123";
		CustomException exception = null;
		try {
			PlantValidator.validatePlantType(invalidPlantType);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for plant type containing numbers");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME_PATTERN, exception.getMessage());
	}

	// testcase for plantheight

	@Test
	public void testValidatePlantHeight_Valid() {
		float validPlantHeight = 0.5f;
		try {
			boolean isValid = PlantValidator.validatePlantHeight(validPlantHeight);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail("Unexpected exception thrown for a valid plant height.");
		}
	}

	@Test
	public void testValidatePlantHeight_Zero() {
		float zeroPlantHeight = 0.0f;
		CustomException exception = null;
		try {
			PlantValidator.validatePlantHeight(zeroPlantHeight);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for zero plant height");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_HEIGHT, exception.getMessage());
	}

	@Test
	public void testValidatePlantHeight_Negative() {
		float negativePlantHeight = -1.5f;
		CustomException exception = null;
		try {
			PlantValidator.validatePlantHeight(negativePlantHeight);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for negative plant height");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_HEIGHT, exception.getMessage());
	}

	// testcases for planting season

	@Test
	public void testValidatePlantingSeason_Valid() {
		String validPlantingSeason = "Spring";
		try {
			boolean isValid = PlantValidator.validatePlantingSeason(validPlantingSeason);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail("Unexpected exception thrown for a valid planting season.");
		}
	}

	@Test
	public void testValidatePlantingSeason_Null() {
		String nullPlantingSeason = null;
		CustomException exception = null;
		try {
			PlantValidator.validatePlantingSeason(nullPlantingSeason);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for null planting season");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANTING_SEASON, exception.getMessage());
	}

	@Test
	public void testValidatePlantingSeason_Empty() {
		String emptyPlantingSeason = "";
		CustomException exception = null;
		try {
			PlantValidator.validatePlantingSeason(emptyPlantingSeason);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for empty planting season");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANTING_SEASON, exception.getMessage());
	}

	@Test
	public void testValidatePlantingSeason_Short() {
		String shortPlantingSeason = "Win";
		CustomException exception = null;
		try {
			PlantValidator.validatePlantingSeason(shortPlantingSeason);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for short planting season");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME_LENGTH, exception.getMessage());
	}

	@Test
	public void testValidatePlantingSeason_ContainsNumbers() {
		String invalidPlantingSeason = "Season2023";
		CustomException exception = null;
		try {
			PlantValidator.validatePlantingSeason(invalidPlantingSeason);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for planting season containing numbers");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANTING_SEASON, exception.getMessage());
	}

	// test cases for hybrid enum class

	@Test
	public void testValidateHybrid_Valid() {
		HybridEnum validHybrid = HybridEnum.YES;
		try {
			boolean isValid = PlantValidator.validateHybrid(validHybrid);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail(ErrorMessages.INVALID_HYBRID);
		}
	}

	@Test
	public void testValidateHybrid_Null() {
		HybridEnum nullHybrid = null;
		CustomException exception = null;
		try {
			PlantValidator.validateHybrid(nullHybrid);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for null hybrid");
		Assertions.assertEquals(ErrorMessages.INVALID_HYBRID, exception.getMessage());
	}

}
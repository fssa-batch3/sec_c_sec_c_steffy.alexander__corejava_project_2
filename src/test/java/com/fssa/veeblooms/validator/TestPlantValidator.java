package com.fssa.veeblooms.validator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fssa.veeblooms.CustomException;
import com.fssa.veeblooms.ErrorMessages;
import com.fssa.veeblooms.Plant;
import com.fssa.veeblooms.Enum.HybridEnum;

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
	public void testValidatePlant_InvalidPlantName() {
		List<String> images = new ArrayList<String>();
		images.add("https://www.youtube.com/watch?v=55tCJ8Odjvw");
		images.add("https://learn.facecampus.org/fn/fop-and-dsa-training/#curriculum");
		images.add("https://app.facecampus.org/calendar/");
		images.add("https://chat.openai.com/");
   new Plant(null);
		Plant plantWithInvalidName = new Plant(images, 25.0, 4, "Flowering", 30, "Spring", HybridEnum.NO);

		try {
			PlantValidator.validatePlant(plantWithInvalidName);
			Assertions.fail("Expected CustomException for invalid plant name, but no exception was thrown.");
		} catch (CustomException e) {
			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME, e.getMessage());
		}
	}

	@Test
	public void testValidatePlant_ValidPlant() {
		List<String> images = new ArrayList<String>();
		images.add("https://www.youtube.com/watch?v=55tCJ8Odjvw");
		images.add("https://learn.facecampus.org/fn/fop-and-dsa-training/#curriculum");
		images.add("https://app.facecampus.org/calendar/");
		images.add("https://chat.openai.com/");
		new Plant("rose");
		Plant validPlant = new Plant(images, 25.0, 4, "Flowering", 30, "Spring", HybridEnum.NO);
		try {
			boolean result = PlantValidator.validatePlant(validPlant);
			Assertions.assertTrue(result);
		} catch (CustomException e) {
			Assertions.fail("An exception should not have been thrown for a valid plant.");
		}
	}

	// testcase for plant name

	@Test
	public void testValidatePlantName_ValidName() {
		// Arrange
		String validPlantName = "Sunflower";
		PlantValidator validator = new PlantValidator();

		try {
			validator.validatePlantName(validPlantName);
		} catch (CustomException e) {
			// Assert
			Assertions.fail("Unexpected exception thrown for a valid plant name.");
		}
	}

	@Test
	public void testValidatePlantName_NullName() {
		// Arrange
		String nullPlantName = null;
		PlantValidator validator = new PlantValidator();

		// Act
		CustomException exception = null;
		try {
			validator.validatePlantName(nullPlantName);
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
		PlantValidator validator = new PlantValidator();

		// Act
		CustomException exception = null;
		try {
			validator.validatePlantName(emptyPlantName);
		} catch (CustomException e) {
			exception = e;
		}


		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME, exception.getMessage());
	}

	@Test
	public void testValidatePlantName_ShortName() {
		// Arrange
		String shortPlantName = "AB";
		PlantValidator validator = new PlantValidator();

		// Act
		CustomException exception = null;
		try {
			validator.validatePlantName(shortPlantName);
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

		PlantValidator validator = new PlantValidator();

		try {
			boolean isValid = validator.validatePlantImagesUrl(validUrls);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail(ErrorMessages.INVALID_PLANT_PLANTIMAGESURL);
		}
	}

	@Test
	public void testValidatePlantImagesUrl_NullList() {
		List<String> nullList = null;
		PlantValidator validator = new PlantValidator();

		try {
			validator.validatePlantImagesUrl(nullList);
			Assertions.fail("Should throw a CustomException for a null list.");
		} catch (CustomException e) {
			// Test passes if it catches the CustomException
			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_PLANTIMAGESURL, e.getMessage());
		}
	}

	@Test
	public void testValidatePlantImagesUrl_EmptyList() {
		List<String> emptyList = new ArrayList<String>();
		PlantValidator validator = new PlantValidator();

		try {
			validator.validatePlantImagesUrl(emptyList);
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
		PlantValidator validator = new PlantValidator();

		try {
			boolean isValid = validator.validatePrice(validPrice);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail("Unexpected exception thrown for a valid price.");
		}
	}

	@Test
	public void testValidatePrice_ZeroPrice() {
		double zeroPrice = 0.0;
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validatePrice(zeroPrice);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for zero price");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_PRICE, exception.getMessage());
	}

	@Test
	public void testValidatePrice_NegativePrice() {
		double negativePrice = -10.50;
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validatePrice(negativePrice);
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
		PlantValidator validator = new PlantValidator();

		try {
			boolean isValid = validator.validateRating(validRating);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail(ErrorMessages.INVALID_PLANT_RATING);
		}
	}

	@Test
	public void testValidateRating_ZeroRating() {
		int zeroRating = 0;
		PlantValidator validator = new PlantValidator();

		try {
			validator.validateRating(zeroRating);
		} catch (CustomException e) {

			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_RATING_ZERO, e.getMessage());
		}

	}

	@Test
	public void testValidateRating_NegativeRating() {
		int negativeRating = -2;
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validateRating(negativeRating);
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
		PlantValidator validator = new PlantValidator();

		try {
			boolean isValid = validator.validatePlantType(validPlantType);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail("Unexpected exception thrown for a valid plant type.");
		}
	}

	@Test
	public void testValidatePlantType_Null() {
		String nullPlantType = null;
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validatePlantType(nullPlantType);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for null plant type");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_TYPE, exception.getMessage());
	}

	@Test
	public void testValidatePlantType_Empty() {
		String emptyPlantType = "";
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validatePlantType(emptyPlantType);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for empty plant type");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_TYPE, exception.getMessage());
	}

	@Test
	public void testValidatePlantType_Short() {
		String shortPlantType = "A";
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validatePlantType(shortPlantType);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for short plant type");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_TYPE, exception.getMessage());
	}

	@Test
	public void testValidatePlantType_ContainsNumbers() {
		String invalidPlantType = "Plant123";
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validatePlantType(invalidPlantType);
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
		PlantValidator validator = new PlantValidator();

		try {
			boolean isValid = validator.validatePlantHeight(validPlantHeight);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail("Unexpected exception thrown for a valid plant height.");
		}
	}

	@Test
	public void testValidatePlantHeight_Zero() {
		float zeroPlantHeight = 0.0f;
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validatePlantHeight(zeroPlantHeight);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for zero plant height");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_HEIGHT, exception.getMessage());
	}

	@Test
	public void testValidatePlantHeight_Negative() {
		float negativePlantHeight = -1.5f;
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validatePlantHeight(negativePlantHeight);
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
		PlantValidator validator = new PlantValidator();

		try {
			boolean isValid = validator.validatePlantingSeason(validPlantingSeason);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail("Unexpected exception thrown for a valid planting season.");
		}
	}

	@Test
	public void testValidatePlantingSeason_Null() {
		String nullPlantingSeason = null;
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validatePlantingSeason(nullPlantingSeason);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for null planting season");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANTING_SEASON, exception.getMessage());
	}

	@Test
	public void testValidatePlantingSeason_Empty() {
		String emptyPlantingSeason = "";
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validatePlantingSeason(emptyPlantingSeason);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for empty planting season");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANTING_SEASON, exception.getMessage());
	}

	@Test
	public void testValidatePlantingSeason_Short() {
		String shortPlantingSeason = "Win";
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validatePlantingSeason(shortPlantingSeason);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for short planting season");
		Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME_LENGTH, exception.getMessage());
	}

	@Test
	public void testValidatePlantingSeason_ContainsNumbers() {
		String invalidPlantingSeason = "Season2023";
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validatePlantingSeason(invalidPlantingSeason);
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
		PlantValidator validator = new PlantValidator();

		try {
			boolean isValid = validator.validateHybrid(validHybrid);
			Assertions.assertTrue(isValid);
		} catch (CustomException e) {
			Assertions.fail(ErrorMessages.INVALID_HYBRID);
		}
	}

	@Test
	public void testValidateHybrid_Null() {
		HybridEnum nullHybrid = null;
		PlantValidator validator = new PlantValidator();

		CustomException exception = null;
		try {
			validator.validateHybrid(nullHybrid);
		} catch (CustomException e) {
			exception = e;
		}

//		 "Expected CustomException for null hybrid");
		Assertions.assertEquals(ErrorMessages.INVALID_HYBRID, exception.getMessage());
	}

}
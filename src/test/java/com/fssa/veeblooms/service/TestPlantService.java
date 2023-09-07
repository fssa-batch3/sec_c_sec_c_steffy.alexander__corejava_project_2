package com.fssa.veeblooms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fssa.veeblooms.Enum.HybridEnum;
import com.fssa.veeblooms.Enum.PlantTypeEnum;
import com.fssa.veeblooms.dao.PlantDAO;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.ErrorMessages;
import com.fssa.veeblooms.model.Plant;
import com.fssa.veeblooms.service.PlantService;
import com.fssa.veeblooms.util.Logger;

/**
 * This class contains JUnit test cases for testing the PlantService methods.
 */
public class TestPlantService {

	/**
	 * Test case to validate the addition of a plant using PlantService.
	 * @throws CustomException If there's a custom exception during the test.
	 * @throws SQLException If there's a SQL-related exception during the test.
	 * @throws DAOException If there's a DAO-related exception during the test.
	 */
	@Test
	public void testAddPlant() throws CustomException, SQLException, DAOException {
		// Prepare plant images URLs
		List<String> images = new ArrayList<String>();
		images.add("https://iili.io/HNOKOYb.jpg");
		images.add("https://iili.io/HNOqRgn.jpg");
		images.add("https://iili.io/HNOqRgn.jpg");
		images.add("https://iili.io/HNOoMjj.jpg");
 
		// Create a Plant object
		Plant plant = new Plant();
		plant.setPlantName("pinkrose");
		plant.setPlantImagesUrl(images);
		plant.setPrice(210);
		plant.setPlantType(PlantTypeEnum.PLANT);
		plant.setPlantHeight(3.4f);
		plant.setPlantingSeason("Early Spring");
		plant.setHybrid(HybridEnum.NO);

		Assertions.assertTrue(PlantService.addPlant(plant));
	}

	/**
	 * Test case to validate invalid plant addition using PlantService.
	 * @throws DAOException If there's a DAO-related exception during the test.
	 */
	
	@Test
	public void testInvalidAddPlant() throws DAOException {
		// Create an empty Plant object
		Plant plant = new Plant(); 
		try {
			// Attempt to add the plant using PlantService, expecting an exception
			Assertions.assertTrue(PlantService.addPlant(plant));
		} catch (CustomException | SQLException e) {
			
			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME, e.getMessage());
		}
	}

	/**
	 * Test case to validate the update of a plant using PlantService.
	 * @throws CustomException If there's a custom exception during the test.
	 * @throws SQLException If there's a SQL-related exception during the test.
	 * @throws DAOException If there's a DAO-related exception during the test.
	 */
	@Test
	public void testUpdatePlant() throws CustomException, SQLException, DAOException {
		// Prepare plant images URLs
		List<String> images = new ArrayList<String>();
		images.add("https://iili.io/H8V3vEJ.webp");
		images.add("https://iili.io/H8VFCvV.jpg");
		images.add("https://iili.io/H8VFzj1.jpg");
		images.add("https://iili.io/H8VF4Fj.jpg");

		// Create a Plant object for updating
		Plant plant = new Plant();
		plant.setPlantName("pinkrose");
		plant.setPlantImagesUrl(images);
		plant.setPrice(220);
		plant.setPlantType(PlantTypeEnum.PLANT);
		plant.setPlantHeight(3.4f);
		plant.setPlantingSeason("Spring");
		plant.setHybrid(HybridEnum.NO);

		// Test the update of the plant using PlantService
		Assertions.assertTrue(PlantService.updatePlant(plant));
	}

	/**
	 * Test case to validate invalid plant update using PlantService.
	 * @throws DAOException If there's a DAO-related exception during the test.
	 */
	@Test
	public void testInvalidUpdatePlant() throws DAOException {
		// Create an empty Plant object for updating
		Plant plant = new Plant();
		try {
			
			Assertions.assertTrue(PlantService.updatePlant(plant));
		} catch (CustomException | SQLException e) {
			
			// Validate that the expected exception message matches
			
			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME, e.getMessage());
		}
		}
	@Test 
	public void testDeletePlant() throws CustomException, SQLException, NullPointerException, DAOException {
	
		Plant plant = new Plant();
		plant.setPlantName("pinkrose");
		
		if(PlantDAO.checkplantName(plant.getPlantName())) {
			Assertions.assertTrue(PlantDAO.deletePlantById(PlantDAO.getPlantIdByName(plant.getPlantName())));
		}
		else {
			Assertions.fail(ErrorMessages.PLANT_NOT_EXISTS);
		}
	
	}
 

	/**
	 * Test case to validate fetching plant image URLs using PlantService.
	 * @throws DAOException If there's a DAO-related exception during the test.
	 * @throws SQLException If there's a SQL-related exception during the test.
	 */
	@Test
	public void testGetImageUrl() throws DAOException, SQLException {
		try {
			// Get plant image URLs using PlantService
			List<String> planturl = PlantService.getPlantImageUrls(2);

			for (String url : planturl) {
				Logger.info(url);
			}
		} catch (SQLException e) {
		
			Assertions.fail("Failed" + e.getMessage());
		}
	}

	/**
	 * Test case to validate fetching all plant records using PlantService.
	 * @throws DAOException If there's a DAO-related exception during the test.
	 * @throws SQLException If there's a SQL-related exception during the test.
	 */
	
	@Test 
	public void testGetAllPlants() throws DAOException, SQLException {
		try {
			// Get all plant records using PlantService
			List<Plant> plantList = PlantService.getAllPlants();

			for (Plant url : plantList) {
				Logger.info(url);
			}
		} catch (SQLException e) {
			
			Assertions.fail("Failed" + e.getMessage());
		}
	}
}


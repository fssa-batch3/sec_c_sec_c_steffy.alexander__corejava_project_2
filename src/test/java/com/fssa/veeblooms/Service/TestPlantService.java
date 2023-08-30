package com.fssa.veeblooms.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.fssa.veeblooms.model.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import com.fssa.veeblooms.Enum.HybridEnum;
import com.fssa.veeblooms.dao.PlantDAO;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.ErrorMessages;
import com.fssa.veeblooms.util.Logger;

public class TestPlantService {

	@Test 
	public void testAddPlant() throws CustomException, SQLException, DAOException {
		List<String> images = new ArrayList<String>();
		images.add("https://iili.io/H8VJXet.jpg");
		images.add("https://iili.io/H8Vdzjn.webp");
		images.add("https://iili.io/H8VdYa2.jpg");
		images.add("https://iili.io/H8Vd0u9.jpg");

		Plant plant = new Plant();
		plant.setPlantName("Pomegranate Plant");
		plant.setPlantImagesUrl(images);
		plant.setPrice(110);
//		plant.setRating(4);
		plant.setPlantType("Fruit");
		plant.setPlantHeight(2.5f);
		plant.setPlantingSeason("spring (February-March) and July-August in sub-tropical ");
		plant.setHybrid(HybridEnum.NO);
		Assertions.assertTrue(PlantService.addPlant(plant));
	}

	@Test
	public void testInvalidAddPlant() throws DAOException {
		Plant plant = new Plant();
		try {
			Assertions.assertTrue(PlantService.addPlant(plant));
		} catch (CustomException | SQLException e) {
			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME, e.getMessage());
		}
	}

	@Test
	public void testUpdatePlant() throws CustomException, SQLException, DAOException {
		List<String> images = new ArrayList<String>();
		images.add("https://www.youtube.com/watch?v=55tCJ8Odjvw");
		images.add("https://learn.facecampus.org/fn/fop-and-dsa-training/#curriculum");
		images.add("https://app.facecampus.org/calendar/");
		images.add("https://chat.openai.com/");

		Plant plant = new Plant();
		plant.setPlantName("Pomegranate Plant");
		plant.setPlantImagesUrl(images);
		plant.setPrice(110);
//		plant.setRating(4);
		plant.setPlantType("Flower");
		plant.setPlantHeight(5.2f);
		plant.setPlantingSeason("Autumn");
		plant.setHybrid(HybridEnum.NO);
		Assertions.assertTrue(PlantService.updatePlant(plant, 17));
	}

	@Test
	public void testInvalidUpdatePlant() throws DAOException {
		Plant plant = new Plant();
		try {
			Assertions.assertTrue(PlantService.updatePlant(plant, 16));
		} catch (CustomException | SQLException e) {
			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME, e.getMessage());
		}
	}

	@Test 
	public void testDeletePlant() throws CustomException, SQLException, NullPointerException, DAOException {
		Assertions.assertTrue(PlantDAO.deletePlantById(1));
	}

	@Test
	public void testInvalidDeletePlant() throws NullPointerException, DAOException {
		try {

			int id = 0;
			boolean t = PlantService.deletePlant(id);
			Assertions.assertTrue(t);

		} catch (SQLException e) {
			Assertions.assertEquals("id cannot be zero or negative", e.getMessage());
		}
	}

	@Test
	public void testGetImageUrl() throws DAOException, SQLException {

		try {

			List<String> planturl = PlantService.getPlantImageUrls(2);

			for (String url : planturl) {
				Logger.info(url);
			}

		} catch (SQLException e) {

			Assertions.fail("Failed" + e.getMessage());
		}
	}

	@Test
	public void testAllPlants() throws DAOException, SQLException {

		try {

			List<Plant> plantList = PlantService.getAllPlants();

			for (Plant url : plantList) {
				Logger.info(url);
			}

		} catch (SQLException e) {

			Assertions.fail("Failed" + e.getMessage());
		}
	}
	
}

package com.fssa.veeblooms.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fssa.veeblooms.CustomException;
import com.fssa.veeblooms.ErrorMessages;
import com.fssa.veeblooms.Plant;
import com.fssa.veeblooms.DAO.PlantDAO;
import com.fssa.veeblooms.Enum.HybridEnum;

public class TestPlantService {

	@Test
	public  void testAddPlant() throws CustomException, SQLException {
		List<String> images = new ArrayList<String>();
		images.add("https://www.youtube.com/watch?v=55tCJ8Odjvw");
		images.add("https://learn.facecampus.org/fn/fop-and-dsa-training/#curriculum");
		images.add("https://app.facecampus.org/calendar/");
		images.add("https://chat.openai.com/");

		Plant plant = new Plant();
		plant.setPlantName("mimo plant");
		plant.setPlantImagesUrl(images);
		plant.setPrice(200);
		plant.setRating(4);
		plant.setPlantType("Flower");
		plant.setPlantHeight(5.2f);
		plant.setPlantingSeason("Autumn");
		plant.setHybrid(HybridEnum.YES);
		Assertions.assertTrue(PlantService.addPlant(plant));
	}
	@Test
	public  void testInvalidAddPlant() {
		Plant plant = new Plant();
		try {
			Assertions.assertTrue(PlantService.addPlant(plant));
		} catch (CustomException | SQLException e) {
			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME, e.getMessage());
		}
	}

	@Test
	public  void testUpdatePlant() throws CustomException, SQLException {
		List<String> images = new ArrayList<String>();
		images.add("https://www.youtube.com/watch?v=55tCJ8Odjvw");
		images.add("https://learn.facecampus.org/fn/fop-and-dsa-training/#curriculum");
		images.add("https://app.facecampus.org/calendar/");
		images.add("https://chat.openai.com/");
		
		Plant plant = new Plant();
		plant.setPlantName("rose plant");
		plant.setPlantImagesUrl(images);
		plant.setPrice(290);
		plant.setRating(5);
		plant.setPlantType("Flower");
		plant.setPlantHeight(5.2f);
		plant.setPlantingSeason("Autumn");
		plant.setHybrid(HybridEnum.YES);
		Assertions.assertTrue(PlantService.updatePlant(plant,17));
	}

	@Test
	public void testInvalidUpdatePlant() {
		Plant plant = new Plant();
		try {
			Assertions.assertTrue(PlantService.updatePlant(plant,16));
		} catch (CustomException | SQLException e) {
			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME, e.getMessage());
		}
	}

	@Test
	public void testDeletePlant() throws CustomException, SQLException {
		Assertions.assertTrue(PlantDAO.deletePlantById(16));
	}

	@Test
	public void testInvalidDeletePlant() {
		try {
			Assertions.assertTrue(PlantDAO.deletePlantById(0));
		} catch (CustomException | SQLException e) {
			Assertions.assertEquals("id cannot be zero or negative", e.getMessage());
		}
	}
}

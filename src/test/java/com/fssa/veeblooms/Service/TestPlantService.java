package com.fssa.veeblooms.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.fssa.veeblooms.model.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import com.fssa.veeblooms.Enum.HybridEnum;
import com.fssa.veeblooms.Enum.PlantTypeEnum;
import com.fssa.veeblooms.dao.PlantDAO;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.ErrorMessages;
import com.fssa.veeblooms.util.Logger;

public class TestPlantService {

	@Test 
	public void testAddPlant() throws CustomException, SQLException, DAOException {
		List<String> images = new ArrayList<String>();
		images.add("https://iili.io/H8V3vEJ.webp");
		images.add("https://iili.io/H8VFCvV.jpg");
		images.add("https://iili.io/H8VFzj1.jpg");
		images.add("https://iili.io/H8VF4Fj.jpg");
 
		Plant plant = new Plant();
		plant.setPlantName("Gerberaaa Plant");
		plant.setPlantImagesUrl(images);
		plant.setPrice(160);
		plant.setPlantType(PlantTypeEnum.FLOWER);
		plant.setPlantHeight(5.4f);
		plant.setPlantingSeason("Spring");
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
		images.add("https://iili.io/H8V3vEJ.webp");
		images.add("https://iili.io/H8VFCvV.jpg");
		images.add("https://iili.io/H8VFzj1.jpg");
		images.add("https://iili.io/H8VF4Fj.jpg");

		Plant plant = new Plant();
		plant.setPlantName("Gerbera Plant");
		plant.setPlantImagesUrl(images);
		plant.setPrice(195);
//		plant.setRating(4);
		plant.setPlantType(PlantTypeEnum.FLOWER);
		plant.setPlantHeight(3.4f);
		plant.setPlantingSeason("Spring");
		plant.setHybrid(HybridEnum.NO);
		Assertions.assertTrue(PlantService.updatePlant(plant));
	}

	@Test
	public void testInvalidUpdatePlant() throws DAOException {
		Plant plant = new Plant();
		try {
			Assertions.assertTrue(PlantService.updatePlant(plant));
		} catch (CustomException | SQLException e) {
			Assertions.assertEquals(ErrorMessages.INVALID_PLANT_NAME, e.getMessage());
		}
	}

	@Test 
	public void testDeletePlant() throws CustomException, SQLException, NullPointerException, DAOException {
		Assertions.assertTrue(PlantDAO.deletePlantById(4));
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
	public void testGetAllPlants() throws DAOException, SQLException {

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

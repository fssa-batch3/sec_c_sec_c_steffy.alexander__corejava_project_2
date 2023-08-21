package com.fssa.veeblooms.Service;

import java.sql.SQLException;

import com.fssa.veeblooms.CustomException;
import com.fssa.veeblooms.Plant;
import com.fssa.veeblooms.dao.PlantDAO;
import com.fssa.veeblooms.validator.PlantValidator;

public class PlantService {

	private static PlantValidator plantValidator;
	private static PlantDAO plantDAO;

	PlantService(PlantValidator plantValidator, PlantDAO plantDAO) {
		PlantService.setPlantValidator(plantValidator);
		PlantService.setPlantDAO(plantDAO);
	}

	public static boolean addPlant(Plant plant) throws CustomException, SQLException {

		if (PlantValidator.validatePlant(plant)) {

			PlantDAO.addPlant(plant);

		}

		return true;

	}

	public static boolean updatePlant(Plant plant, int plantId) throws CustomException, SQLException {

		if (PlantValidator.validatePlant(plant)) {

			PlantDAO.updatePlant(plant, plantId);

		}

		return true;

	}

	public static boolean deletePlant(int id) throws CustomException, SQLException {

		PlantDAO.deletePlantById(8);

		return true;

	}

	public static PlantValidator getPlantValidator() {
		return plantValidator;
	}

	public static void setPlantValidator(PlantValidator plantValidator) {
		PlantService.plantValidator = plantValidator;
	}

	public static PlantDAO getPlantDAO() {
		return plantDAO;
	}

	public static void setPlantDAO(PlantDAO plantDAO) {
		PlantService.plantDAO = plantDAO;
	}

}

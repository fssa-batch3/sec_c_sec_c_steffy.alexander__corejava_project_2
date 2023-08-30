package com.fssa.veeblooms.Service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.veeblooms.dao.PlantDAO;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.Plant;
import com.fssa.veeblooms.validator.PlantValidator;

public class PlantService {

	private static PlantValidator plantValidator;
	private static PlantDAO plantDAO;

	public PlantService(PlantValidator plantValidator, PlantDAO plantDAO) {
		PlantService.setPlantValidator(plantValidator);
		PlantService.setPlantDAO(plantDAO);
	}



	public static boolean addPlant(Plant plant) throws CustomException, DAOException, SQLException {

		if (PlantValidator.validatePlant(plant)) {

			PlantDAO.addPlant(plant);

		}

		return true;

	}

	public static boolean updatePlant(Plant plant) throws DAOException, SQLException, CustomException {

		if (PlantValidator.validatePlant(plant)) {

			PlantDAO.updatePlant(plant);

		}

		return true;

	}

	public static boolean deletePlant(int id) throws DAOException, SQLException {

		PlantDAO.deletePlantById(id);

		return true;

	}

	public static void setPlantValidator(PlantValidator plantValidator) {
		PlantService.plantValidator = plantValidator;
	}


	public static void setPlantDAO(PlantDAO plantDAO) {
		PlantService.plantDAO = plantDAO;
	}

	public static List<String> getPlantImageUrls(int plantid) throws DAOException, SQLException {

		return PlantDAO.getPlantImageUrls(plantid);
	}

	public static List<Plant> getAllPlants() throws DAOException, SQLException {

		return PlantDAO.getAllPlant();
	}
	

	public static int getPlantIdByName(String name) throws DAOException, SQLException {

		return PlantDAO.getPlantIdByName(name);
	}
	
	
	public static Plant getPlantById(int id) throws DAOException, SQLException {

		return PlantDAO.getPlantById(id);
	}
	
	
}

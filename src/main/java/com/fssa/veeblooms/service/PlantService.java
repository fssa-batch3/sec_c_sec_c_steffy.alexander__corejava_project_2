package com.fssa.veeblooms.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.veeblooms.dao.PlantDAO;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.Plant;
import com.fssa.veeblooms.validator.PlantValidator;

public class PlantService {

	// Private static fields for the PlantValidator and PlantDAO
	
	
	private static PlantValidator plantValidator;
	private static PlantDAO plantDAO;

	// Constructor for the PlantService class
	
	public PlantService(PlantValidator plantValidator, PlantDAO plantDAO) {
		
		
		// Setting the PlantValidator and PlantDAO using setters
		
		
		PlantService.setPlantValidator(plantValidator);
		PlantService.setPlantDAO(plantDAO);
	}
	
	
	public PlantService() {
		
	}

	// Method to add a plant
	
	public static boolean addPlant(Plant plant) throws CustomException, DAOException, SQLException {
		if (PlantValidator.validatePlant(plant)) {
			PlantDAO.addPlant(plant);
		}
		return true;
	}

	// Method to update a plant
	
	public static boolean updatePlant(Plant plant) throws DAOException, SQLException, CustomException {
		if (PlantValidator.validatePlant(plant)) {
			PlantDAO.updatePlant(plant);
		}
		return true;
	}

	// Method to delete a plant
	
	public static boolean deletePlant(int id) throws DAOException, SQLException {
		PlantDAO.deletePlantById(id);
		return true;
	}

	// Setter for PlantValidator
	
	public static void setPlantValidator(PlantValidator plantValidator) {
		PlantService.plantValidator = plantValidator;
	}

	// Setter for PlantDAO
	
	public static void setPlantDAO(PlantDAO plantDAO) {
		PlantService.plantDAO = plantDAO;
	}

	// Method to get image URLs of a plant
	
	public static List<String> getPlantImageUrls(int plantid) throws DAOException, SQLException {
		return PlantDAO.getPlantImageUrls(plantid);
	}

	// Method to get a list of all plants
	
	public static List<Plant> getAllPlants() throws DAOException, SQLException {
		return PlantDAO.getAllPlant();
	}

	// Method to get the plant ID by its name
	
	public static int getPlantIdByName(String name) throws DAOException, SQLException {
		return PlantDAO.getPlantIdByName(name);
	}

	// Method to get a plant by its ID
	
	public static Plant getPlantById(int id) throws DAOException, SQLException {
		return PlantDAO.getPlantById(id);
	}
}

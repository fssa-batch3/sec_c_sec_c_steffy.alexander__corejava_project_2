package com.fssa.veeblooms.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.veeblooms.CustomException;
import com.fssa.veeblooms.Plant;
import com.fssa.veeblooms.DAO.PlantDAO;
import com.fssa.veeblooms.Enum.HybridEnum;
import com.fssa.veeblooms.validator.PlantValidator;

public class PlantService {
	
	
	private static PlantValidator plantValidator;
	private static PlantDAO plantDAO;

	public PlantService(PlantValidator plantValidator,PlantDAO plantDAO) {
		this.plantValidator=plantValidator;
		this.plantDAO=plantDAO;
	}
	 
	public static boolean addPlant(Plant plant) throws CustomException, SQLException {

		if (plantValidator.validatePlant(plant)) {

				plantDAO.addPlant(plant);
			
		}

		return true;

	}
	public static boolean updatePlant(Plant plant,int plantId) throws CustomException, SQLException {

		if (plantValidator.validatePlant(plant)) {

				plantDAO.updatePlant(plant,plantId);
			
		}

		return true;

	}
	public static boolean deletePlant(int id) throws CustomException, SQLException {



				PlantDAO.deletePlantById(8);
			


		return true;

	}
	

	

}

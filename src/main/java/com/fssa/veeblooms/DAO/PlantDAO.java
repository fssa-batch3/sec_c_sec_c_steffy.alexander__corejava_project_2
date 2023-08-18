package com.fssa.veeblooms.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.logger.Logger;
import com.fssa.veeblooms.CustomException;
import com.fssa.veeblooms.Plant;
import com.fssa.veeblooms.Enum.HybridEnum;

public class PlantDAO {

	public static void addPlant(Plant plant) throws CustomException, SQLException {

		if (PlantDAO.CheckplantName(plant.getPlantName())) {

			throw new CustomException("Plant name already exists: " + plant.getPlantName());

		}
		Connection connection = null;

		try {
			String insertQuery = "INSERT INTO plant (plantName, price, rating, plantType, plantHeight, plantingSeason, hybrid) VALUES (?, ?, ?, ?, ?, ?, ?)";
			connection = ConnectionUtil.getConnection();

			// Execute insert statement
		
			try(PreparedStatement pst = connection.prepareStatement(insertQuery)){

			pst.setString(1, plant.getPlantName());
			pst.setDouble(2, plant.getPrice());
			pst.setInt(3, plant.getRating());
			pst.setString(4, plant.getPlantType());
			pst.setFloat(5, plant.getPlantHeight());
			pst.setString(6, plant.getPlantingSeason());
			pst.setString(7, plant.getHybrid().toString());

			int row_affected = pst.executeUpdate();

			Logger.info("row/rows affected: " + row_affected);

			addImageUrl(plant);
			}

		} catch (SQLException e) {
			throw new CustomException("Error creating plant", e);
		} finally {
			// close connection

			if (connection != null) {
				connection.close();
			}

		}
	}

	public static boolean CheckplantName(String plantName) throws CustomException, SQLException {
		Connection connection = null;
		try {
			// Create update statement using task id
			String query = "SELECT COUNT(*) FROM plant WHERE plantName = ? ";// ?=hibiscus....count=1
			connection = ConnectionUtil.getConnection();
			try(PreparedStatement pst = connection.prepareStatement(query)){
			pst.setString(1, plantName);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1); // count=1

				if (count > 0) {

					return true;
					// 1 > 0 ===> true
				} else {
					return false;
					// 0 > 0 ===> false
				}
			}			}
		} catch (SQLException e) {
			throw new CustomException("Error while checking whether the plant already exists", e);

		} finally {
			// close connection

			if (connection != null) {
				connection.close();
			}

		}
		return false;
	}

	// This method is used for getting id from the database by using plant name

	public static int getPlantIdByName(String plantName) throws CustomException, SQLException {

		Connection connection = null;

		try {
			// Create update statement using task id
			connection = ConnectionUtil.getConnection();

			String query = "SELECT plant_id FROM plant WHERE plantName = ? ";

			try(PreparedStatement pst = connection.prepareStatement(query)){

			pst.setString(1, plantName);

		
			Logger.info(pst);

			ResultSet rs = pst.executeQuery();

			// .executeQuery() .. returns result set
			// .executeUpdate() .. returns no of rows affected

			int id = 0;

			while (rs.next()) {

				id = rs.getInt("plant_id");
			}

			return id;
			}
		} catch (SQLException e) {
			throw new CustomException("Error getting id by plant name", e);
		} finally {

			if (connection != null) {
				connection.close();
			}

		}

	}

	public static boolean addImageUrl(Plant plant) throws CustomException, SQLException {

		Connection connection = null;
		try {
			// Create update statement using task id
			int id = getPlantIdByName(plant.getPlantName());
			System.out.println(id + "iujy");
			connection = ConnectionUtil.getConnection();
			for (String url : plant.getPlantImagesUrl()) {
				String query = "INSERT INTO plantimagesurl(plant_id,image_url) VALUES (?,?)";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setInt(1, id);
				pst.setString(2, url);
				pst.executeUpdate();
				pst.close();
			}

		} catch (SQLException e) {
			throw new CustomException("Error in adding plant image urls ", e);
		} finally {
			// close connection

			if (connection != null) {
				connection.close();
			}

		}

		return true;
	}

	public static void updatePlant(Plant plant, int plantId) throws CustomException, SQLException {
		Connection connection = null;
		try {
			// Create update statement using task id
			String updatequery = "UPDATE plant SET plantName = ?, price = ?, rating = ?, plantType = ?, plantHeight = ?, plantingSeason = ?, hybrid = ? WHERE plant_id = ?";
			connection = ConnectionUtil.getConnection();
			PreparedStatement pst = connection.prepareStatement(updatequery);
			pst.setString(1, plant.getPlantName());
			pst.setDouble(2, plant.getPrice());
			pst.setInt(3, plant.getRating());
			pst.setString(4, plant.getPlantType());
			pst.setFloat(5, plant.getPlantHeight());
			pst.setString(6, plant.getPlantingSeason()); 
			pst.setString(7, plant.getHybrid().toString());
			pst.setInt(8, plantId);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new CustomException("Error updating plant", e);
		} finally {
			// close connection

			if (connection != null) {
				connection.close();
			}

		}
	}

	public static boolean deletePlantById(int plant_ids) throws CustomException, SQLException {
		if(plant_ids <=0) {
			throw new CustomException("id cannot be zero or negative");
		}
		Connection connection = null;
		java.sql.CallableStatement callableStatement = null;
		try {
			String query = "{call DeletePlants(?)}";


			connection = ConnectionUtil.getConnection();


			callableStatement = connection.prepareCall(query);

			callableStatement.setInt(1, plant_ids);

			callableStatement.execute();
			Logger.info("deleted");
			return true;
		} catch (SQLException e) {
			throw new CustomException("Error deleting plant", e); 
		} finally {
			// close connection

			if (connection != null) {
				connection.close();
			}

		}
	}
	
	
	
	
	public static void main(String[] args) throws CustomException, SQLException {
		
		List<String> images = new ArrayList<String>();
		images.add("https://www.youtube.com/watch?v=55tCJ8Odjvw");
		images.add("https://learn.facecampus.org/fn/fop-and-dsa-training/#curriculum");
		images.add("https://app.facecampus.org/calendar/");
		images.add("https://chat.openai.com/");
		
		Plant plant = new Plant();
		plant.setPlantName("rose plant");
		plant.setPlantImagesUrl(images);
		plant.setPrice(250);
		plant.setRating(5);
		plant.setPlantType("Flower");
		plant.setPlantHeight(5.2f);
		plant.setPlantingSeason("Autumn");
		plant.setHybrid(HybridEnum.YES);
		
		updatePlant(plant, 17);
	}
}
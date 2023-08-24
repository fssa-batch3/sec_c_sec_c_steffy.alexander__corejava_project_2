package com.fssa.veeblooms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.ErrorMessages;
import com.fssa.veeblooms.model.Plant;
import com.fssa.veeblooms.util.ConnectionUtil;
import com.fssa.veeblooms.util.Logger;


public class PlantDAO {

	public static void addPlant(Plant plant) throws DAOException, SQLException {

		if (PlantDAO.checkplantName(plant.getPlantName())) {


			throw new DAOException(ErrorMessages.INVALID_PLANTNAME_ALREADY_EXISTS+ plant.getPlantName());

		}

		try (Connection connection = ConnectionUtil.getConnection()) {
			String insertQuery = "INSERT INTO plant (plantName, price, rating, plantType, plantHeight, plantingSeason, hybrid) VALUES (?, ?, ?, ?, ?, ?, ?)";

			// Execute insert statement

			try (PreparedStatement pst = connection.prepareStatement(insertQuery)) {

				pst.setString(1, plant.getPlantName());
				pst.setDouble(2, plant.getPrice());
				pst.setInt(3, plant.getRating());
				pst.setString(4, plant.getPlantType());
				pst.setFloat(5, plant.getPlantHeight());
				pst.setString(6, plant.getPlantingSeason());
				pst.setString(7, plant.getHybrid().toString());

				int rowaffected = pst.executeUpdate();

				Logger.info("row/rows affected: " + rowaffected);

				addImageUrl(plant);
			}

		} catch (SQLException e) {
			throw new DAOException(ErrorMessages.INVALID_PLANT_CREATING, e);
		}
	}

	public static boolean checkplantName(String plantName) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			// Create update statement using task id
			String query = "SELECT COUNT(*) FROM plant WHERE plantName = ? ";// ?=hibiscus....count=1
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, plantName);

				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					int count = rs.getInt(1); // count=1

					if (count > 0) {
						return true;
					}

					// 1 > 0 ===> true
					return false;
					// 0 > 0 ===> false
				}
			}
		} catch (SQLException e) {
			throw new DAOException(ErrorMessages.INVALID_PLANT_CHECKING_ERROR, e);

		}
		return false;
	}

	// This method is used for getting id from the database by using plant name

	public static int getPlantIdByName(String plantName) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// Create update statement using task id

			String query = "SELECT plant_id FROM plant WHERE plantName = ? ";

			try (PreparedStatement pst = connection.prepareStatement(query)) {

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
			throw new DAOException(ErrorMessages.ERROR_GETTING_PLANTNAME, e);
		}
	}

	public static boolean addImageUrl(Plant plant) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// Create update statement using task id
			int id = getPlantIdByName(plant.getPlantName());
			Logger.info(id + "iujy");
			
			for (String url : plant.getPlantImagesUrl()) {
				String query = "INSERT INTO plantimagesurl(plant_id,image_url) VALUES (?,?)";
				try (PreparedStatement pst = connection.prepareStatement(query)) {
					pst.setInt(1, id);
					pst.setString(2, url);
					pst.executeUpdate();
				}

			}
		} catch (SQLException e) {
			throw new DAOException(ErrorMessages.ERROR_ADDING_IMAGEURL, e);

		}

		return true;
	}

	public static void updatePlant(Plant plant, int plantId) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// Create update statement using task id
			String updatequery = "UPDATE plant SET plantName = ?, price = ?, rating = ?, plantType = ?, plantHeight = ?, plantingSeason = ?, hybrid = ? WHERE plant_id = ?";

			try (PreparedStatement pst = connection.prepareStatement(updatequery)) {
				pst.setString(1, plant.getPlantName());
				pst.setDouble(2, plant.getPrice());
				pst.setInt(3, plant.getRating());
				pst.setString(4, plant.getPlantType());
				pst.setFloat(5, plant.getPlantHeight());
				pst.setString(6, plant.getPlantingSeason());
				pst.setString(7, plant.getHybrid().toString());
				pst.setInt(8, plantId);
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DAOException("Error updating plant", e);
		}
	}

	public static boolean deletePlantById(int plantIds) throws DAOException, SQLException, NullPointerException {
		if (plantIds <= 0) {
			throw new DAOException("id cannot be zero or negative");
		}

		try (Connection connection = ConnectionUtil.getConnection();) {
			String query = "{call DeletePlants(?)}";

			try (CallableStatement cStatement = connection.prepareCall(query)) {

				cStatement.setInt(1, plantIds);

				cStatement.execute();
				Logger.info("deleted");
				return true;
			}
		} catch (SQLException e) {
			throw new DAOException("Error deleting plant", e);
		}
	}

//	public static void main(String[] args) throws CustomException, SQLException {
//
//		List<String> images = new ArrayList<>();
//		images.add("https://www.youtube.com/watch?v=55tCJ8Odjvw");
//		images.add("https://learn.facecampus.org/fn/fop-and-dsa-training/#curriculum");
//		images.add("https://app.facecampus.org/calendar/");
//		images.add("https://chat.openai.com/");
//
//		Plant plant = new Plant();
//		plant.setPlantName("rose plant");
//		plant.setPlantImagesUrl(images);
//		plant.setPrice(250);
//		plant.setRating(5);
//		plant.setPlantType("Flower");
//		plant.setPlantHeight(5.2f);
//		plant.setPlantingSeason("Autumn");
//		plant.setHybrid(HybridEnum.YES);
//
//		updatePlant(plant, 17);
//	}
}
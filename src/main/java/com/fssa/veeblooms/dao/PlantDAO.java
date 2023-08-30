package com.fssa.veeblooms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.veeblooms.Enum.HybridEnum;
import com.fssa.veeblooms.Enum.PlantTypeEnum;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.ErrorMessages;
import com.fssa.veeblooms.model.Plant;
import com.fssa.veeblooms.util.ConnectionUtil;
import com.fssa.veeblooms.util.Logger;

public class PlantDAO {

	public static void addPlant(Plant plant) throws DAOException, SQLException {
		if (PlantDAO.checkplantName(plant.getPlantName())) {

			throw new DAOException(ErrorMessages.INVALID_PLANTNAME_ALREADY_EXISTS + plant.getPlantName());

		}

		try (Connection connection = ConnectionUtil.getConnection()) {
			String insertQuery = "INSERT INTO plant (plantName, price,  plantType, plantHeight, plantingSeason, hybrid) VALUES (?, ?, ?, ?, ?, ?)";

			// Execute insert statement

			try (PreparedStatement pst = connection.prepareStatement(insertQuery)) {

				pst.setString(1, plant.getPlantName());
				pst.setDouble(2, plant.getPrice());
				pst.setString(3, plant.getPlantType() + "");
				pst.setFloat(4, plant.getPlantHeight());
				pst.setString(5, plant.getPlantingSeason());
				pst.setString(6, plant.getHybrid().toString());

				int rowaffected = pst.executeUpdate();

				Logger.info("row/rows affected: " + rowaffected);

				addImageUrl(plant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(ErrorMessages.INVALID_PLANT_CREATING);
		}
	}

	public static boolean checkplantName(String plantName) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			// Create update statement using task id
			String query = "SELECT * FROM plant WHERE plantName = ? ";// ?=hibiscus....count=1
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, plantName);

				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						// count=1
						return true;

					} else {
						// 1 > 0 ===> true
						return false;
						// 0 > 0 ===> false
					}
				}

			}
		} catch (SQLException e) {
			throw new DAOException(ErrorMessages.INVALID_PLANT_CHECKING_ERROR + e.getMessage());

		}

	}

	// This method is used for getting id from the database by using plant name

	public static int getPlantIdByName(String plantName) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// Create update statement using task id

			String query = "SELECT plant_id FROM plant WHERE plantName = ? ";

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setString(1, plantName);

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

	public static List<String> getPlantImageUrls(int plantId) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			List<String> urlList = new ArrayList<>();

			String query = "SELECT image_url FROM plantimagesurl WHERE plant_id = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setInt(1, plantId);

				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					urlList.add(rs.getString("image_url"));
				}
			}
			return urlList;

		} catch (SQLException e) {
			throw new DAOException("Error getting plant image URLs", e);
		}
	}

	public static void updatePlant(Plant plant) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// Create update statement using task id
			String updatequery = "UPDATE plant SET plantName = ?, price = ?, plantType = ?, plantHeight = ?, plantingSeason = ?, hybrid = ? WHERE plant_id = ?";

			try (PreparedStatement pst = connection.prepareStatement(updatequery)) {
				pst.setString(1, plant.getPlantName());
				pst.setDouble(2, plant.getPrice());
//				pst.setInt(3, plant.getRating());
				pst.setString(3, plant.getPlantType() + "");
				pst.setFloat(4, plant.getPlantHeight());
				pst.setString(5, plant.getPlantingSeason());
				pst.setString(6, plant.getHybrid().toString());
				pst.setInt(7, getPlantIdByName(plant.getPlantName()));
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

	//////
	public static Plant getPlantById(int id) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// Create update statement using task id

			String query = "SELECT * FROM plant WHERE plant_id = ? ";

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, id);

				ResultSet rs = pst.executeQuery();

				Plant plant = new Plant();

				while (rs.next()) {

					plant.setPlantName(rs.getString("plantName"));
					plant.setPrice(rs.getDouble("price"));
					plant.setPlantingSeason(rs.getString("plantingSeason"));
					plant.setPlantType(PlantTypeEnum.valueOf(rs.getString("plantType").toUpperCase()));
					plant.setPlantHeight(rs.getFloat("plantHeight"));
//					plant.setRating(rs.getInt("rating"));
					plant.setHybrid(HybridEnum.valueOf(rs.getString("hybrid").toUpperCase()));
				}

				return plant;
			}
		} catch (SQLException e) {
			throw new DAOException(ErrorMessages.ERROR_GETTING_PLANTID, e);
		}
	}

	/////////////
	public static List<Plant> getAllPlant() throws DAOException, SQLException {

		// Created a List Object
		List<Plant> plantProductList = new ArrayList<>();

		try (Connection connection = ConnectionUtil.getConnection()) {

			final String query = "select * from plant";

			try (Statement st = connection.createStatement()) {

				try (ResultSet rs = st.executeQuery(query)) {

					while (rs.next()) {

						Plant plant = createPlantFromResultSet(rs);
						plantProductList.add(plant);

					}

				}

			} catch (SQLException e) {
				throw new DAOException("Error for Get all product by store Method is Failed", e);
			}

			// Returning a product list (ArrayList).
			return plantProductList;

		}
	}

	public static Plant createPlantFromResultSet(ResultSet rs) throws SQLException, DAOException {

		Plant plant = new Plant();
		int plantId = getPlantIdByName(rs.getString("plantName"));
		plant.setPlantId(plantId);
		plant.setPlantName(rs.getString("plantName"));
		plant.setPlantImagesUrl(getPlantImageUrls(plantId));
		plant.setPrice(rs.getDouble("price"));
//		plant.setRating(rs.getInt("rating"));
		plant.setPlantType(PlantTypeEnum.valueOf(rs.getString("plantType").toUpperCase()));
		plant.setPlantHeight(rs.getFloat("plantHeight"));
		plant.setPlantingSeason(rs.getString("plantingSeason"));
		plant.setHybrid(HybridEnum.valueOf(rs.getString("hybrid").toUpperCase()));

		// Returning the product object.
		return plant;

	}

}

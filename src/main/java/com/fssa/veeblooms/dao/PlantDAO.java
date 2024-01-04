package com.fssa.veeblooms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.veeblooms.enumclass.HybridEnum;
import com.fssa.veeblooms.enumclass.PlantTypeEnum;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.ErrorMessages;
import com.fssa.veeblooms.model.Plant;
import com.fssa.veeblooms.util.ConnectionUtil;
import com.fssa.veeblooms.util.Logger;

public class PlantDAO {

	/**
	 * Adds a new plant to the database if the plant name does not already exist.
	 *
	 * @param plant The Plant object containing the information of the plant to be
	 *              added.
	 * @throws DAOException If there is an issue with the DAO operations.
	 * @throws SQLException If a database access error occurs.
	 */

	public static void addPlant(Plant plant) throws DAOException, SQLException {
		// Check if the plant name already exists in the database
		if (PlantDAO.checkplantName(plant.getPlantName())) {

			throw new DAOException(ErrorMessages.INVALID_PLANTNAME_ALREADY_EXISTS + plant.getPlantName());

		}

		try (Connection connection = ConnectionUtil.getConnection()) {
			// SQL query to insert the plant information into the 'plant' table
			String insertQuery = "INSERT INTO plant (plantName, price,  plantType, plantHeight, plantingSeason, hybrid, is_available) VALUES (?, ?, ?, ?, ?, ?,?)";

			// Execute insert statement

			try (PreparedStatement pst = connection.prepareStatement(insertQuery)) {

				pst.setString(1, plant.getPlantName());
				pst.setDouble(2, plant.getPrice());
				pst.setString(3, plant.getPlantType() + "");
				pst.setFloat(4, plant.getPlantHeight());
				pst.setString(5, plant.getPlantingSeason());
				pst.setString(6, plant.getHybrid().toString());
				pst.setInt(7, 1);
				// Execute the insert statement and get the number of affected rows

				int rowaffected = pst.executeUpdate();

				Logger.info("row/rows affected: " + rowaffected);

				addImageUrl(plant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(ErrorMessages.INVALID_PLANT_CREATING);
		}
	}

	/**
	 * Checks if a plant with the specified name exists in the database.
	 *
	 * @param plantName The name of the plant to be checked.
	 * @return {@code true} if a plant with the given name exists, {@code false}
	 *         otherwise.
	 * @throws DAOException If there is an issue with the DAO operations.
	 * @throws SQLException If a database access error occurs.
	 * 
	 */

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

	/**
	 * Retrieves the plant ID associated with the given plant name from the da
	 * tabase.
	 *
	 * @param plantName The name of the plant for which the ID needs to be
	 *                  retrieved.
	 * @return The plant ID associated with the specified name.
	 * @throws DAOException If there is an issue with the DAO operations.
	 * @throws SQLException If a database access error occurs.
	 */

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

	/**
	 * Adds image URLs associated with a plant to the database.
	 *
	 * @param plant The Plant object containing the information of the plant and its
	 *              image URLs.
	 * @return {@code true} if the image URLs were successfully added, {@code false}
	 *         otherwise.
	 * @throws DAOException If there is an issue with the DAO operations.
	 * @throws SQLException If a database access error occurs.
	 */

	public static boolean addImageUrl(Plant plant) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// Create update statement using task id

			// Retrieve the plant ID based on the plant name
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
	
	
	public static List<Integer> getPlantImageUrlId(int plantId) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			List<Integer> urlIdList = new ArrayList<>();

			String query = "SELECT url_id FROM plantimagesurl WHERE plant_id = ?";
			
		
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setInt(1, plantId);

				ResultSet rs = pst.executeQuery();

				while (rs.next()) {
					urlIdList.add(rs.getInt("url_id"));
				}
			}
			return urlIdList;

		} catch (SQLException e) {
			throw new DAOException("Error getting plant image URLs"+ e.getMessage());
		}
	}
	 
	

	public static boolean updateImageUrl(Plant plant) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {

			List<String> plantList = plant.getPlantImagesUrl();
			List<Integer> plantListId = getPlantImageUrlId(plant.getPlantId());
			for (int i=0;i<plantList.size();i++) {
				String query = "UPDATE plantimagesurl SET image_url = ? WHERE url_id=? ";

				try (PreparedStatement pst = connection.prepareStatement(query)) {
					pst.setString(1,plantList.get(i));
					pst.setInt(2, plantListId.get(i));
					pst.executeUpdate();
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(ErrorMessages.ERROR_ADDING_IMAGEURL+ e.getMessage());

		}

		return true;
	}

	/**
	 * Retrieves the image URLs associated with a plant from the database.
	 *
	 * @param plantId The ID of the plant for which image URLs need to be retrieved.
	 * @return A List of image URLs associated with the specified plant ID.
	 * @throws DAOException If there is an issue with the DAO operations.
	 * @throws SQLException If a database access error occurs.
	 * 
	 */

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

	/**
	 * Updates the information of a plant in the database with the new information
	 * provided.
	 *
	 * @param plant The Plant object containing the updated information for the
	 *              plant.
	 * @throws DAOException If there is an issue with the DAO operations.
	 * @throws SQLException If a database access error occurs.
	 */

	public static void updatePlant(Plant plant) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// Create update statement using task id
			// SQL query to update the plant information based on the plant ID
			String updatequery = "UPDATE plant SET plantName = ?, price = ?, plantType = ?, plantHeight = ?, plantingSeason = ?, hybrid = ?,is_available=? WHERE plant_id = ?";

			
			int id= getPlantIdByName(plant.getPlantName());
			plant.setPlantId(id);
			try (PreparedStatement pst = connection.prepareStatement(updatequery)) {
				pst.setString(1, plant.getPlantName());
				pst.setDouble(2, plant.getPrice());
//				pst.setInt(3, plant.getRating());
				pst.setString(3, plant.getPlantType() + "");
				pst.setFloat(4, plant.getPlantHeight());
				pst.setString(5, plant.getPlantingSeason());
				pst.setString(6, plant.getHybrid().toString());
				pst.setInt(7, 1);
				pst.setInt(8, id);

				System.out.println(pst);
				pst.executeUpdate();
				updateImageUrl(plant);
			}
		} catch (SQLException e) {
			throw new DAOException("Error updating plant", e);
		}
	}

	/**
	 * Deletes a plant from the database based on the provided plant ID.
	 *
	 * @param plantIds The ID of the plant to be deleted.
	 * @return {@code true} if the plant was successfully deleted, {@code false}
	 *         otherwise.
	 * @throws DAOException         If there is an issue with the DAO operations or
	 *                              if the plant ID is invalid.
	 * @throws SQLException         If a database access error occurs.
	 * @throws NullPointerException If the provided plant ID is null.
	 */

	public static boolean deletePlantById(int plantIds) throws DAOException, SQLException, NullPointerException {

		// Check if the provided plant ID is valid
		if (plantIds <= 0) {
			throw new DAOException("id cannot be zero or negative");
		}

		try (Connection connection = ConnectionUtil.getConnection()) {
			// SQL query to call a stored procedure to delete plants by ID
			String query = "{call DeletePlants(?)}";

			try (CallableStatement cStatement = connection.prepareCall(query)) {
				// Set the plant ID parameter for the callable statement
				cStatement.setInt(1, plantIds);

				// Execute the callable statement to delete the plant
				cStatement.execute();
				Logger.info("deleted");
				return true;
			}
		} catch (SQLException e) {
			throw new DAOException("Error deleting plant", e);
		}
	}

	/**
	 * Retrieves a plant's information from the database based on the provided plant
	 * ID.
	 *
	 * @param id The ID of the plant for which information needs to be retrieved.
	 * @return A Plant object containing the information of the specified plant.
	 * @throws DAOException If there is an issue with the DAO operations.
	 * @throws SQLException If a database access error occurs.
	 */

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
					plant.setHybrid(HybridEnum.valueOf(rs.getString("hybrid").toUpperCase()));
					plant.setPlantImagesUrl(getPlantImagesById(id));
				}

				return plant;
			}
		} catch (SQLException e) {
			throw new DAOException(ErrorMessages.ERROR_GETTING_PLANTID, e);
		}
	}

	public static ArrayList<String> getPlantImagesById(int id) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// Create update statement using task id

			String query = "SELECT * FROM plantimagesurl WHERE plant_id = ? ";

			ArrayList<String> plantImages = new ArrayList<String>();

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, id);

				ResultSet rs = pst.executeQuery();

				Plant plant = new Plant();

				while (rs.next()) {
					plantImages.add(rs.getString("image_url"));

				}

				return plantImages;
			}
		} catch (SQLException e) {
			throw new DAOException(ErrorMessages.ERROR_GETTING_PLANTID, e);
		}
	}

	/**
	 * Retrieves a list of all plants stored in the database.
	 *
	 * @return A List containing Plant objects representing all plants in the
	 *         database.
	 * @throws DAOException If there is an issue with the DAO operations.
	 * @throws SQLException If a database access error occurs.
	 */
	public static List<Plant> getAllPlant() throws DAOException, SQLException {
		// Create a List to store the retrieved plant objects
		List<Plant> plantProductList = new ArrayList<>();

		try (Connection connection = ConnectionUtil.getConnection()) {
			// SQL query to retrieve all plant records from the 'plant' table
			final String query = "SELECT * FROM plant where is_available=1";

			try (Statement st = connection.createStatement()) {

				try (ResultSet rs = st.executeQuery(query)) {
					// Iterate through the result set and create Plant object s
					while (rs.next()) {
						Plant plant = createPlantFromResultSet(rs);
						plantProductList.add(plant);
					}
				}

			} catch (SQLException e) {
				throw new DAOException("Error for retrieving all plants", e);
			}

			// Return the list of retrieved plant objects
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
		plant.setPlantType(PlantTypeEnum.valueOf(rs.getString("plantType").toUpperCase()));
		plant.setPlantHeight(rs.getFloat("plantHeight"));
		plant.setPlantingSeason(rs.getString("plantingSeason"));
		plant.setHybrid(HybridEnum.valueOf(rs.getString("hybrid").toUpperCase()));

		// Returning the product object.
		return plant;

	}

}

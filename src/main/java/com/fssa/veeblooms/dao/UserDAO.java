package com.fssa.veeblooms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fssa.veeblooms.enumclass.GenderEnum;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.User;
import com.fssa.veeblooms.util.ConnectionUtil;
import com.fssa.veeblooms.util.Logger;

public class UserDAO {

//	public static String hashcodePassword(String password) throws CustomException {
//		try {
//			MessageDigest md = MessageDigest.getInstance("SHA-256");
//			byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
//
//			StringBuilder hashsb = new StringBuilder();
//			for (byte b : hashBytes) {
//				hashsb.append(String.format("%02x", b));
//			}
//
//			return hashsb.toString();
//		} catch (NoSuchAlgorithmException e) {
//
//			throw new CustomException(e.getMessage());
//
//		}
//	}

	// Adds a new user to the database based on the provided User object.
	public static boolean addUser(User user) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			// SQL query to insert a new user into the 'user' table.
			String query = "INSERT INTO users(first_name,last_name,email,password) VALUES(?,?,?,?)";
			// Prepares the SQL query with the provided user details.
			try (PreparedStatement psmt = con.prepareStatement(query)) {
				// Sets the user details in the PreparedStatement.
				psmt.setString(1, user.getFirstName());
				psmt.setString(2, user.getLastName());
				psmt.setString(3, user.getEmail());
				psmt.setString(4, user.getPassword());

				int rowAffected = psmt.executeUpdate();
				// Prints the number of rows affected by the insert query.
				Logger.info(rowAffected + " row/rows affected");
				// Creates a new balance entry for the newly added user.

			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return true;
	}

	
	
	public static User login(String email, String password) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			// SQL query to delete the user from the 'user' table.
			String query = "SELECT * FROM users WHERE email = ? AND password = ?";
			User user;
			System.out.println("email :" + email + " password : " + password);
			// Prepares the SQL query with the user_id.
			try (PreparedStatement psmt = con.prepareStatement(query)) {
				// Sets the user_id in the PreparedStatement.
				psmt.setString(1, email);
				psmt.setString(2, password);
				// Executes the delete query.
				try (ResultSet rs = psmt.executeQuery()) {
					if (rs.next()) {
						user = new User();
						user.setFirstName(rs.getString("first_name"));
						user.setLastName(rs.getString("last_name"));
						user.setEmail(rs.getString("email"));
						user.setPassword(rs.getString("password"));
						user.setMobileNumber(rs.getString("mobile_num"));
						user.setAddress(rs.getString("address"));

						user.setGender(
								rs.getString("gender") != null ? GenderEnum.valueOf(rs.getString("gender")) : null);
						return user;
					}

				}
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return null;
	}

	public static User getUserById(int  id) throws SQLException, DAOException {
		User user = null;
		String query = "SELECT * FROM users WHERE user_id = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setInt(1, id);

				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						user = new User();
						user.setFirstName(rs.getString("first_name"));
						user.setLastName(rs.getString("last_name"));
						user.setEmail(rs.getString("email"));
						user.setPassword(rs.getString("password"));
						user.setMobileNumber(rs.getString("mobile_num"));
						user.setAddress(rs.getString("address"));
						user.setGender(GenderEnum.valueOf(rs.getString("gender").toUpperCase()));

					}
				} 
			}
		} catch (SQLException e) {

			e.printStackTrace();

			throw new DAOException("error getting email");
		}
		return user;
		
	}

	public User getUserByEmail(String email) throws SQLException, DAOException {
		User user = null;
		String query = "SELECT * FROM USERS WHERE email = ?";

		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement pst = con.prepareStatement(query)) {
				pst.setString(1, email);

				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						user = new User();
						user.setFirstName(rs.getString("firstname"));
						user.setLastName(rs.getString("lastname"));
						user.setEmail(rs.getString("email"));
						user.setPassword(rs.getString("password"));
						user.setMobileNumber(rs.getString("mobileNumber"));
						user.setAddress(rs.getString("address"));
						user.setGender(GenderEnum.valueOf(rs.getString("gender").toUpperCase()));

					}
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();

			throw new DAOException("error getting email");
		}

		return user;
	}

	public static boolean updateUser(User user) throws DAOException {
		try (Connection con = ConnectionUtil.getConnection()) {
			// SQL query to update an existing user in the 'users' table.
			String query = "UPDATE users SET first_name=?, last_name=?, email=?,  mobile_num=?, address=?, gender=? WHERE user_id=?";
			// Prepares the SQL query with the provided user details.
			try (PreparedStatement psmt = con.prepareStatement(query)) {
				// Sets the user details in the PreparedStatement.
				psmt.setString(1, user.getFirstName());
				psmt.setString(2, user.getLastName());
				psmt.setString(3, user.getEmail());
				psmt.setString(4, user.getMobileNumber());
				psmt.setString(5, user.getAddress());
				psmt.setString(6, user.getGender().toString());
				psmt.setInt(7, getUserIdByEmail(user.getEmail())); // Assuming you have a 'userId' field in the User
																	// object.

				int rowAffected = psmt.executeUpdate();
				// Prints the number of rows affected by the update query.
				Logger.info(rowAffected + " row/rows affected");
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return true;
	}

	
	
	public static int getUserIdByEmail(String email) throws DAOException {
		int userId = -1; // Default value if the email is not found or an error occurs.

		try (Connection con = ConnectionUtil.getConnection()) {
			// SQL query to retrieve the user ID by email.
			String query = "SELECT user_id FROM users WHERE email=?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {
				
				// Set the email parameter in the PreparedStatement.
				psmt.setString(1, email);

				try (ResultSet rs = psmt.executeQuery()) {
					if (rs.next()) {
						userId = rs.getInt("user_id");
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		return userId;
	}

}

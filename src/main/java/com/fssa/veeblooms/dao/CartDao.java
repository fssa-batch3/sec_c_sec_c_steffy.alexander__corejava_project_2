package com.fssa.veeblooms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fssa.veeblooms.enumclass.OrderStatus;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.Cart;
import com.fssa.veeblooms.model.ErrorMessages;
import com.fssa.veeblooms.util.ConnectionUtil;
import com.fssa.veeblooms.util.Logger;

public class CartDao {
	public static void addToCart(Cart cart) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// SQL query to insert the order information into the 'orders' table
			String insertQuery = "INSERT INTO `cart` (quantity, total_amount, user_id, plant_id) VALUES (?, ?, ?,?)";

			// Execute insert statement
			try (PreparedStatement pst = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

				pst.setString(1, cart.getQuantity() + "");
				pst.setDouble(2, cart.getTotalAmount());
				pst.setInt(3, cart.getUserId());
				pst.setInt(4, cart.getPlantId());

				int affectedRows = pst.executeUpdate();
				int cartId;
				if (affectedRows == 0) {
					throw new SQLException("Adding order failed, no rows affected.");
				}

				try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						cartId = generatedKeys.getInt(1);
						System.out.println("orderId : " + cartId);
					} else {
						throw new SQLException("Creating user failed, no ID obtained.");
					}
				}
//				addCartItems(cart.getCartDetails(), cartId);
				Logger.info("row/rows affected: " + affectedRows);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(ErrorMessages.ORDER_CREATION_FAILED);
		}
	}

	public static void addCartItems(ArrayList<CartDetails> cartDetails, int cartId) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// SQL query to insert the order information into the 'orders' table
			String insertQuery = "INSERT INTO cart_details (cart_id, product_id, product_price, quantity,total_price) VALUES (?, ?, ?, ?,?)";

			// Execute insert statement
			try (PreparedStatement pst = connection.prepareStatement(insertQuery)) {

				for (CartDetails cartDetail : cartDetails) {

					pst.setInt(1, cartId);
					pst.setInt(2, cartDetail.getProductId());
					pst.setDouble(3, cartDetail.getProductPrice());
					pst.setInt(4, cartDetail.getQuantity());
					pst.setDouble(5, cartDetail.getTotalAmount());
					pst.setString(6, OrderStatus.ORDERED + "");

					// Execute the insert statement and get the number of affected rows
					int rowsAffected = pst.executeUpdate();
				}
				Logger.info("Ordered products addded to the ordered_products table successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(ErrorMessages.ORDER_CREATION_FAILED);
		}
	}

	public static ArrayList<Cart> getCartDetailsByUserId(int userid) throws DAOException, SQLException {
		try (Connection con = ConnectionUtil.getConnection()) {
			Cart cart = null;
			// SQL query to delete the user from the 'user' table.
			String query = "SELECT * from cart where user_id=?";

			ArrayList<Cart> cartDetails = new ArrayList<Cart>();
			try (Connection connection = ConnectionUtil.getConnection()) {
				// Prepares the SQL query with the user_id.

				try (PreparedStatement psmt = connection.prepareStatement(query)) {
					psmt.setInt(1, userid);
					try (ResultSet rs = psmt.executeQuery()) {
						while (rs.next()) {
							cart = new Cart();
							cart.setCartId(rs.getInt("cart_id"));
							cart.setQuantity(rs.getInt("quantity"));
							cart.setPlantId(rs.getInt("plant_id"));
							cart.setTotalAmount(rs.getDouble("total_amount"));
							cart.setUserId(rs.getInt("user_id"));
							cartDetails.add(cart);
						}
					}
				}
			}
			return cartDetails;

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}

	public static int getExistingtQuantityByCartId(int cartId) throws DAOException, SQLException {
	
		int quantity = 0;
		String query = "Select quantity from cart where cart_id = ? ";

		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement psmt = connection.prepareStatement(query)) {

				psmt.setInt(1, cartId);

				try (ResultSet rs = psmt.executeQuery()) {
					if (rs.next()) {
						quantity = rs.getInt("quantity");
					}
				}
			}
			return quantity;

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}
	
	public static Cart getCartDetailsByCartId(int cartId) throws DAOException, SQLException {
		Cart cart = null;
		int quantity = 0;
		String query = "Select * from cart where cart_id = ? ";

		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement psmt = connection.prepareStatement(query)) {

				psmt.setInt(1, cartId);

				try (ResultSet rs = psmt.executeQuery()) {
					if (rs.next()) {
						cart= new Cart();
						cart.setCartId(cartId);
						cart.setQuantity(rs.getInt("quantity"));
						cart.setTotalAmount(rs.getDouble("total_amount"));
						cart.setUserId(rs.getInt("user_id"));		
					}
				}
			}
			return cart;

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

	}

	public static int increaseQuantity(int cartId) throws DAOException, SQLException {
		Cart cart = null;
		// SQL query to delete the user from the 'user' table
		String query = "UPDATE cart SET quantity = ?, total_amount=? WHERE cart_id = ?";

		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement psmt = connection.prepareStatement(query)) {
				cart= getCartDetailsByCartId(cartId);
				psmt.setInt(1, cart.getQuantity() + 1);
				double individualPlantPrice = cart.getTotalAmount()/cart.getQuantity();
				psmt.setDouble(2, individualPlantPrice+cart.getTotalAmount());
				psmt.setInt(3, cartId);
				int rowsUpdated = psmt.executeUpdate();

				if (rowsUpdated > 0) {

					System.out.println("Quantity updated successfully");
				} else {

					System.out.println("Cart not found with ID: " + cartId);
				}
			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return cartId;
	}

	public static int decreaseQuantity(int cartId) throws DAOException, SQLException {
		Cart cart = null;
		// SQL query to delete the user from the 'user' table
		String query = "UPDATE cart SET quantity = ?, total_amount=? WHERE cart_id = ?";

		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement psmt = connection.prepareStatement(query)) {
				cart= getCartDetailsByCartId(cartId);
				psmt.setInt(1, cart.getQuantity() - 1);
				double individualPlantPrice = cart.getTotalAmount()/cart.getQuantity();
				psmt.setDouble(2, cart.getTotalAmount()-individualPlantPrice);
				psmt.setInt(3, cartId);
				
				int rowsUpdated = psmt.executeUpdate();

				if (rowsUpdated > 0) {

					System.out.println("Quantity updated successfully");
				} else {

					System.out.println("Cart not found with ID: " + cartId);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return cartId;
	}
	
	public static int getCartIdByPlantId(int plantId, int userId) throws DAOException, SQLException {
		
		String query = "select cart_id from cart where plant_id = ? AND  user_id=?";
		int cartId= -1;
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement psmt = connection.prepareStatement(query)) {

				psmt.setInt(1,plantId);
				psmt.setInt(2, userId);

				ResultSet rs = psmt.executeQuery();

				if (rs.next()) {

					return rs.getInt("cart_id");
				} 
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return cartId;
	}
	
	
	public static void removeCartByCartId(int cartId) throws DAOException, SQLException {
		
		String query = "Delete from cart where cart_id = ?";
	
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement psmt = connection.prepareStatement(query)) {

				psmt.setInt(1,cartId);
				
				int rowsUpdated = psmt.executeUpdate();

				if (rowsUpdated > 0) {

					System.out.println("cart deleted successfully");
				} else {

					System.out.println("Cart not found with ID: " + cartId);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	
	}
	
	public static void removeallCartByUserId(int userId) throws DAOException, SQLException {
		String query = "Delete from cart where user_id = ?";
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			try (PreparedStatement psmt = connection.prepareStatement(query)) {

				psmt.setInt(1,userId);
				
				int rowsUpdated = psmt.executeUpdate();

				if (rowsUpdated > 0) {

					System.out.println("cart deleted successfully");
				} else {

					System.out.println("Cart not found with ID: " + userId);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	}
	
	
	


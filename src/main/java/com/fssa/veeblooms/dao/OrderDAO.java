package com.fssa.veeblooms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.fssa.veeblooms.enumclass.OrderStatus;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.ErrorMessages;
import com.fssa.veeblooms.model.Order;
import com.fssa.veeblooms.model.OrderedProduct;
import com.fssa.veeblooms.model.Plant;
import com.fssa.veeblooms.util.ConnectionUtil;
import com.fssa.veeblooms.util.Logger;

public class OrderDAO {

	public static void addOrder(Order order) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// SQL query to insert the order information into the 'orders' table
			String insertQuery = "INSERT INTO order (total_amount, ordered_date, status, comments,user_id) VALUES (?, ?, ?, ?,?)";

			// Execute insert statement
			try (PreparedStatement pst = connection.prepareStatement(insertQuery)) {

				pst.setDouble(1, order.getTotalAmount());
				pst.setDate(2, java.sql.Date.valueOf(order.getOrderedDate()));
				pst.setString(3, order.getStatus().toString());
				pst.setString(4, order.getComments());
				pst.setInt(5, order.getUserID());

				// Execute the insert statement and get the number of affected rows
				int rowsAffected = pst.executeUpdate();

				Logger.info("row/rows affected: " + rowsAffected);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(ErrorMessages.ORDER_CREATION_FAILED);
		}
	}

	public static void addOrderItems(ArrayList<OrderedProduct> orderedProducts) throws DAOException, SQLException {

		try (Connection connection = ConnectionUtil.getConnection()) {
			// SQL query to insert the order information into the 'orders' table
			String insertQuery = "INSERT INTO order_items (order_id, product_id, price, quantity,total_amount, status) VALUES (?, ?, ?, ?,?,?)";

			// Execute insert statement
			try (PreparedStatement pst = connection.prepareStatement(insertQuery)) {

				for (OrderedProduct orderedProduct : orderedProducts) {
					Plant plant = orderedProduct.getProduct();
//					pst.setInt(1, order.getTotalAmount()); 
					pst.setInt(2, PlantDAO.getPlantIdByName(plant.getPlantName()));
					pst.setDouble(3, plant.getPrice());
					pst.setInt(4, 1);
					pst.setDouble(5, plant.getPrice());
					pst.setString(6, OrderStatus.ORDERED + "");

					// Execute the insert statement and get the number of affected rows
					int rowsAffected = pst.executeUpdate();
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(ErrorMessages.ORDER_CREATION_FAILED);
		}
	}

	private static boolean checkOrderExists(int orderId) throws DAOException, SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT COUNT(*) FROM orders WHERE order_id = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setInt(1, orderId);
				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {
						int count = resultSet.getInt(1);
						return count > 0; // If count > 0, order with the ID exists
					}
				}
			}
		}
		return false;
	}

	public Order getOrderById(int orderId) throws DAOException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM order WHERE order_id = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setInt(1, orderId);

				try (ResultSet resultSet = pst.executeQuery()) {
					if (resultSet.next()) {
						Order order = new Order();
						order.setOrderId(resultSet.getInt("order_id"));
						order.setTotalAmount(resultSet.getDouble("total_amount"));
						order.setOrderedDate(resultSet.getDate("ordered_date").toLocalDate());
						order.setStatus(OrderStatus.valueOf(resultSet.getString("status")));
						order.setComments(resultSet.getString("comments"));

						return order;
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(ErrorMessages.ORDER_RETRIEVAL_FAILED);
		}
		return null;
	}

}

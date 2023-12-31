package com.fssa.veeblooms.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fssa.veeblooms.dao.OrderDAO;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.Order;
import com.fssa.veeblooms.model.OrderedProduct;
import com.fssa.veeblooms.validator.OrderValidator;

public class OrderService {

	public static boolean addOrder(Order order) throws DAOException, CustomException, SQLException {

		if (OrderValidator.validateOrder(order)) {

			OrderDAO.addOrder(order);
			return true;
		}
		return false;

	}

	public static ArrayList<Order> getOrderById(int orderId) throws DAOException, CustomException, SQLException {

		OrderDAO orderDao = new OrderDAO();
		return orderDao.getOrderById(orderId); 

	}

	public static void deleteOrderedProductsByOrderId(int orderId) throws DAOException, CustomException, SQLException {
		OrderDAO.deleteOrderedProductsByOrderId(orderId);
	}

	public static boolean cancelOrder(int orderId) throws DAOException, CustomException, SQLException {
		
	return OrderDAO.cancelOrder(orderId);
	}

	public static boolean itemShipped(int shippedBtn) throws DAOException, CustomException, SQLException {
		
	return OrderDAO.itemShipped(shippedBtn);

	}

	public static boolean DeliveredOrder(int delivered) throws DAOException, CustomException, SQLException {
		
	return OrderDAO.DeliveredOrder(delivered);

	}
	public static ArrayList<Order> getOrder() throws DAOException, CustomException, SQLException {
		
		return OrderDAO.getOrder();

		}
}

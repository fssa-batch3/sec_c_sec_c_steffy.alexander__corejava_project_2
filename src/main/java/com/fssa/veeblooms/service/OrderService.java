package com.fssa.veeblooms.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fssa.veeblooms.dao.OrderDAO;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.Order;
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
}

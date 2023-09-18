package com.fssa.veeblooms.dao;

import java.sql.Connection;

import java.sql.SQLException;

import com.fssa.veeblooms.exception.DAOException;

import com.fssa.veeblooms.model.OrderedProduct;
import com.fssa.veeblooms.util.ConnectionUtil;


public class OrderedProductDAO {

	public static void addOrderedProduct(OrderedProduct orderedproduct) throws DAOException, SQLException {
		  // Check if the plant name already exists in the database
		// Execute insert statement
		try (Connection connection = ConnectionUtil.getConnection()) {

	}
}

	}

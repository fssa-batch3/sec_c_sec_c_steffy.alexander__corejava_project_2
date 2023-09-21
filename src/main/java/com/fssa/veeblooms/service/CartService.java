package com.fssa.veeblooms.service;

import java.sql.SQLException;

import com.fssa.veeblooms.dao.CartDao;
import com.fssa.veeblooms.dao.OrderDAO;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.Cart;
import com.fssa.veeblooms.validator.OrderValidator;

public class CartService {

	public static boolean addToCart(Cart cart) throws DAOException, CustomException, SQLException {

		if (true) {
			// validation needed

			CartDao.addToCart(cart);
			return true; 
		}
		return false;

	}
}

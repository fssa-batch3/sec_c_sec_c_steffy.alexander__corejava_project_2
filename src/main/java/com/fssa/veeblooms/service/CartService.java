package com.fssa.veeblooms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import com.fssa.veeblooms.dao.CartDao;
import com.fssa.veeblooms.dao.CartDetails;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.Cart;

public class CartService {

	public static boolean addToCart(Cart cart) throws DAOException, SQLException {

		if (true) {

			CartDao.addToCart(cart);
			return true;
		}
		return false;

	}

	public static void addCartItems(ArrayList<CartDetails> cartDetails, int cartId)
			throws DAOException, CustomException, SQLException {
		CartDao.addCartItems(cartDetails, cartId);
	}
	

	public static ArrayList<Cart> getCartDetailsByUserId(int userid) throws DAOException, SQLException {
		return CartDao.getCartDetailsByUserId(userid);
	}

	
	public static int getExistingtQuantityByCartId(int cartId) throws DAOException, SQLException {
		return CartDao.getExistingtQuantityByCartId(cartId);
	}
	

	public static void increaseQuantity(int cartId) throws DAOException, SQLException {
		CartDao.increaseQuantity(cartId);
	}
	

	public static void decreaseQuantity(int cartId) throws DAOException, SQLException {

	}
	
}

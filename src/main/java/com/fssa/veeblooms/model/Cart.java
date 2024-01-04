package com.fssa.veeblooms.model;

import java.util.ArrayList;

import com.fssa.veeblooms.dao.CartDetails;

public class Cart {

	private int cartId;
	private int userId;
	private double totalAmount;
	private int quantity;
	private int plantId;
	private ArrayList<CartDetails> cartDetails;
	public int getPlantId() {
		return plantId;
	}

	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}

	

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ArrayList<CartDetails> getCartDetails() {
		return cartDetails;
	}


	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", totalAmount=" + totalAmount + ", quantity="
				+ quantity + ", plantId=" + plantId + ", cartDetails=" + cartDetails + "]";
	}



}

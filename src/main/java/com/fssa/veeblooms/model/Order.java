package com.fssa.veeblooms.model;

import java.time.LocalDate;
import java.util.List;

import com.fssa.veeblooms.enumclass.OrderStatus;

public class Order {

	private int userID;
	private int OrderId;
	private double totalAmount;
	List<OrderedProduct> productsList;
	private LocalDate orderedDate;
	private OrderStatus status;
	private String comments;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double total_amount) {
		this.totalAmount = total_amount;
	}

	public List<OrderedProduct> getProductsList() {
		return productsList;
	}

	public void setProductsList(List<OrderedProduct> productsList) {
		this.productsList = productsList;
	}

	public LocalDate getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(LocalDate ordered_date) {
		this.orderedDate = ordered_date;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Order [user_id=" + userID + ", total_amount=" + totalAmount + ", productsList=" + productsList
				+ ", ordered_date=" + orderedDate + ", status=" + status + ", comments=" + comments + "]";
	}
}

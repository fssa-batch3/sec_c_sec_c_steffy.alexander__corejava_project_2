package com.fssa.veeblooms.model;

import java.time.LocalDate;
import java.util.List;

import com.fssa.veeblooms.enumclass.OrderStatus;

public class Order {

	private int userID;//change to User user
	private int OrderId;
	private double totalAmount;
	
	private LocalDate orderedDate;
	private LocalDate modifiedDate;

	private OrderStatus status;
	private String comments;
	private String address;
	List<OrderedProduct> productsList;

	private String phoneNumber;



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

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
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

	public void setOrderedDate(LocalDate orderedDate) {
		this.orderedDate = orderedDate;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "Order [userID=" + userID + ", OrderId=" + OrderId + ", totalAmount=" + totalAmount + ", productsList="
				+ productsList + ", orderedDate=" + orderedDate + ", modifiedDate=" + modifiedDate + ", status="
				+ status + ", comments=" + comments + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}

	
}

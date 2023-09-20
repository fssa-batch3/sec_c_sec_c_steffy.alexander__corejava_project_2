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
	private String address;


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
	@Override
	public String toString() {
		return "Order [userID=" + userID + ", OrderId=" + OrderId + ", totalAmount=" + totalAmount + ", productsList="
				+ productsList + ", orderedDate=" + orderedDate + ", status=" + status + ", comments=" + comments
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", getUserID()=" + getUserID()
				+ ", getOrderId()=" + getOrderId() + ", getTotalAmount()=" + getTotalAmount() + ", getProductsList()="
				+ getProductsList() + ", getOrderedDate()=" + getOrderedDate() + ", getStatus()=" + getStatus()
				+ ", getComments()=" + getComments() + ", getAddress()=" + getAddress() + ", getPhoneNumber()="
				+ getPhoneNumber() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
}

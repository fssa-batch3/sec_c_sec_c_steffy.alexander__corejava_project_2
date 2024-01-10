package com.fssa.veeblooms.model;

public class OrderedProduct {
	
    private int productId; 
    private double productPrice;
    private int quantity;
    private double totalAmount;


    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "OrderedProduct [productId=" + productId + ", productPrice=" + productPrice + ", quantity=" + quantity
				+ ", totalAmount=" + totalAmount + "]";
	}

  
}

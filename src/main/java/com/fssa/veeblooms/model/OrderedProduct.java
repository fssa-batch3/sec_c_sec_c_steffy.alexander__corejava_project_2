package com.fssa.veeblooms.model;

public class OrderedProduct {
    private Plant product;
    private double productPrice;
    private int quantity;
    private double totalAmount;

    public Plant getProduct() {
        return product;
    }

    public void setProduct(Plant product) {
        this.product = product;
    }

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

    @Override
    public String toString() {
        return "OrderedProduct [product=" + product + ", productPrice=" + productPrice + ", quantity=" + quantity
                + ", totalAmount=" + totalAmount + "]";
    }
}

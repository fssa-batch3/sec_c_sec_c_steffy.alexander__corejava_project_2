package com.fssa.veeblooms.validator;

import java.time.LocalDate;
import java.util.List;

import com.fssa.veeblooms.enumclass.OrderStatus;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.model.ErrorMessages;
import com.fssa.veeblooms.model.Order;
import com.fssa.veeblooms.model.OrderedProduct;

public class OrderValidator {

	Order order = new Order();

	// Set the properties of the order
	public static boolean validateOrder(Order order) throws CustomException {
		if (order == null) {
			throw new CustomException(ErrorMessages.INVALID_ORDER_NULL);
		}

		validateTotalAmount(order.getTotalAmount());
		validateProductsList(order.getProductsList());
		validateOrderedDate(order.getOrderedDate());
		validateStatus(order.getStatus());
		validateComments(order.getComments());
		return true;
	}

	public static boolean validateTotalAmount(double totalAmount) throws CustomException {
		// Check if the price is non-positive
		if (totalAmount <= 0) {
			throw new CustomException(ErrorMessages.INVALID_TOTAL_AMOUNT);
		}
		return true;
	}

	public static boolean validateProductsList(List<OrderedProduct> productsList) throws CustomException {
		if (productsList == null || productsList.isEmpty()) {
			throw new CustomException(ErrorMessages.INVALID_PRODUCT_LIST);
		}
		return true;
	}

	public static boolean validateOrderedDate(LocalDate orderedDate) throws CustomException {
		if (orderedDate == null) {
			throw new CustomException(ErrorMessages.INVALID_ORDERED_DATE);
		}

		LocalDate currentDate = LocalDate.now();

		if (orderedDate.isAfter(currentDate)) {
			throw new CustomException(ErrorMessages.ORDER_DATE_IN_FUTURE);
		}
		return true;
	}

	public static boolean validateStatus(OrderStatus status) throws CustomException {

		if (status == null) {
			throw new CustomException(ErrorMessages.INVALID_STATUS);
		}
		return false;
	}

	public static boolean validateComments(String comments) throws CustomException {

		if (comments != null && !comments.isEmpty() && comments.length() < 15) {
			throw new CustomException(ErrorMessages.INVALID_COMMENTSTATEMENT);
		}

		return true;

	}
}

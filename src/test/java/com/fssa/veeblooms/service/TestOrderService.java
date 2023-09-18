package com.fssa.veeblooms.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fssa.veeblooms.enumclass.OrderStatus;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.Order;
import com.fssa.veeblooms.model.OrderedProduct;

public class TestOrderService {
	@Test
	public void testAddUser() throws CustomException, SQLException, DAOException {

		Order order = new Order();
		List<OrderedProduct> productsList = new ArrayList<>();

		OrderedProduct product1 = new OrderedProduct();
		OrderedProduct product2 = new OrderedProduct();

		productsList.add(product1);
		productsList.add(product2);

		order.setTotalAmount(220.0);
		order.setProductsList(productsList);
		order.setOrderedDate(LocalDate.of(2023, 9, 16));
		order.setStatus(OrderStatus.SHIPPED);
		order.setComments("plant was very fresh and good");
		Assertions.assertTrue(OrderService.addOrder(order));
	}

}

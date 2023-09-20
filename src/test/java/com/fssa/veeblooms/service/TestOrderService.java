package com.fssa.veeblooms.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.fssa.veeblooms.enumclass.HybridEnum;
import com.fssa.veeblooms.enumclass.OrderStatus;
import com.fssa.veeblooms.enumclass.PlantTypeEnum;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.Order;
import com.fssa.veeblooms.model.OrderedProduct;
import com.fssa.veeblooms.model.Plant;

public class TestOrderService {
	@Test
	public void testAddOrder() throws CustomException, SQLException, DAOException {

	
		Order order = new Order();
		List<OrderedProduct> productsList = new ArrayList<>();
		int plantId= 21;
		Plant plant=PlantService.getPlantById(plantId);
		OrderedProduct product1 = new OrderedProduct();
		product1.setProductId(plantId);
		product1.setProductPrice(plant.getPrice());
		product1.setQuantity(1);
		product1.setTotalAmount(plant.getPrice());
		
		productsList.add(product1);

		order.setTotalAmount(186);
		order.setProductsList(productsList);
		order.setOrderedDate(LocalDate.now());
		order.setUserID(2);
		order.setStatus(OrderStatus.ORDERED);
		order.setAddress("sankarnagar street");
		order.setPhoneNumber("98765432210");
		
		System.out.println(order);
		Assertions.assertTrue(OrderService.addOrder(order));
	}

}


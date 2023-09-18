package com.fssa.veeblooms.service;

import java.sql.SQLException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.User;

public class TestUserService {
	UserService userService = new UserService();

	@Test
	public void testAddUser() throws CustomException, SQLException, DAOException {

		User user = new User();
		user.setFirstName("steffy");
		user.setLastName("Alexander");
		user.setEmail("steffy@gmail.com");
		user.setPassword("Steffy@123");

		Assertions.assertTrue(userService.addUser(user));
	}

}

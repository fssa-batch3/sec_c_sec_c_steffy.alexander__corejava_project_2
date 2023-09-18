package com.fssa.veeblooms.service;

import java.sql.SQLException;

import com.fssa.veeblooms.dao.UserDAO;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.exception.DAOException;
import com.fssa.veeblooms.model.User;
import com.fssa.veeblooms.validator.UserValidator;

public class UserService {

	public boolean addUser(User user) throws DAOException, CustomException {

		if (UserValidator.validateUser(user)) {

			UserDAO.addUser(user);
		} 
		return true;
	}

	public User login(String email, String password) throws DAOException, SQLException {

		return UserDAO.login(email, password);

	}
	
	public boolean updateUser(User user) throws DAOException, CustomException {

		if (UserValidator.validateUserDetails(user)) {

			UserDAO.updateUser(user);
		} 
		return true;
	}
	
}

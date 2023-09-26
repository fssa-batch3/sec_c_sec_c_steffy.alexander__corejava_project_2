package com.fssa.veeblooms.validator;

import com.fssa.veeblooms.enumclass.GenderEnum;
import com.fssa.veeblooms.exception.CustomException;
import com.fssa.veeblooms.model.ErrorMessages;
import com.fssa.veeblooms.model.User;

public class UserValidator {

	public static boolean validateUser(User user) throws CustomException {
    	if (user == null) {
            throw new CustomException(ErrorMessages.INVALID_PLANT_NULL);
        }
        validateFirstName(user.getFirstName());
        validateLastName(user.getLastName());
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
         
        return true; 
 	}
	//validateUserDetails
	public static boolean validateUserDetails(User user) throws CustomException {
    	if (user == null) {
            throw new CustomException(ErrorMessages.INVALID_PLANT_NULL);
        }
        validateFirstName(user.getFirstName());
        validateLastName(user.getLastName());
        validateEmail(user.getEmail());
        validateMobileNumber(user.getMobileNumber());
        validateAddress(user.getAddress());
       
        
        return true; 
 	}
	public static boolean validateFirstName(String firstName) throws CustomException {
		if (firstName == null || firstName.isEmpty() || !firstName.matches( "^[a-zA-Z]{3,20}+$")) {
			throw new CustomException(ErrorMessages.INVALID_FIRST_NAME);
		}
		return true;

	}

	private static boolean validateLastName(String lastName) throws CustomException {
		if (lastName == null || lastName.isEmpty() || !lastName.matches( "^[a-zA-Z]{3,20}+$")) {
			throw new CustomException(ErrorMessages.INVALID_LAST_NAME); 
		}
		return true;

	}

	private static boolean validateEmail(String email) throws CustomException {
		if (email == null || email.isEmpty() || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z.-]+\\.[A-Za-z]{2,}$")) {
			throw new CustomException(ErrorMessages.INVALID_EMAIL);
		}
		return true;

	}

	private static boolean validatePassword(String password) throws CustomException {
		if (password == null || password.length() < 8 || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
			throw new CustomException(ErrorMessages.INVALID_PASSWORD);
		}
		return true;

	}
	public static boolean validateMobileNumber(String mobileNumber) throws CustomException {
	    if (mobileNumber == null || mobileNumber.isEmpty() || !mobileNumber.matches("^(\\+91|91)?[6789]\\d{9}$")) {
	        throw new CustomException(ErrorMessages.INVALID_MOBILE_NUMBER);
	    }
	    return true;
	}

	public static boolean validateAddress(String address) throws CustomException {
	    if (address == null || address.isEmpty()) {
	        throw new CustomException(ErrorMessages.INVALID_ADDRESS);
	    }
	    return true;
	}

	public static boolean validateGender(GenderEnum gender) throws CustomException {
	    if (gender == null) {
	        throw new CustomException(ErrorMessages.INVALID_GENDER);
	    }
	    return true;
	}


	

}




























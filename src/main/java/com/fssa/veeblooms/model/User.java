package com.fssa.veeblooms.model;

import com.fssa.veeblooms.enumclass.GenderEnum;

public class User {
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password
				+ ", mobileNumber=" + mobileNumber + ", address=" + address + ", gender=" + gender + "]";
	}

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String mobileNumber;
	private String address;
    private GenderEnum gender;



	public String getFirstName() {
		return firstName; 
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}



	public User(String firstName, String lastName, String email, String password,String mobileNumber,String address,GenderEnum gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	 	this.email = email;
		this.password = password;
	    this.mobileNumber=mobileNumber;
	    this.address=address;
	    this.gender=gender;
	}

	public User() {
		
	}
}

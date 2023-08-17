package com.fssa.veeblooms;

public class CustomException extends Exception {
	private static final long serialVersionUID = 2L;
	public CustomException(String msg) {
		super(msg);
	}

	public CustomException(Throwable te) {
		super(te);
	}

	public CustomException(String msg, Throwable te) {

		super(msg, te);
	}

}

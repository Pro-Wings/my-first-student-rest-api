package com.prowings.model;

public class MyCustomError {

	private String message;
	private String rootCause;
	private int statusCode;

	public MyCustomError() {
	}

	public MyCustomError(String message, String rootCause, int statusCode) {
		super();
		this.message = message;
		this.rootCause = rootCause;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRootCause() {
		return rootCause;
	}

	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}

package com.irctc_booking.response;

public class ErrorResponse {

	private String status;
	private String message;

	public ErrorResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}

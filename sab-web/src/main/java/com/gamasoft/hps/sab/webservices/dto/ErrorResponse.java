package com.gamasoft.hps.sab.webservices.dto;

/**
 * Error representation to be returned by REST services.
 * @author adrian.konkolowicz
 *
 */
public class ErrorResponse {
	
	private Integer responseCode;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
}

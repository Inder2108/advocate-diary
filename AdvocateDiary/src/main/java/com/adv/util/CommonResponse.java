package com.adv.util;

public class CommonResponse {

	private int statusCode = 200;

	public CommonResponse(){}
	
	public CommonResponse(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}

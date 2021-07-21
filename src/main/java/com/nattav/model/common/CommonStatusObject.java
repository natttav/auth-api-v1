package com.nattav.model.common;

public class CommonStatusObject {
	private String code;
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CommonStatusObject(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}

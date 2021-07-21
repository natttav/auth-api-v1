package com.nattav.model;

public class CommonResponse {
	private ResponseStatusObject status;
	private Object data;
	public ResponseStatusObject getStatus() {
		return status;
	}
	public void setStatus(ResponseStatusObject status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public CommonResponse(ResponseStatusObject status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}
}

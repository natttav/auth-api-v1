package com.nattav.model;

public class VerificationResponse {

	private String jwt;
	private String verifyStatus;
	private String username;
	private String refCode;
	
	
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getVerifyStatus() {
		return verifyStatus;
	}
	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}


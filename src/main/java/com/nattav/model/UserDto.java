package com.nattav.model;

public class UserDto {
	private String username;
	private String password;
	private String address;
	private String phoneno;
	private String refcode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getRefcode() {
		return refcode;
	}

	public void setRefcode(String refcode) {
		this.refcode = refcode;
	}
}

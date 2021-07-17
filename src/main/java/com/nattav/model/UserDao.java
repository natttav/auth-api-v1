package com.nattav.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author nattavadhkanthamarn
 *
 */
@Entity
@Table(name = "user")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private String address;
    @Column
    private String phoneno;
    @Column
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


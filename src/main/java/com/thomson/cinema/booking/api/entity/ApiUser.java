package com.thomson.cinema.booking.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ApiUser")
public class ApiUser {

	private int userId;
	private String fullName;
	@Id
	@Column(name = "token", unique = true,
		nullable = false, length = 100)
	private String token;
	private String ipAddress;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "ApiUser [userId=" + userId + ", fullName=" + fullName + ", token=" + token + ", ipAddress=" + ipAddress
				+ "]";
	}

}

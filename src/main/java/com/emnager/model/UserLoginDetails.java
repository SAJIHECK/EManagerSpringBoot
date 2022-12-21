package com.emnager.model;

public class UserLoginDetails {
	
	private int userId;
	private String userName;
	private String userRole;
	private String jwtToken;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public UserLoginDetails(int userId, String userName, String userRole, String jwtToken) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userRole = userRole;
		this.jwtToken = jwtToken;
	}
	
	

}

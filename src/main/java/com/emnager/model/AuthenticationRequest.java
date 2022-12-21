package com.emnager.model;

public class AuthenticationRequest {
	


	private String sUserName;
	private String sPassword;
	
	
	AuthenticationRequest(){
		
	}
	
	public AuthenticationRequest(String sUserName, String sPassword) {
		super();
		this.sUserName = sUserName;
		this.sPassword = sPassword;
	}
	public String getsUserName() {
		return sUserName;
	}
	public void setsUserName(String sUserName) {
		this.sUserName = sUserName;
	}
	public String getsPassword() {
		return sPassword;
	}
	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}
	
	


}

package com.emnager.model;

public class AuthenticationResponse {

	
	private String sJWT;

	public AuthenticationResponse(String sJWT) {
		super();
		this.sJWT = sJWT;
	}

	public String getsJWT() {
		return sJWT;
	}

	public void setsJWT(String sJWT) {
		this.sJWT = sJWT;
	}
	
	



}

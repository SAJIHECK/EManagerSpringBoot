package com.emnager.exception;

public class ErrorResponseOutput {

	public int iError;
	public String sMessage;
	public double dTimeStamp;
	
	public ErrorResponseOutput() {
		
	}
	
	public ErrorResponseOutput(int iError, String sMessage, double dTimeStamp) {
		this.iError = iError;
		this.sMessage = sMessage;
		this.dTimeStamp = dTimeStamp;
	}


	public int getiError() {
		return iError;
	}

	public void setiError(int iError) {
		this.iError = iError;
	}

	public String getsMessage() {
		return sMessage;
	}
	public void setsMessage(String sMessage) {
		this.sMessage = sMessage;
	}
	public double getdTimeStamp() {
		return dTimeStamp;
	}
	public void setdTimeStamp(double dTimeStamp) {
		this.dTimeStamp = dTimeStamp;
	}
	
	


}

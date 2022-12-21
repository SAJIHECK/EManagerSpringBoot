package com.emnager.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ErrorMessage {

	private List<String> errorDescription;
	
	public ErrorMessage() {
		errorDescription=new ArrayList<String>();
		errorDescription.add("No Error");
		errorDescription.add("InavlidUserName");	
		errorDescription.add("session Expired Please login");	
		errorDescription.add("File Size Limit Exceeded");
	}
	
	public String getErrorMessage(int errorCode){
    	return errorDescription.get(errorCode);
	}
	
}

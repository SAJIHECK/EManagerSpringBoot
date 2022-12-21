package com.emnager.exception;

import org.springframework.stereotype.Component;

@Component
public class ErrorCode {
	
	
	public static final int NO_Error=0;
	public static final int InavlidUsername=1;
	public static final int JWTTokenExpired=2;
	public static final int FileSizeExceeded=3;
}

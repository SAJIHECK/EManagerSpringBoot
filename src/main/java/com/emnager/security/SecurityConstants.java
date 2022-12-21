package com.emnager.security;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {
	
	@Value("${session.timeout}")
    public static final long JWT_EXPIRATION = 70000;
    public static final String JWT_SECRET = "secret";
}

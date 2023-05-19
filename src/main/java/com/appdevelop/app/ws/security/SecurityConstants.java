package com.appdevelop.app.ws.security;

import org.springframework.core.env.Environment;

import com.appdevelop.app.ws.SpringApplicationContext;

public class SecurityConstants {
	
	public static final long EXPIRATION_TIME=864000000; // 10 days
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String HEADER_STRING="Authorization";
	public static final String SIGN_UP_URL="/users";
//	public static final String TOKEN_SECRET="fgwcqmnz4x3xvpuwu01cqwp6guzha643jmw4zd7ltovlz9j86it6t3fro6q86mgp"; //random 64 bit alphabet string
	
	public static String getTokenSecret() {
		Environment environment = (Environment) SpringApplicationContext.getBean("environment");
		return environment.getProperty("tokenSecret");
	}
}

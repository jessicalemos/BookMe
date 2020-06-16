package com.app.BookMe.security;

public class SecurityConstants {
	public static final String SECRET = "a5ZdWYJPZWMyHXRSZXk=";
	public static final long EXPIRATION_TIME =  25920000000L;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/register";
	public static final String SIGN_IN_URL= "/login";
}

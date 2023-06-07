package com.appdevelop.app.ws.shared;

import org.springframework.stereotype.Component;

import com.appdevelop.app.ws.security.SecurityConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Random;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

@Component
public class Utils {
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefjhigklmnopqrstuvwxyz";

	
	public String generateUserId(int length) {
		return generateRandomString(length);
	}
	
	public String generateAddressId(int length) {
		return generateRandomString(length);
	}
	
	private String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		
		for (int i=0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		
		return new String(returnValue);
	}
	
	public static boolean hasTokenExpired(String token) {
	    try {
	        String secretKey = SecurityConstants.getTokenSecret();

	        Claims claims = Jwts.parserBuilder()
	                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
	                .build()
	                .parseClaimsJws(token)
	                .getBody();

	        Date tokenExpirationDate = claims.getExpiration();
	        Date todayDate = new Date();

	        return tokenExpirationDate.before(todayDate);
	    } catch (ExpiredJwtException e) {
	        // JWT was already expired
	        return true;
	    } catch (JwtException e) {
	        // An error occurred while parsing the JWT
	        throw new RuntimeException("Failed to parse JWT", e);
	    }
	}
	
	public String generateEmailVerificationToken(String userId) {
		byte[] keyBytes = SecurityConstants.getTokenSecret().getBytes(StandardCharsets.UTF_8);
	    Key key = Keys.hmacShaKeyFor(keyBytes);
	    
		String token = Jwts.builder()
				.setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
		return token;
	}


}

package com.appdevelop.app.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.appdevelop.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(String iserId);
	UserDto updateUser(String userId, UserDto user);
	void deleteUser(String userId);
	List<UserDto> getUser(int page, int limit);
	boolean verifyEmailToken(String token);
	boolean requestPasswordReset(String email);
	boolean resetPassword(String token, String password);

}

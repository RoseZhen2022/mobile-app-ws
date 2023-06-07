package com.appdevelop.app.ws.io.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.appdevelop.app.ws.io.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	void delete(UserEntity userEntity);
	UserEntity save(UserEntity userEntity);
	UserEntity findUserByEmailVerificationToken(String token);

}

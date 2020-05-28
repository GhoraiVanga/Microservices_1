package com.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;



@Service
public interface UserService extends UserDetailsService
{
	Userdto createUser(Userdto user);
	Userdto getUser(String email);
	Userdto getUserByUserId(String userId);
	Userdto updateUser(String userId, Userdto user);
	void deleteUser(String userId);
	List<Userdto> getUsers(int page, int limit);
	boolean verifyEmailToken(String token);
	boolean requestPasswordReset(String email);
	boolean resetPassword(String token, String password);
	

}

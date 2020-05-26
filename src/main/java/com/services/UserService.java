package com.services;

import org.springframework.stereotype.Service;

@Service
public interface UserService
{
	Userdto createUser(Userdto user);

}

package com.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService 
{
	
	@Autowired
	UserRepositry userRepositry;
	
	@Override
	public Userdto createUser(Userdto user)
	{
		UserEntity userEntity=new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setEncryptedPassword("asjkbkj");
		userEntity.setUserId("testUserId");
		UserEntity storedUserDetails =userRepositry.save(userEntity);
		Userdto returnUserdto=new Userdto();
		BeanUtils.copyProperties(storedUserDetails, returnUserdto);
		
		return returnUserdto;
	}

}

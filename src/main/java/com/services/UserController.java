package com.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController 
{
	@Autowired
	UserService userService;
	@GetMapping
	public String getUser()
	{
		return "get user called";
	}

	@PostMapping
	
	public UserRest createUser(@RequestBody UserDetailsRequstModel userDetail)
	{
		
		
		UserRest returnValue=new UserRest();
		Userdto userdto=new Userdto();
		BeanUtils.copyProperties(userDetail,userdto );
		Userdto createdUser=userService.createUser(userdto);
		BeanUtils.copyProperties(createdUser, returnValue);
		return returnValue;
		
		
	}
	@PutMapping
	public String updateUser()
	{
		return "get updateuser called";
	}
	@DeleteMapping
	public String deleteUser()
	{
		return "get deleteuser called";
	}
	
}

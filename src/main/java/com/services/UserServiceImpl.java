package com.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;





@Service
public class UserServiceImpl implements UserService 
{
	
	@Autowired
	UserRepositry userRepositry;
	@Autowired
	Utils utils;
	@Autowired
	 BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Userdto createUser(Userdto user)
	{
		if(userRepositry.findByEmail(user.getEmail())!=null) throw new RuntimeException("Recod already exist");
		UserEntity userEntity=new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		String publicUserId=utils.generateUserId(30);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(publicUserId);
		UserEntity storedUserDetails =userRepositry.save(userEntity);
		Userdto returnUserdto=new Userdto();
		BeanUtils.copyProperties(storedUserDetails, returnUserdto);
		
		return returnUserdto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	UserEntity userEntity=	userRepositry.findByEmail(email );
	if(userEntity==null) throw new UsernameNotFoundException(email);
	
	return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),new ArrayList<>());
	}

	@Override
	public Userdto getUser(String email) {
		UserEntity userEntity = userRepositry.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		Userdto returnValue = new Userdto();
		BeanUtils.copyProperties(userEntity, returnValue);
 
		return returnValue;
	}

	@Override
	public Userdto getUserByUserId(String userId) {
		Userdto returnValue = new Userdto();
		UserEntity userEntity = userRepositry.findByUserId(userId);

		if (userEntity == null)
			throw new UsernameNotFoundException("User with ID: " + userId + " not found");

		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;
	}

	@Override
	public Userdto updateUser(String userId, Userdto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Userdto> getUsers(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verifyEmailToken(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requestPasswordReset(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resetPassword(String token, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}

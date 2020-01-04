package com.yourules.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yourules.bean.User;
import com.yourules.dao.UserRepository;

@Transactional
public class UserService 
{
	@Autowired
	UserRepository uRep;
	
	public List<User> getUserAll()
	{
		return uRep.findAll();
	}
	
	public User getByEmail(String email, String password)
	{
		User tempUser =  uRep.findByEmail(email);
		
		if(tempUser != null)
		{	
			if(tempUser.getPassword().equals(password))
			{
				return tempUser;
			}
		}
		
		return null;
	}
	
	public void registerUser(String email, String password)
	{
		String[] trim = email.split("@");
		String username = trim[0];
		
		User tempUser = new User(username, password, email);
		
		uRep.save(tempUser);
	}
	
	public void updateUser(User user)
	{
		uRep.save(user);
	}
	
	public boolean checkUserEmail(String email)
	{
		User tempUser = uRep.findByEmail(email);
		
		if(tempUser == null)
		{
			return true;
		}
		
		return false;
	}	
}

package com.yourules.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yourules.bean.User;
import com.yourules.service.UserService;

@Controller
@RequestMapping(value="/User/")
public class UserController 
{
	@Autowired
	UserService userService;
	
	@RequestMapping(value="All.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<User> getAllUsers()
	{
		return userService.getUserAll();
	}
	
	@RequestMapping(value="Authenticate.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public User authenticate(HttpServletRequest request, HttpServletResponse response)
	{
		String tempEmail = request.getParameter("email");
		String tempPass = request.getParameter("password");
		
		System.out.println("tempEmail: " + tempEmail);
		System.out.println("tempPass: " + tempPass);
		
		//User tempUser = userService.getByEmail(tempEmail, tempPass);
		User tempUser = null;
		if(tempEmail.equals("temp@yr.com")){
			tempUser= new User("temp", "tempy", "temp@yr.com");
		} else if(tempEmail.equals("pemp@yr.com")){
			tempUser = new User("pemp", "tempy", "pemp@yr.com");
		} else if(tempEmail.equals("yemp@yr.com")){
			tempUser = new User("yemp", "tempy", "pemp@yr.com");
		}
		
		if(tempUser != null)
		{
			System.out.println(tempUser.toString());
			return tempUser;
		}
		else
		{
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
	}
	
	@RequestMapping(value="Register.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public void registerUser(HttpServletRequest request, HttpServletResponse response)
	{
		String tempEmail = request.getParameter("email");
		String tempPassword = request.getParameter("password");

		boolean registered = userService.checkUserEmail(tempEmail);
	
		if(registered)
		{
			userService.registerUser(tempEmail, tempPassword);
			System.out.println("regiter succ");
		}
		else
		{
			System.out.println("regiter fail");
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="Update.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public User updateUser(HttpServletRequest request, HttpServletResponse response)
	{
		String tempUserId = request.getParameter("userId");
		String tempUsername = request.getParameter("username");
		String tempEmail = request.getParameter("email");
		String tempPassword = request.getParameter("pass");
		
		User tempUser = new User(Integer.parseInt(tempUserId), tempUsername, tempPassword, tempEmail);
	
		userService.updateUser(tempUser);
		
		return tempUser;
	}
}

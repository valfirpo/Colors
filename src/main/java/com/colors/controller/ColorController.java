package com.colors.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yourules.bean.Game;


@Controller
@RequestMapping(value = "/Color/")
public class ColorController {

	
	@RequestMapping(value = "Blue.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<String> blue(HttpServletRequest request)
	{
		String color = "blue";
		System.out.println(color);
		ArrayList<String> list = new ArrayList<>();
		list.add(color);
		
		return list;
	}
	
	@RequestMapping(value = "Red.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<String> red(HttpServletRequest request)
	{
		String color = "red";
		System.out.println(color);
		ArrayList<String> list = new ArrayList<>();
		list.add(color);
		
		return list;
	}
	
	@RequestMapping(value = "Yellow.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<String> yellow(HttpServletRequest request)
	{
		String color = "yellow";
		System.out.println(color);
		ArrayList<String> list = new ArrayList<>();
		list.add(color);
		
		return list;
	}
	
}

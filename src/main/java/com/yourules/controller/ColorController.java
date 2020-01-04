package com.yourules.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/Color/")
public class ColorController {

	
	@RequestMapping(value = "Blue.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String blue(HttpServletRequest request)
	{
		String color = "blue";
		
		return color;
	}
	
	@RequestMapping(value = "Red.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String red(HttpServletRequest request)
	{
		String color = "red";
		
		return color;
	}
	
	@RequestMapping(value = "Yellow.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public String yellow(HttpServletRequest request)
	{
		String color = "yellow";
		
		return color;
	}
	
}

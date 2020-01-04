package com.yourules.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yourules.bean.Rule;
import com.yourules.service.RuleService;

@Controller
@RequestMapping(value = "/Rule/")
public class RuleController {
	
	@Autowired
	RuleService ruleService;
	
	@RequestMapping(value = "GetRulesByGame.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<Rule> getRulesByGame(HttpServletRequest request)
	{
		String gameId = request.getParameter("id");
		return ruleService.getRulesByGame(Integer.parseInt(gameId));
		//return ruleService.getRulesByGame(Integer.parseInt(request.getParameter("id")));
	}
	
	@RequestMapping(value = "Create.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public void createRule(HttpServletRequest request)
	{
		String tempRule = request.getParameter("rule");
		String tempDrinks = request.getParameter("drinks");
		String tempGameId = request.getParameter("gameId");
		
		ruleService.addRule(tempRule, Integer.parseInt(tempDrinks), Integer.parseInt(tempGameId));
	}
	
	@RequestMapping(value = "Update.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public void updateRule(HttpServletRequest request)
	{
		String tempRuleId = request.getParameter("ruleId");
		String tempRuleRule = request.getParameter("rule");
		String tempUsed = request.getParameter("used");
		String tempDrinks = request.getParameter("drinks");
		String tempGameId = request.getParameter("gameId");
		
		Rule tempRule = new Rule(Integer.parseInt(tempRuleId), tempRuleRule, Integer.parseInt(tempUsed), Integer.parseInt(tempDrinks), Integer.parseInt(tempGameId));
		
		ruleService.updateRule(tempRule);
	}
	
	@RequestMapping(value = "SaveRules.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public void saveRule(HttpServletRequest request)
	{
		String ruleStr = request.getParameter("rules");
		
		ruleService.updateRules(ruleStr);
	}
}

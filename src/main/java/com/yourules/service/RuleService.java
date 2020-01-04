package com.yourules.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yourules.bean.Rule;
import com.yourules.dao.RuleRepository;

@Transactional
public class RuleService 
{
	@Autowired
	RuleRepository rRep;
	
	public List<Rule> getRulesByGame(int gameId)
	{
		List<Rule> rules = rRep.findByGameId(gameId);
		return rules;
	}
	
	public void addRule(String rule, int drinks, int gameId)
	{
		Rule newRule = new Rule(rule, 0, drinks, gameId);
		
		rRep.save(newRule);
	}
	
	public void updateRule(Rule rule)
	{
		rRep.save(rule);
	}
	
	public void updateRules(String allRuleStr)
	{
		String[]  rules = allRuleStr.split(":");
		for(String r : rules)
		{
			Rule rule = createRule(r);
			rRep.save(rule);
		}
	}
	
	private static Rule createRule(String singleRuleStr)
	{
		String[] ruleAtt  = singleRuleStr.split(",");
		Rule rule = new Rule();
		rule.setId(Integer.parseInt(ruleAtt[0]));
		rule.setRule(ruleAtt[1]);
		rule.setUsed(Integer.parseInt(ruleAtt[2]));
		rule.setDrink(Integer.parseInt(ruleAtt[3]));
		rule.setGameId(Integer.parseInt(ruleAtt[4]));
		
		return rule;
	}
}

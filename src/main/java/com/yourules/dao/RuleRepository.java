package com.yourules.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourules.bean.Rule;

public interface RuleRepository extends JpaRepository<Rule, Integer>
{
	public List<Rule> findByGameId(int gameId);
}

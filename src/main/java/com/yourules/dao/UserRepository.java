package com.yourules.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourules.bean.User;

import java.lang.String;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	public List<User> findAll();
	
	public User findByEmail(String email);
}

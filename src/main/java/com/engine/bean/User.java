package com.engine.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name  = "yr_user")
@Component
public class User 
{
	@Id
	@Column(name = "user_id")
	@SequenceGenerator(name = "YR_USERID_SEQ", sequenceName = "YR_USERID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "YR_USERID_SEQ")
	private int id;
	
	@Column(name = "user_name")
	@NotEmpty(message="Username cannot be empty")
	private String username;
	
	@Column(name = "user_password")
	@NotEmpty(message="Password cannot be empty")
	private String password;
	
	@Column(name = "user_email")
	@NotEmpty(message="e-mail address cannot be empty")
	private String email;
	
	public User() {}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
}

package com.example.project6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project6.model.User;

@Repository
public interface IUserDAO extends JpaRepository<User, Long>{
	public User findByEmail(String email);
}

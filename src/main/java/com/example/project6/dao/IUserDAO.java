package com.example.project6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.User;

@Repository
@Transactional
public interface IUserDAO extends JpaRepository<User, Long>{
	public User findByEmail(String email);
	public User findOneById(Long id);
}

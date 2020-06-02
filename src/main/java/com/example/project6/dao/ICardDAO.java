package com.example.project6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project6.model.CreditCard;

@Repository
public interface ICardDAO extends JpaRepository<CreditCard, Long>{
	public CreditCard findOneById(Long id);
}

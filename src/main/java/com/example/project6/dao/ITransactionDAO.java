package com.example.project6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project6.model.Transaction;

@Repository
public interface ITransactionDAO extends JpaRepository<Transaction, Long>{

}

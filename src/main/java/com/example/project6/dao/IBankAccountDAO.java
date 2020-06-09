package com.example.project6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.BankAccount;

@Repository
@Transactional
public interface IBankAccountDAO extends JpaRepository<BankAccount, Long>{
}

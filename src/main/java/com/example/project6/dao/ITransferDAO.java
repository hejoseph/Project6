package com.example.project6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project6.model.Transfer;

@Repository
public interface ITransferDAO extends JpaRepository<Transfer, Long>{
}

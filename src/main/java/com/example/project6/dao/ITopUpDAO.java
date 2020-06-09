package com.example.project6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project6.model.TopUp;

@Repository
public interface ITopUpDAO extends JpaRepository<TopUp, Long>{
}

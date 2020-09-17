package com.example.project6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.TopUp;

@Repository
@Transactional
public interface ITopUpDAO extends JpaRepository<TopUp, Long>{
}

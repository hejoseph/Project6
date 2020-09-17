package com.example.project6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Transactions")
public class Transaction implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;
	@Column(name = "connection")
	private String connection;
	@Column(name = "description")
	private String description;
	@Column(name = "amount")
	private String amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public Transaction() {
	}
	
	public Transaction(String connection, String description, String amount, User user) {
		this.connection = connection;
		this.description = description;
		this.amount = amount;
		this.user = user;
	}
	public String getConnection() {
		return connection;
	}
	public String getDescription() {
		return description;
	}
	public String getAmount() {
		return amount;
	}
	public User getUser() {
		return user;
	}
	public void setConnection(String connection) {
		this.connection = connection;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

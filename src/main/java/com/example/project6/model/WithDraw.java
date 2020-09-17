package com.example.project6.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="WithDraws")
public class WithDraw implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;
	
	private Date date;
	private double sum;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bank_id")
	private BankAccount bankAccount;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	public WithDraw() {
	}
	
	public WithDraw(Date date, double sum, BankAccount bankAccount, User user) {
		this.date = date;
		this.sum = sum;
		this.bankAccount = bankAccount;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public double getSum() {
		return sum;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public User getUser() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

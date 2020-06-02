package com.example.project6.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class TopUp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;
	
	private Date date;
	private double sum;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="card_id")
	private CreditCard card;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public TopUp() {
	}

	public TopUp(Date date, double sum, CreditCard card, User user) {
		this.date = date;
		this.sum = sum;
		this.card = card;
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

	public CreditCard getCard() {
		return card;
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

	public void setCard(CreditCard card) {
		this.card = card;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

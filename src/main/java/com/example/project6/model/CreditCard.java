package com.example.project6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Cards")
public class CreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;
	@Column(name = "number")
	private String number;
	@Column(name = "expirationDate")
	private String expirationDate;
	@Column(name = "secretCode")
	private String secretCode;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public CreditCard() {
	}
	
	public CreditCard(String cardInfo) {
		this.number = cardInfo;
	}

	public CreditCard(String cardInfo, User user) {
		this.number = cardInfo;
		this.user = user;
	}

	public Long getId() {
		return id;
	}


	public String getCardInfo() {
		return number;
	}


	public User getUser() {
		return user;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setCardInfo(String cardInfo) {
		this.number = cardInfo;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}

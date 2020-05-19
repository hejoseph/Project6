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

@Entity
@Table(name="Cards")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;
	@Column(name = "cardInfo")
	private String cardInfo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public Card(String cardInfo) {
		super();
		this.cardInfo = cardInfo;
	}

	public Long getId() {
		return id;
	}


	public String getCardInfo() {
		return cardInfo;
	}


	public User getUser() {
		return user;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}

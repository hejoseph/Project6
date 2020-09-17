package com.example.project6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="BankAccounts")
public class BankAccount implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;
	@Column(name = "iban")
	private String iban;
	@Column(name = "bic")
	private String bic;
	@Column(name = "swift")
	private String swift;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public BankAccount() {
	}
	
	public BankAccount(String iban, String bic, String swift, User user) {
		this.iban = iban;
		this.bic = bic;
		this.swift = swift;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public String getIban() {
		return iban;
	}

	public String getBic() {
		return bic;
	}

	public String getSwift() {
		return swift;
	}

	public User getUser() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}

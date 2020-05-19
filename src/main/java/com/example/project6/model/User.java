package com.example.project6.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;
	@Column(name="first_name")
	protected String firstName;
	@Column(name="last_name")
	protected String lastName;
	@Column(name="email")
	protected String email;
	@Column(name="password")
	protected String password;
	@Column(name="balance")
	protected double balance;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "from")
	private List<Connection> contacts;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Card> cards;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Transaction> transactions;
	
	public User(String lastName, String firstName, String email, String password) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
		this.balance = 0.0;
	}
	
	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Connection> getContacts() {
		return contacts;
	}

	public void setContacts(List<Connection> contacts) {
		this.contacts = contacts;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}

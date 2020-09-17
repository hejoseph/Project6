package com.example.project6.model;

//import com.googlecode.jmapper.annotations.JGlobalMap;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@JGlobalMap
public class UserDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private double balance;
	private CreditCard card;
	private BankAccount bankAccount;
	private List<UserDto> contacts = new ArrayList<>();
	private List<TopUp> topUps = new ArrayList<>();
	private List<WithDraw> withDraws = new ArrayList<>();
	private List<Transfer> transfers = new ArrayList<>();

	public UserDto() {
	}
	
	public UserDto(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.balance = 0.0;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public double getBalance() {
		return balance;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

//	public List<User> getContacts() {
//		return contacts;
//	}
//
//	public void setContacts(List<User> contacts) {
//		this.contacts = contacts;
//	}

	public List<UserDto> getContacts() {
		return contacts;
	}

	public void setContacts(List<UserDto> contacts) {
		this.contacts = contacts;
	}

	public List<TopUp> getTopUps() {
		return topUps;
	}

	public void setTopUps(List<TopUp> topUps) {
		this.topUps = topUps;
	}

	public List<WithDraw> getWithDraws() {
		return withDraws;
	}

	public void setWithDraws(List<WithDraw> withDraws) {
		this.withDraws = withDraws;
	}

	public List<Transfer> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<Transfer> transfers) {
		this.transfers = transfers;
	}
}

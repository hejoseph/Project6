package com.example.project6.model;

//import com.googlecode.jmapper.annotations.JGlobalMap;

import java.util.ArrayList;
import java.util.List;

//@JGlobalMap
public class UserDto2 {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private double balance;
	private List<UserDto2> contacts = new ArrayList<>();

	public UserDto2() {
	}

	public UserDto2(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.balance = 0.0;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<UserDto2> getContacts() {
		return contacts;
	}

	public void setContacts(List<UserDto2> contacts) {
		this.contacts = contacts;
	}
}

package com.example.project6.service;

import com.example.project6.model.User;
import com.example.project6.model.UserDto;

public interface IUserService {
	public boolean createUser(UserDto user);
	public User connect(String email, String password);
	public boolean addContact(String connectedEmail, String email);
	public boolean topUpMoneyToAccount(String email, double amount);
	public boolean withDrawMoneyFromAccount(String email, double amount);
	public boolean sendMoney(String senderEmail, String receiverEmail, double amount);
	public boolean sendMoney(String senderEmail, String receiverEmail, double amount, String description);
	public User findUserById(Long userId);
	public User findUserByEmail(String email);

	public UserDto findUserByEmailDto(String email);
}

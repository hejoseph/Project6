package service;

import model.User;
import model.UserDto;

public interface IUserService {
	public boolean createUser(UserDto user);
	public User connect(UserDto user);
//	public boolean addContact(User userConnected, String email);
	public boolean topUpMoneyToAccount(User userConnected, double amount);
	public boolean withDrawMoneyFromAccount(User userConnected, double amount);
	public boolean sendMoney(User fromUser, User toUser, double amount);
	public boolean sendMoney(User fromUser, User toUser, double amount, String description);
	public User findUserById(User userConnected);
	public boolean generateTransaction(User userConnected, String connection, String description, double amount);
	
}

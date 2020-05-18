package service;

import model.User;
import model.UserDto;

public interface IUserService {
	public boolean createUser(UserDto user);
	public User connect(UserDto user);
//	public boolean addContact(User userConnected, String email);
	public boolean topUpAccount(User userConnected, double amount);
	public boolean sendMoney(User fromUser, User toUser, double amount);
	public boolean sendMoney(User fromUser, User toUser, double amount, String description);
}

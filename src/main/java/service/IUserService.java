package service;

import model.User;
import model.UserDto;

public interface IUserService {
	public boolean createUser(UserDto user);
	public User connect(UserDto user);
}

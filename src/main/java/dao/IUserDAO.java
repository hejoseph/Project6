package dao;

import model.User;

public interface IUserDAO {
	public User findByEmail(String email);
}

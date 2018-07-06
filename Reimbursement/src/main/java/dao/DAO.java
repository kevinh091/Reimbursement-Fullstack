package dao;

import user.User;

public interface DAO {
	User selectUser(String username);
	User selectUser(int id);
}
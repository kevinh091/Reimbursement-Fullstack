package services;

import user.User;

public interface Services {
	User getUser(String username);
	User getUser(int id);
}

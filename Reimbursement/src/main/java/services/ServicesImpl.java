package services;

import dao.DAOimpl;
import user.User;

public class ServicesImpl implements Services{
	private DAOimpl dao;
	
	public ServicesImpl() {
		dao= new DAOimpl();
	}
	
	@Override
	public User getUser(String username) {
		return dao.selectUser(username);
	}

	@Override
	public User getUser(int id) {
		return dao.selectUser(id);
	}
	
}

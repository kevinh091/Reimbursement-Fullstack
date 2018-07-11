package services;

import java.sql.Blob;
import java.util.List;

import dao.DAOimpl;
import reimbursement.Reimbursement;
import startup.Config;
import user.User;

public class AuthenticationServicesImpl implements AuthenticationServices{
	private DAOimpl dao;
	
	public AuthenticationServicesImpl() {
		dao = Config.dao;
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

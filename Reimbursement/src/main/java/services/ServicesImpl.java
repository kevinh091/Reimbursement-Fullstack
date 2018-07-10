package services;

import java.sql.Blob;
import java.util.List;

import dao.DAOimpl;
import reimbursement.Reimbursement;
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

	@Override
	public Reimbursement selectReimbursement(int id) {
		return dao.selectReimbursement(id);
	}

	@Override
	public List<Reimbursement> selectReimbursement(User user) {
		return dao.selectReimbursement(user);
	}

	@Override
	public int insertReimbursement(int amount, String description, Blob receipt, User author, int type) {
		return dao.insertReimbursement(amount, description, receipt, author, type);
	}

	@Override
	public int updateReimbursement(int id, User resovler, Integer status) {
		return dao.updateReimbursement(id, resovler, status);
	}
	
}

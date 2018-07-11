package services;

import java.sql.Blob;
import java.util.List;

import dao.DAOimpl;
import reimbursement.Reimbursement;
import startup.Config;
import user.User;

public class RequestServiceImpl implements RequestService{
	private DAOimpl dao;
	
	public RequestServiceImpl() {
		dao = Config.dao;
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
	public int insertReimbursement(int amount, String description, Blob receipt, int author, int type) {
		return dao.insertReimbursement(amount, description, receipt, author, type);
	}

	@Override
	public int updateReimbursement(int id, User resovler, Integer status) {
		return dao.updateReimbursement(id, resovler, status);
	}
	
}

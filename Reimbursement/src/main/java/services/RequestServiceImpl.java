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
	public List<Reimbursement> listReimbursements(int id) {
		return dao.listReimbursements(id);
	}
	
	@Override
	public List<Reimbursement> managerReimbursements(String input){
		return dao.managerReimbursements(input);
	}

	@Override
	public int insertReimbursement(int amount, String description, Blob receipt, int author, int type) {
		return dao.insertReimbursement(amount, description, receipt, author, type);
	}

	@Override
	public int updateReimbursement(int id, int resovler, int status) {
		return dao.updateReimbursement(id, resovler, status);
	}
	
}

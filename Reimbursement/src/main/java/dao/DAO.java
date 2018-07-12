package dao;

import java.sql.Blob;
import java.util.List;

import reimbursement.Reimbursement;
import user.User;

public interface DAO {
	User selectUser(String username);
	User selectUser(int id);
	Reimbursement selectReimbursement(int id);
	//if the user is an employee, return all the reims he requested
	//if the user is a manager, return all reims he solved
	List<Reimbursement> listReimbursements(int id);
	
	
	int insertReimbursement(int amount, String description, Blob receipt, int author, int type);
	int updateReimbursement(int id, int resovler, int status); // status: 1 pending; 2 approved; 3 denied
	List<Reimbursement> managerReimbursements(String input);
}

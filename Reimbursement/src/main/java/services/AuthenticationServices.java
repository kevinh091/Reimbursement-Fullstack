package services;

import java.sql.Blob;
import java.util.List;

import reimbursement.Reimbursement;
import user.User;

public interface AuthenticationServices {
	User getUser(String username);
	User getUser(int id);
	
}

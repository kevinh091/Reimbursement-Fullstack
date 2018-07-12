package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import reimbursement.Reimbursement;
import user.User;


public class DAOimpl implements DAO{
	static{
	       try {
	           Class.forName("oracle.jdbc.driver.OracleDriver");
	       } catch (ClassNotFoundException e) {
	           e.printStackTrace();
	       }
	   }
    private static String db_url = "jdbc:oracle:thin:@revaturedatabase.cgfxycwbhqwa.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static String db_username = "reimburst_database";
    private static String db_password = "q561312665";
    
    public DAOimpl() {
    	openConnection();
    }
    
    private Connection openConnection() {
    	Connection conn=null;
    	try {
    		conn=DriverManager.getConnection(db_url, db_username, db_password);
    	}catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return conn;
    }
    
    private void closeConnection(Connection conn) {
    	try {
    		if (conn != null || !conn.isClosed() ) {
    			conn.close();
    		}
    	} catch (SQLException e) {
    	}
    }
    
	@Override
	public User selectUser(String username) {
		Connection conn =openConnection();
        String sql = "select * from ERS_USERS where ERS_USERNAME = ?";
        User user = null;
        try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	user = new User(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5),
            			rs.getString(6), rs.getInt(7));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        closeConnection(conn);
		return user;
	}
    
	@Override
	public User selectUser(int id) {
		Connection conn =openConnection();
        String sql = "select * from ERS_USERS where ERS_USERS_ID = ?";
        User user = null;
        try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	user = new User(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5),
            			rs.getString(6), rs.getInt(7));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        closeConnection(conn);
		return user;
	}
	
	@Override
	public Reimbursement selectReimbursement(int id) {
		Connection conn =openConnection();
        String sql = "select * from ERS_REIMBURSEMENT where REIMB_ID = ?";
        Reimbursement reimbursement = null;
        try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            
            String authorUsername = selectUser(rs.getInt(7)).getUsername();
            String resolverUsername = selectUser(rs.getInt(8)).getUsername();
            
            reimbursement = new Reimbursement(rs.getInt(1), rs.getFloat(2),rs.getDate(3),rs.getDate(4), rs.getString(5),
            			rs.getBlob(6), authorUsername,resolverUsername,rs.getInt(9),rs.getInt(10));
		} catch (SQLException e) {
			e.printStackTrace();
		}
        closeConnection(conn);
		return reimbursement;
	}
	
	@Override
	public List<Reimbursement> listReimbursements(int id) {
		Connection conn =openConnection();
		User user = this.selectUser(id);
        String sql = "select * from ERS_REIMBURSEMENT where REIMB_AUTHOR = ?";
        List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
        try {
			PreparedStatement ps = conn.prepareStatement(sql);
			/*if(user.getUserRole()==1) {
				ps.setString(1, "REIMB_AUTHOR");
			}
			if(user.getUserRole()==2) {
				ps.setString(1, "REIMB_RESOLVER");
			}*/
			ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()) {
            String authorUsername = selectUser(rs.getInt(7)).getUsername();
            String resolverUsername = selectUser(rs.getInt(8)).getUsername();
            reimbursement.add(new Reimbursement(rs.getInt(1), rs.getFloat(2),rs.getDate(3),rs.getDate(4), rs.getString(5),
            			rs.getBlob(6), authorUsername,resolverUsername,rs.getInt(9),rs.getInt(10)));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        closeConnection(conn);
		return reimbursement;
	}
	
	@Override
	public List<Reimbursement> managerReimbursements(String input){
		Connection conn =openConnection();
		//User user = this.selectUser(id);
        String sql1 = "select * from ERS_REIMBURSEMENT";
        String sql2 = "select * from ERS_REIMBURSEMENT where REIMB_STATUS_ID= ?";
        List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
        try {
        	PreparedStatement ps;
			if(input.equals("*")) {
				ps = conn.prepareStatement(sql1);
			}else {
				ps = conn.prepareStatement(sql2);
				ps.setString(1, input);
			}
			
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
            String authorUsername = selectUser(rs.getInt(7)).getUsername();
            String resolverUsername = selectUser(rs.getInt(8)).getUsername();
            reimbursement.add(new Reimbursement(rs.getInt(1), rs.getFloat(2),rs.getDate(3),rs.getDate(4), rs.getString(5),
            			rs.getBlob(6), authorUsername,resolverUsername,rs.getInt(9),rs.getInt(10)));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        closeConnection(conn);
		return reimbursement;
	}

	@Override
	public int insertReimbursement(int amount, String description, Blob receipt, int author, int type) {
		Connection conn =openConnection();
		String sql = 
        		" BEGIN "+
        		"INSERT_REIMBURSEMENT(?, ?, ?, ?, ?);" +
        		"END;"
        		;
        try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setString(2, description);
			ps.setBlob(3, receipt);
			ps.setInt(4, author);
			ps.setInt(5, type);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        closeConnection(conn);
		return -2; // should not be here. 
	}

	@Override
	public int updateReimbursement(int id, int resovler, int status) {
		Connection conn =openConnection();
        String sql = 
        		" BEGIN "+
        		"UPDATE_REIMBURSEMENT(?, ?, ?);" +
        		"END;"
        		;
        try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, resovler);
			ps.setInt(3, status);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        closeConnection(conn);
		return -2; // should not be here. 
	}
}

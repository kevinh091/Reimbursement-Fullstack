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
    private Connection conn;
    
    public DAOimpl() {
    	try{  								//connect and have the connection open
    		conn = DriverManager.getConnection(db_url, db_username, db_password);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
	@Override
	public User selectUser(String username) {
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
		return user;
	}
    
	@Override
	public User selectUser(int id) {
        String sql = "select * from ERS_USERS where ERS_USERS_ID = ?";
        User user = null;
        try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            	user = new User(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5),
            			rs.getString(6), rs.getInt(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public Reimbursement selectReimbursement(int id) {
        String sql = "select * from ERS_REIMBURSEMENT where REIMB_ID = ?";
        Reimbursement reimbursement = null;
        try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            reimbursement = new Reimbursement(rs.getInt(1), rs.getFloat(2),rs.getDate(3),rs.getDate(4), rs.getString(5),
            			rs.getBlob(6), rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursement;
	}
	
	@Override
	public List<Reimbursement> selectReimbursement(User user) {
        String sql = "select * from ERS_REIMBURSEMENT where ? = ?";
        List<Reimbursement> reimbursement = new ArrayList<Reimbursement>();
        try {
			PreparedStatement ps = conn.prepareStatement(sql);
			if(user.getUserRole()==1) {
				ps.setString(1, "REIMB_AUTHOR");
			}
			if(user.getUserRole()==2) {
				ps.setString(1, "REIMB_RESOLVER");
			}
			ps.setInt(2, user.getUserId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
            reimbursement.add(new Reimbursement(rs.getInt(1), rs.getFloat(2),rs.getDate(3),rs.getDate(4), rs.getString(5),
            			rs.getBlob(6), rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getInt(10)));
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursement;
	}

	@Override
	public int insertReimbursement(int amount, String description, Blob receipt, int author, int type) {
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
        
		return -2; // should not be here. 
	}

	@Override
	public int updateReimbursement(int id, User resovler, Integer status) {
        String sql = 
        		"UPDATE ERS_REIMBURSEMENT"+
        		"SET ? = '?', ?= '?', REIMB_RESOLVED=CURRENT_TIMESTAMP"+
        		"WHERE REIMB_ID = ?;"
        		;
        try {
			PreparedStatement ps = conn.prepareStatement(sql);
			if(resovler !=null) {
				ps.setString(1, "REIMB_RESOLVER");
				ps.setInt(2, resovler.getUserId());
			}
			if(status != null) {
				ps.setString(3, "REIMB_STATUS_ID");
				ps.setInt(4, status);
			}
			ps.setInt(5, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return -2; // should not be here. 
	}
}

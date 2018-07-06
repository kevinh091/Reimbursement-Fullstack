package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
}

package user;

public class User {
	private transient int userId;
	private String username;
	private transient String password;
	private String firstname;
	private String lastname;
	private String email;
	private int userRole;
	
	public User(int i, String u, String p, String f, String l, String e, int r) {
		userId =i;
		username =u;
		password =p;
		firstname =f;
		lastname =l;
		email =e;
		userRole = r;
	}
	
	public int userId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserRole() {
		return this.userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", lastname=" + lastname
				+ ", firstname=" + firstname + ", email=" + email + ", userRole=" + userRole + "]";
	}
}

package DAO_model;

public class User_model {
	private String username ,password,email,fullname,role;
	
	public User_model() {}
	
	public User_model(String username, String email, String fullname, String role) {
		this.username = username;
		this.email = email;
		this.fullname = fullname;
		this.role = role;
	}
	
	public User_model(String username, String password, String email, String fullname, String role) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.role = role;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User_model [username=" + username + ", password=" + password + ", email=" + email + ", fullname="
				+ fullname + ", role=" + role + "]";
	}


}
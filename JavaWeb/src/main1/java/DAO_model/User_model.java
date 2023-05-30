package DAO_model;

public class User_model {
	private String username ,password, email, fullname, role, city, tenduong, sonha;
	private String disabled = "0";
	private int idc;

	public User_model() {}
	
	public User_model(String username, String passsword, String email, String fullname) {
		this.username = username;
		this.email = email;
		this.password = passsword;
		this.fullname = fullname;
	}
	
	public User_model(String city, String tenduong, String sonha) {
		super();
		this.city = city;
		this.tenduong = tenduong;
		this.sonha = sonha;
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
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTenduong() {
		return tenduong;
	}

	public void setTenduong(String tenduong) {
		this.tenduong = tenduong;
	}

	public String getSonha() {
		return sonha;
	}

	public void setSonha(String sonha) {
		this.sonha = sonha;
	}

	public int getIdc() {
		return idc;
	}

	public void setIdc(int idc) {
		this.idc = idc;
	}
	
	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	@Override
	public String toString() {
		return "User_model [username=" + username + ", password=" + password + ", email=" + email + ", fullname="
				+ fullname + ", role=" + role + "]";
	}


}
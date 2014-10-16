package com.citihub.siteassessor;

/**
 * User details 
 * @author citihubuser
 *
 */
public class User {
	private String id;
	private String user;
	private String password;
	private String status;
	
	
	public User(String id, String user, String password, String status) {
		super();
		this.id = id;
		this.user = user;
		this.password = password;
		this.status = status;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user=" + user + ", password=" + password
				+ ", status=" + status + "]";
	}	
}

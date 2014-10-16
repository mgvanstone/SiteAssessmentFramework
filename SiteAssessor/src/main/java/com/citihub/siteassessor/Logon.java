package com.citihub.siteassessor;

/**
 * Logon details
 * @author citihubuser
 *
 */
public class Logon {
	private String username;
	private String password;
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
	@Override
	public String toString() {
		return "Logon [username=" + username + ", password=" + password + "]";
	}
	
	
}

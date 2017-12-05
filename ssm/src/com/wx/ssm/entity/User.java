package com.wx.ssm.entity;

public class User extends IdEntity{
	private String name;
	private String password;
	private int enabled ;
	//盐值, 进行密码加密
	private String salt;
	//该用户拥有的角色
	private Role role;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", enabled=" + enabled + ", salt=" + salt + ", role="
				+ role + "]";
	}
	
}

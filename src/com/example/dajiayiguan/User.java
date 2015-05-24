package com.example.dajiayiguan;

public class User {

	private static User instance = null;
	
	public User(){
		
	}
	
	public static User getInstance(){
		if(instance == null){
			instance = new User();
		}
		return instance;
	}
	
	public void initUser(String uid, String mobile, String realName, String sex){
		this.uid = uid;
		this.mobile = mobile;
		this.realName = realName;
		this.sex = sex;
	}
	
	public String getUid(){
		return uid;
	}
	public String getMobile(){
		return mobile;
	}
	public String getRealName(){
		return realName;
	}
	public String getSex(){
		return sex;
	}
	private String uid;
	private String mobile;
	private String realName;
	private String sex;
	
	
}

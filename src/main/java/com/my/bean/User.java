package com.my.bean;

public class User {
    private String name;
    private  String password;

    public void setPassword(String password){
        this.password=password;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + "]";
	}
    
}

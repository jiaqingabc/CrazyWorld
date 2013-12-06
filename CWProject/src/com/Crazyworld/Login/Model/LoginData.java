package com.Crazyworld.Login.Model;

public class LoginData {
	
	private String LoginKey = null;
	private int stateCode = 0;

	public LoginData() {
		// TODO Auto-generated constructor stub
	}

	public String getLoginKey() {
		return LoginKey;
	}

	public void setLoginKey(String loginKey) {
		LoginKey = loginKey;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return super.equals(o);
	}
}

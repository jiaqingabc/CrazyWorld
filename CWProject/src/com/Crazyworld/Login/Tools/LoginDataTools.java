package com.Crazyworld.Login.Tools;

import com.Crazyworld.Login.Model.LoginData;

import android.content.Context;
import android.telephony.TelephonyManager;

public class LoginDataTools {
	
	private TelephonyManager phoneManager = null;

	public LoginDataTools(TelephonyManager phoneManager) {
		this.phoneManager = phoneManager;
	}
	
	public LoginData getLoginData(){
		LoginData data = new LoginData();
		String key = this.phoneManager.getDeviceId();
		data.setLoginKey(key);
		return data;
	}
	
	public boolean keyMatcher(LoginData data){
		boolean flag = false;
		flag = getLoginData().equals(data);
		return flag;
	}
}

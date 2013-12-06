package com.Crazyworld.Login;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import com.Crazyworld.Login.Model.LoginData;
import com.Crazyworld.Login.Tools.LoginDataTools;
import com.Crazyworld.Login.Tools.NetworkTools;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Window;

public class LoginActivty extends Activity {
	private LoginDataTools loginTools = null;
	private NetworkTools networkTools = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		init();
		initView();
		
		LoginTask task = new LoginTask();
		task.execute(this.networkTools);
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		
		super.onStart();
	}

	private void initView() {

	}

	private void init() {
		this.loginTools = new LoginDataTools(
				(TelephonyManager) getSystemService(TELEPHONY_SERVICE));
		this.networkTools = new NetworkTools(loginTools.getLoginData());
	}
	
	class LoginTask extends AsyncTask<NetworkTools, Void, LoginData>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected LoginData doInBackground(NetworkTools... params) {
			// TODO Auto-generated method stub
			NetworkTools tools = params[0];
			LoginData d = null;
			try {
				d = tools.postLoginKey();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return d;
		}

		@Override
		protected void onPostExecute(LoginData result) {
			// TODO Auto-generated method stub
			boolean flag = false;
			if(result != null){
				flag = loginTools.getLoginData().equals(result);
				if(flag){//login success
					
				}
			} else {//login false
				
			}
			
		}
	}
}

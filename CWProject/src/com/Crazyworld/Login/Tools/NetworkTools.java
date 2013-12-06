package com.Crazyworld.Login.Tools;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.Crazyworld.Login.LoginConstant;
import com.Crazyworld.Login.Model.LoginData;
import com.google.gson.Gson;

public class NetworkTools {
	
	HttpClient client = null;
	Gson gson = null;
	LoginData data = null;

	public NetworkTools(LoginData data) {
		this.gson = new Gson();
		this.data = data;
		
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 15*1000);
		params.setParameter("charset", HTTP.UTF_8);
		this.client = new DefaultHttpClient(params);
	}

	public LoginData postLoginKey() throws URISyntaxException, ClientProtocolException, IOException{
		LoginData d = null;
		String value = null;
		
		if(this.data == null || (value = getKeyStr()) == null){
			return null;
		}
		
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		BasicNameValuePair pair = new BasicNameValuePair(LoginConstant.LOGIN_KEY, value);
		HttpPost post = new HttpPost();
		post.addHeader("charset", HTTP.UTF_8);
		post.setURI(new URI(LoginConstant.LOGIN_SERVER_URI));
		HttpResponse response = this.client.execute(post);
		
		int stateCode = 0;
		String resStr = null;
		if(response != null){
			stateCode = response.getStatusLine().getStatusCode();
			resStr = EntityUtils.toString(response.getEntity());
		}
		
		if(stateCode == 200){
			d = getLoginData(resStr);
			System.out.println("Upload resStr : "+resStr);
		} else {
			d = new LoginData();
			System.out.println("Upload stateCode : "+stateCode);
		}
		
		d.setStateCode(stateCode);
		
		return d;
	}
	
	public String getKeyStr(){
		String keyStr = null;
		if(this.data == null){
			return null;
		} 
		keyStr = this.gson.toJson(data);
		return keyStr;
	}
	
	private LoginData getLoginData(String dataStr){
		LoginData data = new LoginData();
		if(dataStr == null){
			return null;
		}
		this.gson.fromJson(dataStr, LoginData.class);
		return data;
	}
}

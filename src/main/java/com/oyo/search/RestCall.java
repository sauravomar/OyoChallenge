package com.oyo.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestCall {
	
	public static String call(String urlWithParam){
		
		URL url = null;
		HttpURLConnection conn = null;
		try {
			
			StringBuilder sb =  new StringBuilder();
			url = new URL(urlWithParam);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			
			return sb.toString();
			
		  } catch (MalformedURLException e) {
			e.printStackTrace();
		  } catch (Exception e) {
			e.printStackTrace();
		  }finally{
			 if(conn != null) conn.disconnect();
		  }
		return null;
	}
}

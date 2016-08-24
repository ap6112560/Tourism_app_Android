package com.example.nav;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
public class async {
String sync(String s){
	int REGISTRATION_TIMEOUT = 15 * 1000;
    int WAIT_TIMEOUT = 60 * 1000;
    HttpClient httpclient = new DefaultHttpClient();
    HttpParams params1 = httpclient.getParams();
    HttpResponse response = null;
    String content =  "";
    HttpConnectionParams.setConnectionTimeout(params1, REGISTRATION_TIMEOUT);
    HttpConnectionParams.setSoTimeout(params1, WAIT_TIMEOUT);
    ConnManagerParams.setTimeout(params1, WAIT_TIMEOUT);
    String url = null;
    if(s.startsWith("https://")){
    	url=s;
    }
    else if(s.equals("update")){
    	url="https://spreadsheets.google.com/tq?tqx=out:text&tq=select%20*%20where%20C%3D%22xx%22&key=1W7gYiZBqDv_56Bp92vSdbYBd8DyN_WNZnBZZlRS5bxQ";
    }
   HttpGet httpget=new HttpGet(url);
    
    try {
		response = httpclient.execute(httpget);
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    StatusLine statusLine = response.getStatusLine();
    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
			response.getEntity().writeTo(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        content = out.toString();    
    }
    return content;
}}
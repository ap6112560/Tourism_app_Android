package com.example.shaws.fbloginsample;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.nav.MainActivity;
import com.example.nav.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
public class fb extends Fragment {
    public static CallbackManager callbackManager;
    Button share;
    public static String name=null,email=null;
    ShareDialog shareDialog;
    SharedPreferences s;
    LoginButton login;
    public static ProfilePictureView profile;
 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
			s=this.getActivity().getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
	        View v= inflater.inflate(R.layout.fb, container, false);
	        callbackManager = CallbackManager.Factory.create();
	        login = (LoginButton)v.findViewById(R.id.login_button);
	        profile = (ProfilePictureView)v.findViewById(R.id.picture);
	        shareDialog = new ShareDialog(this);
	        share = (Button)v.findViewById(R.id.share);
	        login.setReadPermissions("public_profile email");
	        share.setVisibility(View.INVISIBLE);
	       
	        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	        if (networkInfo != null && networkInfo.isConnected()) {
	        if(AccessToken.getCurrentAccessToken() != null){
	        	MainActivity.RequestData();
	        	if(name!=null)
	        		s.edit().putString(MainActivity.name,name).commit();
	        	else
	        		s.edit().putString(MainActivity.name,null).commit();
	        	if(email!=null)
	        		s.edit().putString(MainActivity.email,email).commit();
	        	else
	        		s.edit().putString(MainActivity.email,null).commit();
	            share.setVisibility(View.VISIBLE);
	            s.edit().putString(MainActivity.user, "logged").commit();
	        }}
	        login.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                if(AccessToken.getCurrentAccessToken() != null) {	                	
	                    share.setVisibility(View.INVISIBLE);
	                    name=null;
	    	        	s.edit().putString(MainActivity.name,null).commit();
	    	        	email=null;
	    	        	s.edit().putString(MainActivity.email,null).commit();
	                    profile.setProfileId(null);
	                    s.edit().putString(MainActivity.user, "null").commit();
	                }
	            }
	        });
	       share.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                ShareLinkContent content = new ShareLinkContent.Builder().build();
	                shareDialog.show(content);	 
	            }
	        });
	        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
	            @Override
	            public void onSuccess(LoginResult loginResult) {
	 
	                if(AccessToken.getCurrentAccessToken() != null){
	                    MainActivity.RequestData();
	                    Handler h=new Handler();
	                    Runnable r=new Runnable() {
	                		
	                		@Override
	                		public void run() {
	                    s.edit().putString(MainActivity.user, "logged").commit();
	                    if(name!=null)
	    	        		s.edit().putString(MainActivity.name,name).commit();
	    	        	else
	    	        		s.edit().putString(MainActivity.name,null).commit();
	    	        	if(email!=null)
	    	        		s.edit().putString(MainActivity.email,email).commit();
	    	        	else
	    	        		s.edit().putString(MainActivity.email,null).commit();
	                    share.setVisibility(View.VISIBLE);}
	                	};
                		long delayMillis=2000;
                		h.postDelayed(r, delayMillis);
	                }
	            }
	 
	            @Override
	            public void onCancel() {
	 
	            }
	 
	            @Override
	            public void onError(FacebookException exception) {
	            }
	        });
	return v;
	}
}
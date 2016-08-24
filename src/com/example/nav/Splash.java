package com.example.nav;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
public class Splash extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		Handler h=new Handler();
	    WebView view ;    
	    setContentView(R.layout.splash);
	    view=(WebView) findViewById(R.id.webView1);
	    view.getSettings().setUseWideViewPort(true);
	    view.getSettings().setLoadWithOverviewMode(true);
	    view.loadUrl("file:///android_asset/path_promo.gif");
	    Runnable r=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			startActivity(new Intent(getApplicationContext(), MainActivity.class));
			finish();
		}
	};
		long delayMillis=16000;
		h.postDelayed(r, delayMillis);
	} 
}
package com.example.nav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class soon extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	        // Creating view correspoding to the fragment
		View v= inflater.inflate(R.layout.soon, container, false);
		return v;	
	    }
}
package com.example.nav;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dailymotion.websdk.DMWebVideoView;
public class vid extends Fragment {
	static DMWebVideoView mVideoView;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	      	View v= inflater.inflate(R.layout.video, container, false);
	        mVideoView = ((DMWebVideoView)v.findViewById(R.id.dmWebVideoView));
	        mVideoView.setVideoId("x1zlbsz");
	        mVideoView.setAutoPlay(true);
	        return v;
	    }
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mVideoView.onPause();
        }
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mVideoView.onResume();
        }
	}
}
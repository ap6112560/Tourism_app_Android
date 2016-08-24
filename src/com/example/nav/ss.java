package com.example.nav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ViewFlipper;

public class ss extends Fragment {
	private static final int SWIPE_MIN_DISTANCE = 120,SWIPE_THRESHOLD_VELOCITY = 200;
	private ViewFlipper mViewFlipper;	
	Button b1,b2;
	@SuppressWarnings("deprecation")
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	        View v=inflater.inflate(R.layout.show, container, false);
			mViewFlipper = (ViewFlipper) v.findViewById(R.id.view_flipper);
			mViewFlipper.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(final View view, final MotionEvent event) {
					detector.onTouchEvent(event);
					return true;
				}
			}); 
			b1=(Button) v.findViewById(R.id.play);
			b2=(Button) v.findViewById(R.id.stop);
			b1.setVisibility(View.INVISIBLE);
			flip();
			b1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					flip();	
					b1.setVisibility(View.INVISIBLE);
					b2.setVisibility(View.VISIBLE);
				}
			});
			b2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					//stop auto flipping 
					mViewFlipper.stopFlipping();
					b1.setVisibility(View.VISIBLE);
					b2.setVisibility(View.INVISIBLE);
				}
			});
			return v;
	    }
	class SwipeGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			try {
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.stopFlipping();
					mViewFlipper.showNext();
					if(b2.getVisibility()==View.VISIBLE)
					flip();
					return true;
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.stopFlipping();
					mViewFlipper.showPrevious();
					if(b2.getVisibility()==View.VISIBLE)
					flip();
					return true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}
	}
	void flip(){
		mViewFlipper.setAutoStart(true);
		mViewFlipper.setFlipInterval(4000);
		mViewFlipper.startFlipping();
		}}
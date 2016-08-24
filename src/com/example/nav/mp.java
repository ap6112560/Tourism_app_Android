package com.example.nav;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class mp extends Fragment implements OnMapReadyCallback{
	private static final LatLng goa = new LatLng(15.2993,74.1240),hp = new LatLng(31.1048,77.1734),ladakh = new LatLng(34.1700,77.5800),kerala = new LatLng(10.8505,76.2711),darjeeling = new LatLng(27.0360,88.2627);
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		View v=inflater.inflate(R.layout.map, container, false);
	    SupportMapFragment mSupportMapFragment; 
	    mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
	    mSupportMapFragment.getMapAsync(this);
        return v;
	}

	@Override
	public void onMapReady(GoogleMap gMap) {
		// TODO Auto-generated method stub
		gMap.addMarker(new MarkerOptions().position(goa).title("Goa"));
		gMap.addMarker(new MarkerOptions().position(hp).title("Himachal Pradesh"));
		gMap.addMarker(new MarkerOptions().position(ladakh).title("Ladakh"));
		gMap.addMarker(new MarkerOptions().position(kerala).title("Kerala"));
		gMap.addMarker(new MarkerOptions().position(darjeeling).title("Darjeeling"));
    	gMap.setMyLocationEnabled(true);
    	gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.1048,77.1734),16));
    	gMap.animateCamera(CameraUpdateFactory.zoomTo(4), 2000, null);
    	gMap.getUiSettings().setCompassEnabled(true);
    	gMap.getUiSettings().setTiltGesturesEnabled(true);
    	gMap.getUiSettings().setRotateGesturesEnabled(true);
    	gMap.setBuildingsEnabled(true);    
	}
}
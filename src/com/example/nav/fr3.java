package com.example.nav;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
public class fr3 extends AppCompatActivity {
Toolbar toolbar;
CollapsingToolbarLayout collapsingToolbarLayout;

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
setContentView(R.layout.fr3);
initInstancesDrawer();
}

private void initInstancesDrawer() {
toolbar = (Toolbar) findViewById(R.id.toolbar);
toolbar.setNavigationIcon(R.drawable.ic_back);
toolbar.setNavigationOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		NavUtils.navigateUpFromSameTask(getParent());
	}
});
setSupportActionBar(toolbar);
collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
collapsingToolbarLayout.setTitle("Himachal Pradesh");
}}
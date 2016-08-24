package com.example.nav;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.shaws.fbloginsample.fb;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.animation.Animator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
 boolean y=false,flag=true;
 int mPosition = -1;
 String s;
 static ProgressDialog dialog;
 String[] mCountries ;
 int[] mFlags = new int[]{
 R.drawable.goa1,
 R.drawable.hp1,
 R.drawable.ladakh1,
 R.drawable.kerala1,
 R.drawable.darjeeling1,
 R.drawable.form,
 R.drawable.map
 };
 ImageView imageView;
 ImageButton imageButton;
 LinearLayout revealView, layoutButtons;
 Animation alphaAnimation;
 float pixelDensity;
 SharedPreferences sp;
 public static final String MyPREFERENCES = "Prefs",ver = "version",update="update",lik="link",name="name",email="email",user="user" ;
 private DrawerLayout mDrawerLayout;
 private ListView mDrawerList;
 private ActionBarDrawerToggle mDrawerToggle;
 private LinearLayout mDrawer ;
 private List<HashMap<String,String>> mList ;
 private SimpleAdapter mAdapter;
 final private String COUNTRY = "country",FLAG = "flag";
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 FacebookSdk.sdkInitialize(getApplicationContext());
 setContentView(R.layout.activity_main);
 sp=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
 mCountries = getResources().getStringArray(R.array.countries);
 
 mDrawerList = (ListView) findViewById(R.id.drawer_list);
 
 mDrawer = ( LinearLayout) findViewById(R.id.drawer);
 
 mList = new ArrayList<HashMap<String,String>>();
 for(int i=0;i<=6;i++){
 HashMap<String, String> hm = new HashMap<String,String>();
 hm.put(COUNTRY, mCountries[i]);
 hm.put(FLAG, Integer.toString(mFlags[i]) );
 mList.add(hm);
 }
 
 String[] from = { FLAG,COUNTRY };
 
 int[] to = { R.id.flag , R.id.country};
 
 mAdapter = new SimpleAdapter(this, mList, R.layout.drawer_layout, from, to);

 mDrawerList.setAdapter(mAdapter);

 mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
 
 mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open,R.string.drawer_close){
 
 /** Called when drawer is closed */
 public void onDrawerClosed(View view) {

getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
 highlightSelectedCountry();
 }
 
/** Called when a drawer is opened */
 public void onDrawerOpened(View drawerView) {

getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer1);
getSupportActionBar().setTitle("Select");
 }
 };
 mDrawerLayout.setDrawerListener(mDrawerToggle);
 
 mDrawerList.setOnItemClickListener(new OnItemClickListener() {
 
@Override
 public void onItemClick(AdapterView<?> arg0, View arg1, int position,
 long arg3) {
	y=false;
	 showFragment(position);
 mDrawerLayout.closeDrawer(mDrawer);
 }
 });
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
pixelDensity = getResources().getDisplayMetrics().density;

imageView = (ImageView) findViewById(R.id.imageView);
imageButton = (ImageButton) findViewById(R.id.launchTwitterAnimation);
revealView = (LinearLayout) findViewById(R.id.linearView);
layoutButtons = (LinearLayout) findViewById(R.id.layoutButtons);

alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);}
 
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
 if (mDrawerToggle.onOptionsItemSelected(item)) {
 return true;
 }
 switch (item.getItemId()) {        
 case R.id.item2: 
	 getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new ss()).commit(); 
	 getSupportActionBar().setTitle("Contact");
	 s="Contact";
	 y=true;
	 if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
		 mDrawerLayout.closeDrawers();
	 break;
 case R.id.item3:   
	 getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new vid()).commit(); 
	 getSupportActionBar().setTitle("Video");
	 s="Video";
	 y=true;
	 if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
		 mDrawerLayout.closeDrawers(); 
	 break;
 case R.id.item5:
	 getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,new fb()).commit(); 
	 getSupportActionBar().setTitle("Signin");
	 s="Login";
	 y=true;
	 if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
		 mDrawerLayout.closeDrawers();
	 break;
 case R.id.item4:			
			ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	        if (networkInfo != null && networkInfo.isConnected()) {
	        	dialog = new ProgressDialog(this);
	        	dialog.setTitle("Checking for updates");
	        	dialog.setMessage("Please wait...");
	        	dialog.setIndeterminate(false);
	        	dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        	dialog.show(); 
	        	
	        	new AsyncTask<String, Void, String>(){

					@Override
					protected String doInBackground(String... params) {
						// TODO Auto-generated method stub
						String x = new async().sync(params[0]);
						 return x;
					}
					@Override
					protected void onPostExecute(String result) {
						// TODO Auto-generated method stub
						MainActivity.dialog.dismiss(); 
						check(result,"update");
				         
					}
	        		
	        	}.execute("update");
	        }
	        else
	        	Toast.makeText(getApplicationContext(), "Check your Internet Connection", Toast.LENGTH_SHORT).show();
			
		break;
	 default:finish();
 }
 return super.onOptionsItemSelected(item);
 }
 
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
 getMenuInflater().inflate(R.menu.main, menu);
 return true;
 }
 
 public void showFragment(int position){
if(position==0){
	startActivity(new Intent(getApplicationContext(), fr2.class)); 
 }
 else if(position==1){
	 startActivity(new Intent(getApplicationContext(), fr3.class)); 
 }
 else if(position==2){
	 startActivity(new Intent(getApplicationContext(), fr4.class)); 
 }
 else if(position==3){
	 startActivity(new Intent(getApplicationContext(), fr5.class)); 
 }
 else if(position==4){
	 startActivity(new Intent(getApplicationContext(), fr6.class));  
 }
 else if(position==6){ 
 getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new mp()).commit();
}
 else  
	 getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new fr1()).commit();
 }
 
 public void highlightSelectedCountry(){
	 if(y==true)
		 getSupportActionBar().setTitle(s);
 int selectedItem = mDrawerList.getCheckedItemPosition();
 mPosition = selectedItem;
 if((mPosition!=-1)&&(y==false))
	 getSupportActionBar().setTitle(mCountries[mPosition]);
 }
 
 public void check(String result,String work){
	 int start = result.indexOf("{", result.indexOf("{") + 1);
     int end = result.lastIndexOf("}");
     String jsonResponse = result.substring(start, end);
     try {
         JSONObject table = new JSONObject(jsonResponse);
          JSONArray rows = table.getJSONArray("rows");
        	 for (int r = 0; r < rows.length(); ++r) {
          		
                 JSONObject row =rows.getJSONObject(0);
                 JSONArray columns = row.getJSONArray("c");
                 String version=columns.getJSONObject(9).getString("v");
                 String link=columns.getJSONObject(12).getString("v");
                 Intent broadcastIntent = new Intent();
                 broadcastIntent.setAction(MyRequestReceiver.PROCESS_RESPONSE);
                 broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
                 Bundle b=new Bundle();
                 if(work.equals("update")){
                 if((!version.equals("null"))&&(!version.equals("1"))){
                     b.putString(update, "update");
                     b.putString(ver, version);
                     b.putString(lik, link);
                     broadcastIntent.putExtras(b);
                     sendBroadcast(broadcastIntent);
                 }else Toast.makeText(getApplicationContext(), "No update available", Toast.LENGTH_LONG).show();
                 }
     	}}
          catch (JSONException e) {
         e.printStackTrace();
 }}
 
 public class fr1 extends Fragment {
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
			View v= inflater.inflate(R.layout.fr1, container, false);
					if(sp.getString(user, "").equals("logged"))
						getChildFragmentManager().beginTransaction().replace(R.id.fl, new rm()).commit();
					else
						getChildFragmentManager().beginTransaction().replace(R.id.fl, new lm()).commit();
			return v;		
		    }
	}
 
 public static void RequestData(){
     GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
         @Override
         public void onCompleted(JSONObject object,GraphResponse response) {

             JSONObject json = response.getJSONObject();
             try {
                 if(json != null){
                     fb.name=json.getString("name");
                     fb.email=json.getString("email");
                     fb.profile.setProfileId(json.getString("id"));
                 }

             } catch (JSONException e) {
                 e.printStackTrace();
             }
         }
     });
     Bundle parameters = new Bundle();
     parameters.putString("fields", "id,name,link,email,picture");
     request.setParameters(parameters);
     request.executeAsync();
 }

 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     fb.callbackManager.onActivityResult(requestCode, resultCode, data);
 }
 public void launchTwitter(View view) {

     
     int x = imageView.getRight();
     int y = imageView.getBottom();
     x -= ((28 * pixelDensity) + (16 * pixelDensity));

     int hypotenuse = (int) Math.hypot(imageView.getWidth(), imageView.getHeight());

     if (flag) {

         imageButton.setBackgroundResource(R.drawable.rounded_cancel);
         imageButton.setImageResource(R.drawable.image_cancel);

         FrameLayout.LayoutParams parameters = (FrameLayout.LayoutParams)
                 revealView.getLayoutParams();
         parameters.height = imageView.getHeight();
         revealView.setLayoutParams(parameters);

         Animator anim = ViewAnimationUtils.createCircularReveal(revealView, x, y, 0, hypotenuse);
         anim.setDuration(700);

         anim.addListener(new Animator.AnimatorListener() {
             @Override
             public void onAnimationStart(Animator animator) {

             }

             @Override
             public void onAnimationEnd(Animator animator) {
                 layoutButtons.setVisibility(View.VISIBLE);
                 layoutButtons.startAnimation(alphaAnimation);
             }

             @Override
             public void onAnimationCancel(Animator animator) {

             }

             @Override
             public void onAnimationRepeat(Animator animator) {

             }
         });

         revealView.setVisibility(View.VISIBLE);
         anim.start();

         flag = false;
     } else {

         imageButton.setBackgroundResource(R.drawable.rounded_button);
         imageButton.setImageResource(R.drawable.twitter_logo);

         Animator anim = ViewAnimationUtils.createCircularReveal(revealView, x, y, hypotenuse, 0);
         anim.setDuration(400);

         anim.addListener(new Animator.AnimatorListener() {
             @Override
             public void onAnimationStart(Animator animator) {

             }

             @Override
             public void onAnimationEnd(Animator animator) {
                 revealView.setVisibility(View.GONE);
                 layoutButtons.setVisibility(View.GONE);
             }

             @Override
             public void onAnimationCancel(Animator animator) {

             }

             @Override
             public void onAnimationRepeat(Animator animator) {

             }
         });

         anim.start();
         flag = true;
     }
 }
}
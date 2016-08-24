package com.example.nav;

import java.util.List;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;
public class rm extends Fragment implements  ValidationListener,  TextWatcher, OnCheckedChangeListener {
int pos=0;
@NotEmpty
@Order(1)
EditText e1;
@NotEmpty
@Order(2)
EditText e2;
@NotEmpty
@Order(3)
EditText e3;
@NotEmpty
@Length(min = 10, max = 10)
@Order(4)
EditText e4;
@Email
@Order(5)
EditText e5;
int tothi=0;
Button b;
RadioGroup r;
ListView l;
String option;
String [] check,s;
String smit="",mit="https://docs.google.com/forms/d/1ToOfL-1a5z-Ci9Le4W8AfEmZuKj2SSp8EqeGFLpt6i4/formResponse?ifq";
RadioButton rb1;
SharedPreferences sp;
private Validator mValidator;
private boolean click=false;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		sp=this.getActivity().getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
		View v= inflater.inflate(R.layout.rm, container,false);
		check=getResources().getStringArray(R.array.othreq);
		e1=(EditText) v.findViewById(R.id.editText1);
		e2=(EditText) v.findViewById(R.id.editText2);
		e3=(EditText) v.findViewById(R.id.editText3);
		e4=(EditText) v.findViewById(R.id.editText4);
		e5=(EditText) v.findViewById(R.id.editText5);
		l=(ListView) v.findViewById(R.id.lv);
		ArrayAdapter<String> lv=new ArrayAdapter<String>(getActivity(), R.layout.abc_list_menu_item_checkbox, check);
		l.setAdapter(lv);
		l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		int num=l.getCount();
		for(int j=0;j<num;j++){
			View vi=lv.getView(j, null, l);
			vi.measure(0, 0);
			tothi+=vi.getMeasuredHeight();
		}
		tothi+=l.getDividerHeight()*(num-1);
		ViewGroup.LayoutParams p=l.getLayoutParams();
		p.height=tothi;
		l.setLayoutParams(p);
		l.requestLayout();
		r=(RadioGroup) v.findViewById(R.id.rd);
		r.setOnCheckedChangeListener(this);
		rb1=(RadioButton) v.findViewById(R.id.radioButton1);
		rb1.setChecked(true);
		if(!sp.getString(MainActivity.name, "").equals(null)){
			e1.setText(sp.getString(MainActivity.name, ""));
		}
		if(!sp.getString(MainActivity.email, "").equals(null)){
			e5.setText(sp.getString(MainActivity.email, ""));
		}

		e1.addTextChangedListener(this);
		e2.addTextChangedListener(this);
		e3.addTextChangedListener(this);
		e4.addTextChangedListener(this);
		e5.addTextChangedListener(this);
		b=(Button) v.findViewById(R.id.button1);
		s=getResources().getStringArray(R.array.checks);
		ArrayAdapter<String> ad=new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, s);
		Spinner sp=(Spinner) v.findViewById(R.id.spinner1);
		sp.setAdapter(ad);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				pos=position;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
		sp.setSelection(-1);
b.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if(click)
		{smit+="&entry.1638461458=null"+"&entry.849342282="+e1.getText()+"&entry.1071647991="+e2.getText()+"&entry.475037207="+e3.getText()+"&entry.650102252="+e4.getText()+"&entry.1605832281="+e5.getText()+option+"&entry.1405393596="+s[pos];
		 String com="&entry.946988664=";    
		 for (int i=0; i<l.getCount(); i++) {
			 if (l.isItemChecked(i)){   
				 smit += com+l.getItemAtPosition(i);        
		 }}
		 smit+="&entry.1462072038=null&entry.137377776=null&entry.1905792573=0&entry.1844289190=null&submit=Submit"; 
		 mit+=smit;

		String all="@#&=*+-_.,:!?()/~'%";
		 try {mit=Uri.encode(mit,all);
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		 //e3.setText(mit);	
			ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	        if (networkInfo != null && networkInfo.isConnected()) {
		    final ProgressDialog pd=ProgressDialog.show(getActivity(), "registering your response", "Please wait...",true);
		    new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				return  new async().sync(params[0]);
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				pd.dismiss();
				getFragmentManager().beginTransaction().replace(R.id.fl, new soon()).commit();	
			}
		}.execute(mit);
	        }
	        else Toast.makeText(getActivity(), "Check your Internet Connection..", Toast.LENGTH_SHORT).show();
	        }
		else Toast.makeText(getActivity(), "Kindly check if you have filled all the red fields properly..", Toast.LENGTH_SHORT).show();
	}
});
mValidator = new Validator(this);
mValidator.setValidationListener(this);
mValidator.setValidationMode(Validator.Mode.BURST);
		return v;
	}

	@Override
	public void onValidationSucceeded() {
		// TODO Auto-generated method stub
		click=true;
	}
	@Override
	public void onValidationFailed(List<ValidationError> errors) {
		// TODO Auto-generated method stub
		click=false;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		mValidator.validateTill(getView());
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		if(checkedId==R.id.radioButton2)
		option="&entry.128024644=F";
		else
			option="&entry.128024644=M";
	}
}

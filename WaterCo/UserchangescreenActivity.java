package com.example.waterco;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.myutility.KeywordStore;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class UserchangescreenActivity extends Activity {

	String alertmsg = "";
	
	FrameLayout   frmupdateuser, frmsetting;
	
	Button  btnupdateuser, btnsetting;
	
	ImageView imbgoback;
	
	
	//Button btnnewusercancel, btnnewusercreate;
	//EditText edtnewuserusername, edtnewuserpassword;
	
	Button btnupdatecancel, btnupdateuserpass;
	EditText edtupdateoldpass, edtupdatenewpass, edtupdateconnewpass;
	
	
	Button btnsettingcacel, btnsettingupdate;
	
	
	SQLiteDatabase db; 
	
	TextView  txtdate, txtvaluepooltmp, txtlabelpooltmp, txtvaluespatmp, txtlabelspatmp, txtvaluesolartmp, txtlabelsolartmp;
	
	EditText edtsysip, edtsysport, edtclientid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userchangescreen);
		
		
		db = this.openOrCreateDatabase("WATERCO.DB",MODE_PRIVATE,null);
		
		imbgoback = (ImageView) findViewById(R.id.imageView2);
		
		
	
		
		//frmnewuser = (FrameLayout) findViewById(R.id.frmnewuser);
		frmupdateuser = (FrameLayout) findViewById(R.id.frmupdateuser);
		frmsetting = (FrameLayout) findViewById(R.id.frmcontrolset);
		
		frmupdateuser.setVisibility(View.VISIBLE);
		frmsetting.setVisibility(View.INVISIBLE);
		//frmnewuser.setVisibility(View.VISIBLE);
		
		
		//txttime = (TextView) findViewById(R.id.txttime);
		txtdate = (TextView) findViewById(R.id.txtdate);
		txtvaluepooltmp = (TextView) findViewById(R.id.txtvaluepooltmp);
		txtlabelpooltmp = (TextView) findViewById(R.id.txtlabelpooltmp);
		txtvaluespatmp = (TextView) findViewById(R.id.txtvaluespatmp);
		txtlabelspatmp = (TextView) findViewById(R.id.txtlabelspatmp);
		txtvaluesolartmp = (TextView) findViewById(R.id.txtvaluesolartmp);
		txtlabelsolartmp = (TextView) findViewById(R.id.txtlabelsolartmp);
		
		Typeface tf = Typeface.createFromAsset(getAssets(), "font/GOTHIC.TTF");
		Typeface tfb = Typeface.createFromAsset(getAssets(), "font/GOTHICB.TTF");
		
		//txttime.setTypeface(tf);
		txtdate.setTypeface(tfb);
		
		
		txtvaluepooltmp.setTypeface(tf);
		txtlabelpooltmp.setTypeface(tfb);
		txtvaluespatmp.setTypeface(tf);
		txtlabelspatmp.setTypeface(tfb);
		txtvaluesolartmp.setTypeface(tf);
		txtlabelsolartmp.setTypeface(tfb);
	
		

		txtvaluepooltmp.setText( "28.5 " + (char) 0x00B0 + "C");
		txtvaluespatmp.setText( "23.4 " + (char) 0x00B0 + "C");
		txtvaluesolartmp.setText("36.2 " + (char) 0x00B0 + "C");
		
		
		txtdate.setTypeface(tfb);
		
		DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
		String date = df.format(Calendar.getInstance().getTime());
		
		txtdate.setText(date);
		
		
		
		//btnnewuser = (Button) findViewById(R.id.newuser);
		btnupdateuser = (Button) findViewById(R.id.edituser);
		btnsetting = (Button) findViewById(R.id.systemsetting);
		
		
		
		//btnnewuser.setBackgroundResource(R.drawable.squre_button_on);
		btnupdateuser.setBackgroundResource(R.drawable.squre_button_on);
		btnsetting.setBackgroundResource(R.drawable.squre_button);
		
		
		//btnnewuser.setTypeface(null, Typeface.BOLD);
		btnupdateuser.setTypeface(null, Typeface.BOLD);
		btnsetting.setTypeface(null, Typeface.NORMAL);
		
		
		edtsysip = (EditText)findViewById(R.id.edtsystemip);
		edtsysport = (EditText)findViewById(R.id.edtsystemport);
		edtclientid = (EditText)findViewById(R.id.edtclientid);
		
		
		btnsettingcacel = (Button) findViewById(R.id.btnsettingcacel);
		btnsettingupdate = (Button) findViewById(R.id.btnsettingupdate);
		
		
		// NER USER BUTTON
		/*
		btnnewusercancel = (Button) findViewById(R.id.btnnewusercacel);
		btnnewusercreate = (Button) findViewById(R.id.btnnewusercreate);
		edtnewuserpassword = (EditText) findViewById(R.id.edtpass);
		edtnewuserusername = (EditText) findViewById(R.id.edtnewid);
		
		btnnewusercancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				edtnewuserpassword.setText("");
				edtnewuserusername.setText("");
				
			}
		});
		
		
		btnnewusercreate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
								
				
				boolean flag = false;
				
				String username = edtnewuserusername.getText().toString();
				String password = edtnewuserpassword.getText().toString();
				
				if(!username.equals("") && !password.equals(""))
				{
					
					
					Cursor cursor =  db.rawQuery("Select * from login where username='"+username+"'",null);
					if(cursor.getCount() == 0)
					{
					
					  ContentValues values = new ContentValues();
					  values.put("username", username );
					  values.put("password", password );
					  db.insert("login", null, values);
					  db.close();

					  alertmsg = "Record Insert Successfully";
					  
					} 
					else
					{
						alertmsg = "Username Already EXISTS";
					}
					
					flag = true;
				}
				else
				{
					 alertmsg = "Username or password blank not allow";
					 flag = true;
				}
				
				edtnewuserusername.setText("");
				edtnewuserpassword.setText("");

				if(flag)
				{
				AlertDialog.Builder b1 = new Builder(UserchangescreenActivity.this);
				b1.setMessage(alertmsg);
				b1.setTitle("Login Alert");
				b1.setCancelable(false);
				
				b1.setPositiveButton("OK",new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.cancel();
					//	edtnewuserusername.requestFocus();						
						
					}
					  
				  });
				
				AlertDialog a11 = b1.create();				
				a11.show();
				flag = false;
				}
				
			}
		});
		
		
		*/
		
		
		// UPDATE USER 
		
		btnupdatecancel = (Button) findViewById(R.id.btnupdatecacel);
		btnupdateuserpass = (Button) findViewById(R.id.btnupdateuser);
		
		edtupdateoldpass = (EditText) findViewById(R.id.edtoldpass);
		edtupdatenewpass = (EditText) findViewById(R.id.edtnewpass);
		edtupdateconnewpass = (EditText) findViewById(R.id.edtconnewpass);
		
		
		
		btnupdatecancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				edtupdateoldpass.setText("");
				edtupdatenewpass.setText("");
				edtupdateconnewpass.setText("");
				
			}
		});
		
		
		btnupdateuserpass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				boolean flag = false;
				String usename = "admin"; // KeywordStore.UserName
			
				String oldpass = edtupdateoldpass.getText().toString().trim();
				String newpass = edtupdatenewpass.getText().toString().trim();
				String connewpass = edtupdateconnewpass.getText().toString().trim();
				
				if(!oldpass.equals("") && !newpass.equals("") && !connewpass.equals(""))					
				{			
					if(oldpass.equals(newpass))
					{
						 alertmsg = "Old password and new password both are equal";
						 flag = true;
					}
					
					if(!newpass.equals(connewpass))
					{
						 alertmsg = "New password and confirm password both are not equal";
						 flag = true;
					}
					
					if(!flag)
					{
						
						
						Cursor cursor =  db.rawQuery("Select * from login where username= '"+usename+"' and password='"+oldpass+"'",null);
						if(cursor.getCount() != 0)
						{
											
							db.execSQL("Update login Set password = '"+newpass+"' where username='"+usename+"'");
						
							alertmsg = "Record update successfully";
							flag = true;
						}
						else
						{
							alertmsg = "Old password not match";
							flag = true;
						}
					}
					
					
				}
				else
				{
					 alertmsg = "password fields blank not allow";
					 flag = true;
				}
				
				edtupdateoldpass.setText("");
				edtupdatenewpass.setText("");
				edtupdateconnewpass.setText("");

				
				if(flag)
				{
				AlertDialog.Builder b1 = new Builder(UserchangescreenActivity.this);
				b1.setMessage(alertmsg);
				b1.setTitle("Login Alert");
				b1.setCancelable(false);
				
				b1.setPositiveButton("OK",new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.cancel();
							
						if(alertmsg.equals("Record update successfully"))
						{
							Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage( getBaseContext().getPackageName());
							i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(i);
						}
						
					}
					  
				  });
				
				AlertDialog a11 = b1.create();				
				a11.show();
				flag = false;
				}
				
				
			}
		});
		
		
		
		btnsettingcacel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Cursor cursor =  db.rawQuery("Select sysip, sysport, clientid from setting", null);
				
				if (cursor.moveToFirst()) {

		           edtsysip.setText(cursor.getString(0).toString().trim());
		           edtsysport.setText(cursor.getString(1).toString().trim());
		           edtclientid.setText(cursor.getString(2).toString().trim());
		           
		           }
		              			
		    
		    
				
			}
		});
		
		
		btnsettingupdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				boolean flag = false;
				
				String  ip = edtsysip.getText().toString().trim();
				String  port = edtsysport.getText().toString().trim();
				String  cid = edtclientid.getText().toString().trim();
				
				
				if(!ip.equals("") && !port.equals("") && !cid.equals("") )
				{
					db.execSQL("UPDATE setting Set sysip='"+ip+"', sysport='"+port+"', clientid='"+cid+"'");					
					alertmsg = "Update successfully";
					flag = true;
				}
				else
				{
					alertmsg = "Empty field not allow";
					flag = true;
				}
				
				
				if(flag)
				{
				AlertDialog.Builder b1 = new Builder(UserchangescreenActivity.this);
				b1.setMessage(alertmsg);
				b1.setTitle("Login Alert");
				b1.setCancelable(false);
				
				b1.setPositiveButton("OK",new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.cancel();
							
						if(alertmsg.equals("Update successfully"))
						{
							
							Cursor cursor =  db.rawQuery("Select sysip, sysport, clientid from setting", null);
							
							if (cursor.moveToFirst()) {

					           edtsysip.setText(cursor.getString(0).toString().trim());
					           edtsysport.setText(cursor.getString(1).toString().trim());
					           edtclientid.setText(cursor.getString(2).toString().trim());
					          
					           }
							
						}
						
					}
					  
				  });
				
				AlertDialog a11 = b1.create();				
				a11.show();
				flag = false;
				}
				
				
			}
		});
		
		
		
		/*
		
		btnnewuser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
				//frmnewuser.setVisibility(View.VISIBLE);
				frmupdateuser.setVisibility(View.INVISIBLE);
				frmsetting.setVisibility(View.INVISIBLE);
				
			
				btnnewuser.setBackgroundResource(R.drawable.squre_button_on);
				btnupdateuser.setBackgroundResource(R.drawable.squre_button);
				btnsetting.setBackgroundResource(R.drawable.squre_button);
				
				btnnewuser.setTypeface(null, Typeface.BOLD);
				btnupdateuser.setTypeface(null, Typeface.NORMAL);
				btnsetting.setTypeface(null, Typeface.NORMAL);
				
				
			}
		});
		*/
		
		btnupdateuser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
				//frmnewuser.setVisibility(View.INVISIBLE);
				frmupdateuser.setVisibility(View.VISIBLE);
				frmsetting.setVisibility(View.INVISIBLE);
				
				//btnnewuser.setBackgroundResource(R.drawable.squre_button);
				btnupdateuser.setBackgroundResource(R.drawable.squre_button_on);
				btnsetting.setBackgroundResource(R.drawable.squre_button);
				
				//btnnewuser.setTypeface(null, Typeface.NORMAL);
				btnupdateuser.setTypeface(null, Typeface.BOLD);
				btnsetting.setTypeface(null, Typeface.NORMAL);
				
			}
		});

		btnsetting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		
				//frmnewuser.setVisibility(View.INVISIBLE);
				frmupdateuser.setVisibility(View.INVISIBLE);
				frmsetting.setVisibility(View.VISIBLE);
				
				
				//btnnewuser.setBackgroundResource(R.drawable.squre_button);
				btnupdateuser.setBackgroundResource(R.drawable.squre_button);
				btnsetting.setBackgroundResource(R.drawable.squre_button_on);
				
				//btnnewuser.setTypeface(null, Typeface.NORMAL);
				btnupdateuser.setTypeface(null, Typeface.NORMAL);
				btnsetting.setTypeface(null, Typeface.BOLD);
				
				
				Cursor cursor =  db.rawQuery("Select sysip, sysport, clientid from setting", null);
				
				if (cursor.moveToFirst()) {

		           edtsysip.setText(cursor.getString(0).toString().trim());
		           edtsysport.setText(cursor.getString(1).toString().trim());
		           edtclientid.setText(cursor.getString(2).toString().trim());
		         
		           }
		               
		        
				
				
			}
		});
		
		
		imbgoback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
		
	}
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    finish();
	}
	
}

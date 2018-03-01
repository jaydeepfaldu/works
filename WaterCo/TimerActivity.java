package com.example.waterco;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.Checksum;

import com.myutility.CustomAdaper;
import com.myutility.TimerModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.os.Bundle;
import android.text.StaticLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TimerActivity extends Activity  {
	
	//static boolean checkevery = false;
	static int checkevery = 1;
	
	
	static int timer_list_position;
	
	
	 
	
	ImageView imbgoback;
	
	TextView  txtdate, txtvaluepooltmp, txtlabelpooltmp, txtvaluespatmp, txtlabelspatmp, txtvaluesolartmp, txtlabelsolartmp;
	
	ListView timerlist;
	CustomAdaper adapter;	
	TimerActivity timeractivity;
	
	SQLiteDatabase db; 
	
    public  ArrayList<TimerModel > CustomListViewValuesArr = new ArrayList<TimerModel>();
    
    Button btnaddtimer;
      
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timerscreen);

		
		db = this.openOrCreateDatabase("WATERCO.DB",MODE_PRIVATE,null);
		
		
		
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
		
		
		
		imbgoback = (ImageView) findViewById(R.id.imageView2);
		
		imbgoback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackPressed();				
			}
		});	
		
		
		timerlist = (ListView)findViewById(R.id.timerlist);
		
		 adapter=new CustomAdaper( this, CustomListViewValuesArr);
		 timerlist.setAdapter( adapter );
		
		 loadList();
		 
		 btnaddtimer = (Button) findViewById(R.id.btnaddtime);
		 
		 
		 btnaddtimer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				
				//db.execSQL("INSERT INTO timmer (timername, timerstarttime, timerendtime, timerday, timeractive) values ('abc', '4:00pm', '8:80pm', 'Everyday', 1)");				
				//TimerModel time = new TimerModel("ABCD","4:50am-8:55pm","Everyday",true);
				//CustomListViewValuesArr.add( time );
				//adapter.notifyDataSetChanged();
				
				
				
				
				showUserDialog(false, null);
				
				
			}
		});
		 
		
	}
	
	
	 public void loadList()
     {
         
		// CustomListViewValuesArr.add( new TimerModel("xyz", "sdf", "asd", true));
		 
		 CustomListViewValuesArr.clear();
		 Cursor cursor = db.rawQuery("SELECT timerid, timername, timerstarttime, timerendtime, timerdays, timeractive FROM timmer", null);
		 
		 //Log.e("LENGTH", cursor.getCount()+"");
		 
		 if(cursor.getCount()>0)
		 {
			 cursor.moveToFirst();
			 while(!cursor.isAfterLast())
			 {
				 int active = cursor.getInt(5);
				 boolean boolactive = false;
				 
				 if(active==1)
				 {
					 boolactive = true;
				 }
				 
				 TimerModel tm = new TimerModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2)+" - "+cursor.getString(3), cursor.getString(4), boolactive);
				 CustomListViewValuesArr.add(tm);
				 cursor.moveToNext();
			 }
		 }
		 
		 adapter.notifyDataSetChanged();
		// if(!CustomListViewValuesArr.isEmpty())
		 //{
			//CustomListViewValuesArr.clear();
		//	adapter.notifyDataSetChanged();
		// }
		 
		 //Cursor cursor = db.rawQuery("SELECT * FROM timmer", null);
		 
		
         
         //CustomListViewValuesArr.add( time );
		 
         //for (int i = 0; i < 11; i++) {
              
             
         //}
          
     }
	
	 public void onItemClick(int mPosition)
     {
         TimerModel tempValues = (TimerModel ) CustomListViewValuesArr.get(mPosition);
         
         
 

         showUserDialog(true, tempValues);

         
         //Toast.makeText(TimerActivity.this,tempValues.getTimerName().toString(),Toast.LENGTH_SHORT).show();
     }
	
	
	@Override
	public void onBackPressed() {
	    super.onBackPressed();
	    finish();
	}
	
	public void ischeck(CheckedTextView ch)
	{
		if(ch.isChecked())
		{
			ch.setChecked(false);
			ch.setTextColor(getResources().getColor(R.color.gray));
		}
		else
		{
			ch.setChecked(true);
			ch.setTextColor(getResources().getColor(R.color.check));
		}
	}
	
	
	public void showUserDialog(final Boolean isUpdate, final TimerModel tm)
	{
		
		timeractivity.checkevery = 2;
		
		TextView txttitle;
		
		Button btnstarthrinc, btnstarthrdec, btnstartmninc, btnstartmndec, btnstartam, btnstartpm ;
		final TextView txtsthr;
		final TextView txtstmn;
		final TextView txtstampm;
		
		
		Button btnstophrinc, btnstophrdec, btnstopmninc, btnstopmndec, btnstopam, btnstoppm ;
		final TextView txtstophr;
		final TextView txtstopmn;
		final TextView txtstopampm;

		
		
		final Button btneveryday;
		
		Button btnsavetimer;
		
		
		
		final Dialog dialog = new Dialog(TimerActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);		
	    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setContentView(R.layout.timeraddlayout);
		dialog.setCancelable(false);
				
		final Spinner  spntimerfun = (Spinner) dialog.findViewById(R.id.drwtimerfunction);
		txttitle = (TextView) dialog.findViewById(R.id.textView1);
		
		
		List<String> timerfunctionlist = new ArrayList<String>();
		timerfunctionlist.add("Filtration Pump");
		timerfunctionlist.add("Heater Demand");
		timerfunctionlist.add("Solar");
		timerfunctionlist.add("Light");
		
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinnerlayout, timerfunctionlist);		 
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		 spntimerfun.setAdapter(dataAdapter);
		 
		 btneveryday = (Button) dialog.findViewById(R.id.btncancel);
		 btnsavetimer = (Button) dialog.findViewById(R.id.btnheatdemand);
		 
		
		 btnstartam = (Button) dialog.findViewById(R.id.btnsam);
		 btnstartpm = (Button) dialog.findViewById(R.id.btnspm);
		 btnstarthrinc = (Button) dialog.findViewById(R.id.btnshinc);
		 btnstarthrdec = (Button) dialog.findViewById(R.id.btnshdec);
		 btnstartmninc = (Button) dialog.findViewById(R.id.btnpoolupcelsius);
		 btnstartmndec = (Button) dialog.findViewById(R.id.btnpooldowncelsius);
		 
		 txtsthr = (TextView) dialog.findViewById(R.id.txtsh);
		 txtstmn = (TextView) dialog.findViewById(R.id.txtpoolcelsius);
		 txtstampm = (TextView) dialog.findViewById(R.id.txtsampm);
		 
		 

		 btnstopam = (Button) dialog.findViewById(R.id.btnspam);
		 btnstoppm = (Button) dialog.findViewById(R.id.btnsppm);
		 btnstophrinc = (Button) dialog.findViewById(R.id.btnspaupcelsius);
		 btnstophrdec = (Button) dialog.findViewById(R.id.btnspadowncelsius);
		 btnstopmninc = (Button) dialog.findViewById(R.id.btnspminc);
		 btnstopmndec = (Button) dialog.findViewById(R.id.btnspmdec);
		 
		 txtstophr = (TextView) dialog.findViewById(R.id.txtspacelsius);
		 txtstopmn = (TextView) dialog.findViewById(R.id.txtspm);
		 txtstopampm = (TextView) dialog.findViewById(R.id.txtspampm);	
		 
		 txtsthr.setText("08");
		 txtstmn.setText("00");
		 txtstampm.setText("AM");
		 
		 txtstophr.setText("06");
		 txtstopmn.setText("00");
		 txtstopampm.setText("PM");
		 
		 
			final CheckedTextView sun = (CheckedTextView) dialog.findViewById(R.id.checksolar);
			final CheckedTextView mon = (CheckedTextView) dialog.findViewById(R.id.checkgas);
			final CheckedTextView tue = (CheckedTextView) dialog.findViewById(R.id.checktue);
			final CheckedTextView wed = (CheckedTextView) dialog.findViewById(R.id.checkswed);
			final CheckedTextView thu = (CheckedTextView) dialog.findViewById(R.id.checkthu);
			final CheckedTextView fri = (CheckedTextView) dialog.findViewById(R.id.checkheat);
			final CheckedTextView sat = (CheckedTextView) dialog.findViewById(R.id.checksat);
		 
		 
		 if(isUpdate)
		 {
			 spntimerfun.setSelection(timerfunctionlist.indexOf(tm.getTimerName().toString()));
			
			 txttitle.setText("Edit Timer");
			 
			 String time = tm.getTimerTime();
			 String days = tm.getTimerDays();
			 
			 //Log.d("Times : ", time);
			 
			 txtsthr.setText(time.subSequence(0, 2));
			 txtstmn.setText(time.subSequence(3, 5));
			 txtstampm.setText(time.subSequence(7, 9));
			 
			 txtstophr.setText(time.subSequence(11, 14));
			 txtstopmn.setText(time.subSequence(15, 17));
			 txtstopampm.setText(time.subSequence(19, 21));
			 
			 
			 
			 if(days.equals(getResources().getString(R.string.Everyday).toString()))
			 {
				btneveryday.setText(getResources().getString(R.string.Everyday));
				sun.setChecked(true);
				sun.setTextColor(getResources().getColor(R.color.check));
				mon.setChecked(true);
				mon.setTextColor(getResources().getColor(R.color.check));
				tue.setChecked(true);
				tue.setTextColor(getResources().getColor(R.color.check));
				wed.setChecked(true);
				wed.setTextColor(getResources().getColor(R.color.check));
				thu.setChecked(true);
				thu.setTextColor(getResources().getColor(R.color.check));
				fri.setChecked(true);
				fri.setTextColor(getResources().getColor(R.color.check));
				sat.setChecked(true);
				sat.setTextColor(getResources().getColor(R.color.check));
				TimerActivity.checkevery = 2;
			 }
			 else if(days.indexOf("Sat") > -1 &&  days.indexOf("Sat") < 3)
			 {
				 	btneveryday.setText(getResources().getString(R.string.Weekends));
					sun.setChecked(false);
					sun.setTextColor(getResources().getColor(R.color.gray));
					mon.setChecked(false);
					mon.setTextColor(getResources().getColor(R.color.gray));
					tue.setChecked(false);
					tue.setTextColor(getResources().getColor(R.color.gray));
					wed.setChecked(false);
					wed.setTextColor(getResources().getColor(R.color.gray));
					thu.setChecked(false);
					thu.setTextColor(getResources().getColor(R.color.gray));
					fri.setChecked(false);
					fri.setTextColor(getResources().getColor(R.color.gray));
					sat.setChecked(true);
					sat.setTextColor(getResources().getColor(R.color.check));
					TimerActivity.checkevery = 3;
			 }
			 else
			 {
				 btneveryday.setText(getResources().getString(R.string.Weekdays));
				 TimerActivity.checkevery = 1;
				 if(days.indexOf("Sun") > -1)
				 {
					 	sun.setChecked(true);
						sun.setTextColor(getResources().getColor(R.color.check));
				 }
				 else
				 {
						sun.setChecked(false);
						sun.setTextColor(getResources().getColor(R.color.gray));
				 }
				 
				 
				 if(days.indexOf("Mon") > -1)
				 {
					 	mon.setChecked(true);
						mon.setTextColor(getResources().getColor(R.color.check));
				 }
				 else
				 {
					 mon.setChecked(false);
					 mon.setTextColor(getResources().getColor(R.color.gray));
				 }
				 
				 if(days.indexOf("Tue") > -1)
				 {
					 	tue.setChecked(true);
					 	tue.setTextColor(getResources().getColor(R.color.check));
				 }
				 else
				 {
					 tue.setChecked(false);
					 tue.setTextColor(getResources().getColor(R.color.gray));
				 }
				 
				 if(days.indexOf("Wed") > -1)
				 {
					 	wed.setChecked(true);
					 	wed.setTextColor(getResources().getColor(R.color.check));
				 }
				 else
				 {
					 wed.setChecked(false);
					 wed.setTextColor(getResources().getColor(R.color.gray));
				 }
				 
				 if(days.indexOf("Thu") > -1)
				 {
					 	thu.setChecked(true);
					 	thu.setTextColor(getResources().getColor(R.color.check));
				 }
				 else
				 {
					 thu.setChecked(false);
					 thu.setTextColor(getResources().getColor(R.color.gray));
				 }
				 
				 if(days.indexOf("Fri") > -1)
				 {
					 	fri.setChecked(true);
					 	fri.setTextColor(getResources().getColor(R.color.check));
				 }
				 else
				 {
					 fri.setChecked(false);
					 fri.setTextColor(getResources().getColor(R.color.gray));
				 }
				 
				 if(days.indexOf("Sat") > -1)
				 {
					 	sat.setChecked(true);
					 	sat.setTextColor(getResources().getColor(R.color.check));
				 }
				 else
				 {
					 sat.setChecked(false);
					 sat.setTextColor(getResources().getColor(R.color.gray));
				 }
				 
				 
				 
				 
			 }
			 
		 }
		 
		 
		
		 btnstarthrinc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
							
				
				int h = Integer.parseInt(txtsthr.getText().toString().trim());
				if(h<12)
				h = h + 1;
				else
				{
					h = 0;
				}
				if(h<10)
				txtsthr.setText("0"+h+"");
				else
				txtsthr.setText(h+"");
				
				
			}
		});
		
		 btnstarthrdec.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
								
					
					int h = Integer.parseInt(txtsthr.getText().toString().trim());
					if(h>0)
					h = h - 1;
					else
					{
						h = 12;
					}
					
						if(h<10)
						txtsthr.setText("0"+h+"");
						else
						txtsthr.setText(h+"");
					
					
					
				}
			});
		
		 
		 btnstartmninc.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
								
					
					int m = Integer.parseInt(txtstmn.getText().toString().trim());
					if(m<59)
					m = m + 1;
					else
					{
						m = 0;
					}
					
					if(m<10)
						txtstmn.setText("0"+m);
						else
						txtstmn.setText(m+"");
					
					
					
				}
			});
		 
		 
		 btnstartmndec.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
								
					
					int m = Integer.parseInt(txtstmn.getText().toString().trim());
					if(m>0)
					m = m - 1;
					else
					{
						m = 59;
					}
					if(m<10)
						txtstmn.setText("0"+m);
						else
						txtstmn.setText(m+"");
					
				}
			});
		 
		 
		 btnstartam.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
								
					txtstampm.setText("AM");
					
					
				}
			});
		 
		 btnstartpm.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
								
					txtstampm.setText("PM");
					
					
				}
			});
		 
		 
		 btnstophrinc.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
								
					
					int h = Integer.parseInt(txtstophr.getText().toString().trim());
					if(h<12)
					h = h + 1;
					else
					{
						h = 0;
					}
					if(h<10)
						txtstophr.setText("0"+h+"");
						else
						txtstophr.setText(h+"");
					
					
				}
			});
			
			 btnstophrdec.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
									
						
						int h = Integer.parseInt(txtstophr.getText().toString().trim());
						if(h>0)
						h = h - 1;
						else
						{
							h = 12;
						}
						txtstophr.setText(h+"");
						if(h<10)
							txtstophr.setText("0"+h+"");
							else
							txtstophr.setText(h+"");
						
					}
				});
			
			 
			 btnstopmninc.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
									
						
						int m = Integer.parseInt(txtstopmn.getText().toString().trim());
						if(m<59)
						m = m + 1;
						else
						{
							m = 0;
						}
						txtstopmn.setText(m+"");
						if(m<10)
							txtstopmn.setText("0"+m+"");
							else
								txtstopmn.setText(m+"");
						
					}
				});
			 
			 
			 btnstopmndec.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
									
						
						int m = Integer.parseInt(txtstopmn.getText().toString().trim());
						if(m>0)
						m = m - 1;
						else
						{
							m = 59;
						}
						txtstopmn.setText(m+"");
						if(m<10)
							txtstopmn.setText("0"+m+"");
							else
								txtstopmn.setText(m+"");
						
					}
				});
			 
			 
			 btnstopam.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
									
						txtstopampm.setText("AM");
						
						
					}
				});
			 
			 btnstoppm.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
									
						txtstopampm.setText("PM");
						
						
					}
				});
		 
			 
			
		 
	
		
		sun.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					ischeck(sun);		
			}
		});
		
		mon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					ischeck(mon);		
			}
		});
		tue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					ischeck(tue);		
			}
		});
		wed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					ischeck(wed);		
			}
		});
		thu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					ischeck(thu);		
			}
		});
		fri.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					ischeck(fri);		
			}
		});
		sat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					ischeck(sat);		
			}
		});
		
		
		
		 btneveryday.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					if(TimerActivity.checkevery == 1)
					{
						btneveryday.setText(getResources().getString(R.string.Everyday));
					sun.setChecked(true);
					sun.setTextColor(getResources().getColor(R.color.check));
					mon.setChecked(true);
					mon.setTextColor(getResources().getColor(R.color.check));
					tue.setChecked(true);
					tue.setTextColor(getResources().getColor(R.color.check));
					wed.setChecked(true);
					wed.setTextColor(getResources().getColor(R.color.check));
					thu.setChecked(true);
					thu.setTextColor(getResources().getColor(R.color.check));
					fri.setChecked(true);
					fri.setTextColor(getResources().getColor(R.color.check));
					sat.setChecked(true);
					sat.setTextColor(getResources().getColor(R.color.check));
					TimerActivity.checkevery = 2;
					}
					else if(TimerActivity.checkevery == 2)
					{
						btneveryday.setText(getResources().getString(R.string.Weekends));
						sun.setChecked(false);
						sun.setTextColor(getResources().getColor(R.color.gray));
						mon.setChecked(false);
						mon.setTextColor(getResources().getColor(R.color.gray));
						tue.setChecked(false);
						tue.setTextColor(getResources().getColor(R.color.gray));
						wed.setChecked(false);
						wed.setTextColor(getResources().getColor(R.color.gray));
						thu.setChecked(false);
						thu.setTextColor(getResources().getColor(R.color.gray));
						fri.setChecked(false);
						fri.setTextColor(getResources().getColor(R.color.gray));
						sat.setChecked(true);
						sat.setTextColor(getResources().getColor(R.color.check));
						TimerActivity.checkevery = 3;
					}					
					else 
					{
						btneveryday.setText(getResources().getString(R.string.Weekdays));
						sun.setChecked(false);
						sun.setTextColor(getResources().getColor(R.color.gray));
						mon.setChecked(false);
						mon.setTextColor(getResources().getColor(R.color.gray));
						tue.setChecked(false);
						tue.setTextColor(getResources().getColor(R.color.gray));
						wed.setChecked(false);
						wed.setTextColor(getResources().getColor(R.color.gray));
						thu.setChecked(false);
						thu.setTextColor(getResources().getColor(R.color.gray));
						fri.setChecked(false);
						fri.setTextColor(getResources().getColor(R.color.gray));
						sat.setChecked(false);
						sat.setTextColor(getResources().getColor(R.color.gray));
						TimerActivity.checkevery = 1;
					}
					
				}
			});
		
		
		 
		 
		
		Button btncancel = (Button) dialog.findViewById(R.id.btntimercancel);
		
		btncancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				dialog.cancel();
				
			}
		});
		
		
		btnsavetimer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				String days = "";
				int countdays = 0;
				boolean onepress = false;
				
				if(sun.isChecked())
				{
					days = "Sun, ";
					onepress = true;
					countdays = countdays + 1;
				}
				if(mon.isChecked())
				{
					days = days + "Mon, ";
					onepress = true;
					countdays = countdays + 1;
				}
				if(tue.isChecked())
				{
					days = days + "Tue, ";
					onepress = true;
					countdays = countdays + 1;
				}
				if(wed.isChecked())
				{
					days = days + "Wed, ";
					onepress = true;
					countdays = countdays + 1;
				}
				if(thu.isChecked())
				{
					days = days + "Thu, ";
					onepress = true;
					countdays = countdays + 1;
				}
				if(fri.isChecked())
				{
					days = days + "Fri, ";
					onepress = true;
					countdays = countdays + 1;
				}
				if(sat.isChecked())
				{
					days = days + "Sat, ";
					onepress = true;
					countdays = countdays + 1;
				}
				
				
				
				
				if(countdays == 7)
				{
					days = "Everyday";
				}
				else if(onepress)				
					days = days.trim().substring(0,days.length()-2);
				
				if(days.equals(""))
				{
				
				}
				else
				{
					
					if(isUpdate)
					{
						editTimmer(tm.getTimerId() ,spntimerfun.getSelectedItem().toString() , txtsthr.getText()+":"+txtstmn.getText()+"  "+txtstampm.getText(),  txtstophr.getText()+":"+txtstopmn.getText()+"  "+txtstopampm.getText(), days, true);
					}
					else
					{
						addTimer(spntimerfun.getSelectedItem().toString() , txtsthr.getText()+":"+txtstmn.getText()+"  "+txtstampm.getText(),  txtstophr.getText()+":"+txtstopmn.getText()+"  "+txtstopampm.getText(), days, true);
					}
					dialog.dismiss();
				}
			}	
			});
		
		
				
		dialog.show();
		 
	}

	
	
	public void addTimer(String timername, String timerstarttime, String timerendtime, String timerdays, boolean timeractive )
	{
		//TimerModel tm = new TimerModel(timername, timertime, timerdays, timeractive);
		//CustomListViewValuesArr.add( tm );
		//adapter.notifyDataSetChanged();
		
		int active = 0;
		if(timeractive)
			active = 1;
		
		db.execSQL("INSERT INTO timmer (timername, timerstarttime, timerendtime, timerdays, timeractive) VALUES ('"+timername+"','"+timerstarttime+"','"+timerendtime+"', '"+timerdays+"' ,"+active+")");
		
		loadList();
		
	}


	public void editTimmer(int id, String timername, String timerstarttime, String timerendtime, String timerdays, boolean timeractive )
	{
		//TimerModel tm = new TimerModel(timername, timertime, timerdays, timeractive);
		//CustomListViewValuesArr.add( tm );
		//adapter.notifyDataSetChanged();
		
		int active = 0;
		if(timeractive)
			active = 1;
		
		db.execSQL("UPDATE timmer SET timername = '"+timername+"',  timerstarttime = '"+timerstarttime+"', timerendtime = '"+timerendtime.trim()+"', timerdays = '"+timerdays+"', timeractive = "+active+"  where timerid = "+id+"");
		
		loadList();
		
	}
	
	

}

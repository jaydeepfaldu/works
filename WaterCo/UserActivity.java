package com.example.waterco;

import com.myutility.*;

import java.io.File;
import java.security.KeyStore;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.bumptech.glide.Glide;

import com.myutility.KeywordStore;
import com.transmittion.*;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.*;

public class UserActivity extends Activity implements OnTouchListener, MqttCallback {
	
	
	//UDPClient udpClient;
		
	private float x1,x2;
	static final int MIN_DISTANCE = 150;
	 
	ImageView imgcmp;
	TextView  txtcmp;
	
	
	ImageView imgfpb;
	TextView  txtfpbmode;
	TextView  txtfpbopt;
	
	
	ImageView imgleb;
	TextView txtleb;
	
	
	ImageView imghelp;
	
	
	TextView txtslidCound;
	
	
	ImageView imgprog1, imgprog2, imgprog3, imgprog4, imgprog5, imgprog6, imgprog7, imgprog8, imgprog9, imgprog10, imgprog11, imgprog12, imgprog13, imgprog14, imgprog15, imgprog16, imgprog17, imgprog18, imgprog19, imgprog20, imgprog21, imgprog22, imgprog23, imgprog24 ;
	
	
	
	

	ImageView imgSetting, imgTimmer, imgheater;
	
	TextView  txtdate, txtvaluepooltmp, txtlabelpooltmp, txtvaluespatmp, txtlabelspatmp, txtvaluesolartmp, txtlabelsolartmp;	
	
	TextView txtpoolspamode, txtfiltermode, txtfilteroption, txtheatermode, txtlightmode, txttimer, txtsetting;
 
	SQLiteDatabase db; 
	
	int count = 1;
	
	//Transceive tr;
	
	TextView txtmessage;
	
	 LinearLayout ll;
	
	 MqttClient client;
	 AnimationDrawable animationDrawable_filter,  animationDrawable_light;
	 
	 
	 ViewFlipper programslide;
	 
	// int update_counter = 1;
	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userscreen);
	
		//tr = new Transceive();

		//udpClient = new UDPClient();
		
		txtslidCound = (TextView) findViewById(R.id.txtslidecount);
		
		txtslidCound.setText(KeywordStore.SLIDECOUNT + "");
		
		imghelp = (ImageView)findViewById(R.id.imghelp);
		
		programslide = (ViewFlipper)findViewById(R.id.slide1);
		
		imgSetting = (ImageView) findViewById(R.id.imgseting);
		
		imgheater = (ImageView) findViewById(R.id.imgheater);
	
		
		imgTimmer = (ImageView) findViewById(R.id.imgalert);
		
		db = this.openOrCreateDatabase("WATERCO.DB",MODE_PRIVATE,null);
		
		
		txtmessage = (TextView) findViewById(R.id.txtmessage);
		
		
		//txttime = (TextView) findViewById(R.id.txttime);
		txtdate = (TextView) findViewById(R.id.txtdate);
		txtvaluepooltmp = (TextView) findViewById(R.id.txtvaluepooltmp);
		txtlabelpooltmp = (TextView) findViewById(R.id.txtlabelpooltmp);
		txtvaluespatmp = (TextView) findViewById(R.id.txtvaluespatmp);
		txtlabelspatmp = (TextView) findViewById(R.id.txtlabelspatmp);
		txtvaluesolartmp = (TextView) findViewById(R.id.txtvaluesolartmp);
		txtlabelsolartmp = (TextView) findViewById(R.id.txtlabelsolartmp);
		
		
		
		txtpoolspamode = (TextView) findViewById(R.id.txtpoolspamode);
		txtfiltermode = (TextView) findViewById(R.id.txtfiltermode);
		txtfilteroption = (TextView) findViewById(R.id.txtfilteroption);
		txtheatermode = (TextView) findViewById(R.id.txtheatermode);		
		txtlightmode = (TextView) findViewById(R.id.txtlightmode);
		txttimer = (TextView) findViewById(R.id.txttimer);
		txtsetting = (TextView) findViewById(R.id.txtsetting);
		
		
		
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
	
		DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
		String date = df.format(Calendar.getInstance().getTime());
		
		txtdate.setText(date);
		
		
		txtvaluepooltmp.setText( "28.5 " + (char) 0x00B0 + "C");
		txtvaluespatmp.setText( "23.4 " + (char) 0x00B0 + "C");
		txtvaluesolartmp.setText("36.2 " + (char) 0x00B0 + "C");
		
		imgSetting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				showUserDialog();
					
						
				
			}
		});
		
		txtmessage.setSelected(true);
		
		imgTimmer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(UserActivity.this, TimerActivity.class);
				startActivity(intent);
				
				
			}
		});
		
		
		
		Thread t = new Thread() {
			
			  @Override
			  public void run() {
			    try {
			      while (!isInterrupted()) {
			        Thread.sleep(10);
			        
			        runOnUiThread(new Runnable() {
			          @Override
			          public void run() {
			        	  
			        	if(!KeywordStore.EXECUTE_RUNNING){
			        		 new SenderTask().execute("CALL");
			        		 
			        		 //new ReceiverTask().execute("Call");
			        		 
			        		 try
			        		 {
			        			 if(KeywordStore.update)
			        			 {
			        				 	update_all();
			        				 	KeywordStore.update = false;
			        			 }
			        		 }
			        		 catch(Exception e)
			        		 {
			        			 
			        		 }
			        		// KeywordStore.receivemsg = "0";
			        		
			        		 
			        	}
			        	
			        	 txtmessage.setText(KeywordStore.receivemsg);
			        	
			        	 			        		  
			          }
			        });
			      }
			    } catch (InterruptedException e) {
			    	
			    	Log.e("ERROR IN THRAD", e.getMessage());
			    }
			  }
			};

			if(!KeywordStore.isInternet)
			{
				t.start();
			}
			else
			{
				try
				{
					MemoryPersistence persistance = new MemoryPersistence();
					
					
					client = new MqttClient( "tcp://"+KeywordStore.ServerIP+":"+KeywordStore.ServerPort,KeywordStore.ClientID, persistance);
										 						 
					    MqttConnectOptions options = new MqttConnectOptions();

					    options.setCleanSession(true);
										    
					    options.setUserName(KeywordStore.ServerUserName);
					    options.setPassword(KeywordStore.ServerUserPassword.toCharArray());
					    	
					    client.connect(options);
					    
					    client.subscribe(KeywordStore.ClientSubSctibe,0);		    
					    client.setCallback(UserActivity.this);
					    
				}
				catch(Exception e)
				{
				
					Log.e("EXCEPTION ", e.getMessage());
				}
				
				
			}
		
		
		
		
		imgcmp = (ImageView) findViewById(R.id.imgpoolspa);
		txtcmp = (TextView) findViewById(R.id.txtpoolspamode);
		
		
		
		
		imgcmp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//KeywordStore.command = "%%%a###";
				
			//	new SenderTask().execute("CALL");
				
				
				String currentStatus = Integer.toHexString(KeywordStore.CPM);								
				KeywordStore.command = "0x250x250x250x000x01"+incrementCounter()+KeywordStore.myip+"0x"+KeywordStore.PreReceiveCounter2+"0x000x000x000x000x01"+checkValueLength(currentStatus)+"0x230x230x23";		
				
				
			}
		});
		
		
		
		imghelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Uri uri = Uri.parse("http://www.waterco.com.au/downloads/Manuals/8290052_Aquamaster_Manual_14.08.14%20FA.pdf"); // missing 'http://' will cause crashed
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
				
			}
		});
		
		
		imgfpb = (ImageView) findViewById(R.id.imgfilter);		
		
		
		imgfpb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String currentStatus = Integer.toHexString(KeywordStore.FPM);								
				KeywordStore.command = "0x250x250x250x000x02"+incrementCounter()+KeywordStore.myip+"0x"+KeywordStore.PreReceiveCounter2+"0x000x000x000x000x01"+checkValueLength(currentStatus)+"0x230x230x23";			
								
				
			}
		});
		
		
		imgleb = (ImageView) findViewById(R.id.imglight);
		
		imgleb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				
				String currentStatus = Integer.toHexString(KeywordStore.LBE);
						
			//	KeywordStore.command = "0x250x250x250x000x04"+incrementCounter()+"0x280x010x010x230x230x23";
				if(!KeywordStore.isInternet)
				{
					//Toast.makeText(UserActivity.this,"Local mode", Toast.LENGTH_SHORT).show();
					KeywordStore.command = "0x250x250x250x000x04"+incrementCounter()+KeywordStore.myip+"0x"+KeywordStore.PreReceiveCounter2+"0x000x000x000x000x01"+checkValueLength(currentStatus)+"0x230x230x23";
				}
				else
				{
				Toast.makeText(UserActivity.this,"Internet mode", Toast.LENGTH_SHORT).show();
				}
				//25 25 25 00 04 XX 28 01 01 23 23 23
				
				//Log.d("COMMAND : ", KeywordStore.command);
				
			}
		});
		
		
		
		
		imgprog1 = (ImageView) findViewById(R.id.imgpro1);
		imgprog2 = (ImageView) findViewById(R.id.imgpro2);
		imgprog3 = (ImageView) findViewById(R.id.imgpro3);
		imgprog4 = (ImageView) findViewById(R.id.imgpro4);
		imgprog5 = (ImageView) findViewById(R.id.imgpro5);
		imgprog6 = (ImageView) findViewById(R.id.imgpro6);
		imgprog7 = (ImageView) findViewById(R.id.imgpro7);
		imgprog8 = (ImageView) findViewById(R.id.imgpro8);
		imgprog9 = (ImageView) findViewById(R.id.imgpro9);
		imgprog10 = (ImageView) findViewById(R.id.imgpro10);
		imgprog11 = (ImageView) findViewById(R.id.imgpro11);
		imgprog12 = (ImageView) findViewById(R.id.imgpro12);
		imgprog13 = (ImageView) findViewById(R.id.imgpro13);
		imgprog14 = (ImageView) findViewById(R.id.imgpro14);
		imgprog15 = (ImageView) findViewById(R.id.imgpr15);
		imgprog16 = (ImageView) findViewById(R.id.imgpro16);
		imgprog17 = (ImageView) findViewById(R.id.imgpro17);
		imgprog18 = (ImageView) findViewById(R.id.imgpro18);
		imgprog19 = (ImageView) findViewById(R.id.imgpro19);
		imgprog20 = (ImageView) findViewById(R.id.imgpro20);
		imgprog21 = (ImageView) findViewById(R.id.imgpro21);
		imgprog22 = (ImageView) findViewById(R.id.imgpro22);
		imgprog23 = (ImageView) findViewById(R.id.imgpr23);
		imgprog24 = (ImageView) findViewById(R.id.imgpro24);
		
		
		
		
		
		imgprog1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				
				/*try
				{
					Thread.sleep(2000);
				}
				catch(Exception e)
				{
					
				}
				*/
				//if(KeywordStore.status == 200)
				//{
				
				
				 if(KeywordStore.PROG1 == 0)
				 {
					 KeywordStore.command = "%%%b1=1###";
						//new SenderTask().execute("CALL"); 
				 }
				 else if(KeywordStore.PROG1 == 1)
				 {
					 KeywordStore.command = "%%%b1=2###";
						//new SenderTask().execute("CALL");
				 }
				
					KeywordStore.PROG1 = setProgStates(imgprog1, KeywordStore.PROG1);
					
					
					
					//KeywordStore.status = 0;
				//}
				//else
				//{					
					//Toast.makeText(UserActivity.this,"NO UPDATE", Toast.LENGTH_LONG).show();					
				//}
				
				
				
			}
		});
		
		
		imgprog2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
						
				
				if(KeywordStore.PROG2 == 0)
				 {
					 KeywordStore.command = "%%%b2=1###";
						//new SenderTask().execute("CALL"); 
				 }
				 else if(KeywordStore.PROG2 == 1)
				 {
					 KeywordStore.command = "%%%b2=2###";
						//new SenderTask().execute("CALL");
				 }
				
				KeywordStore.PROG2 = setProgStates(imgprog2, KeywordStore.PROG2);
				
			}
		});
		
		
		
		imgprog3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
						
				if(KeywordStore.PROG3 == 0)
				 {
					 KeywordStore.command = "%%%b3=1###";
						//new SenderTask().execute("CALL"); 
				 }
				 else if(KeywordStore.PROG3 == 1)
				 {
					 KeywordStore.command = "%%%b3=2###";
						//new SenderTask().execute("CALL");
				 }
				
				KeywordStore.PROG3 = setProgStates(imgprog3, KeywordStore.PROG3);
				
			}
		});
		
		
		imgprog4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(KeywordStore.PROG4 == 0)
				 {
					 KeywordStore.command = "%%%b4=1###";
						//new SenderTask().execute("CALL"); 
				 }
				 else if(KeywordStore.PROG4 == 1)
				 {
					 KeywordStore.command = "%%%b4=2###";
						//new SenderTask().execute("CALL");
				 }
				
				KeywordStore.PROG4 = setProgStates(imgprog4, KeywordStore.PROG4);
				
			}
		});
		
		
		imgprog5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
						
				if(KeywordStore.PROG5 == 0)
				 {
					 KeywordStore.command = "%%%b5=1###";
						//new SenderTask().execute("CALL"); 
				 }
				 else if(KeywordStore.PROG5 == 1)
				 {
					 KeywordStore.command = "%%%b5=2###";
						//new SenderTask().execute("CALL");
				 }
				
				KeywordStore.PROG5 = setProgStates(imgprog5, KeywordStore.PROG5);
				
			}
		});
		
		
		imgprog6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
						
				if(KeywordStore.PROG6 == 0)
				 {
					 KeywordStore.command = "%%%b6=1###";
						//new SenderTask().execute("CALL"); 
				 }
				 else if(KeywordStore.PROG6 == 1)
				 {
					 KeywordStore.command = "%%%b6=2###";
						//new SenderTask().execute("CALL");
				 }
				
				KeywordStore.PROG6 = setProgStates(imgprog6, KeywordStore.PROG6);
				
			}
		});
		
		imgprog7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(KeywordStore.PROG7 == 0)
				 {
					 KeywordStore.command = "%%%b7=1###";
						//new SenderTask().execute("CALL"); 
				 }
				 else if(KeywordStore.PROG7 == 1)
				 {
					 KeywordStore.command = "%%%b7=2###";
						//new SenderTask().execute("CALL");
				 }
				
				KeywordStore.PROG7 = setProgStates(imgprog7, KeywordStore.PROG7);
				
			}
		});
		
		
		imgprog8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(KeywordStore.PROG8 == 0)
				 {
					 KeywordStore.command = "%%%b8=1###";
						//new SenderTask().execute("CALL"); 
				 }
				 else if(KeywordStore.PROG8 == 1)
				 {
					 KeywordStore.command = "%%%b8=2###";
						//new SenderTask().execute("CALL");
				 }
				
				
				
				KeywordStore.PROG8 = setProgStates(imgprog8, KeywordStore.PROG8);
				
			}
		});
		
		imgprog9.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
						
				KeywordStore.PROG9 = setProgStates(imgprog9, KeywordStore.PROG9);
				
			}
		});
		
		
		imgprog10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
						
				KeywordStore.PROG10 = setProgStates(imgprog10, KeywordStore.PROG10);
				
			}
		});
		
		
		imgprog11.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
						
				KeywordStore.PROG11 = setProgStates(imgprog11, KeywordStore.PROG11);
				
			}
		});
		
		imgprog12.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG12 = setProgStates(imgprog12, KeywordStore.PROG12);
		
	}
});

		imgprog13.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG13 = setProgStates(imgprog13, KeywordStore.PROG13);
		
	}
});

imgprog14.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG14 = setProgStates(imgprog14, KeywordStore.PROG14);
		
	}
});

imgprog15.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG15 = setProgStates(imgprog15, KeywordStore.PROG15);
		
	}
});

imgprog16.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG16 = setProgStates(imgprog16, KeywordStore.PROG16);
		
	}
});

imgprog17.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG17 = setProgStates(imgprog17, KeywordStore.PROG17);
		
	}
});

imgprog18.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG18 = setProgStates(imgprog18, KeywordStore.PROG18);
		
	}
});

imgprog19.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG19 = setProgStates(imgprog19, KeywordStore.PROG19);
		
	}
});
imgprog20.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG20 = setProgStates(imgprog20, KeywordStore.PROG20);
		
	}
});
imgprog21.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG21 = setProgStates(imgprog21, KeywordStore.PROG21);
		
	}
});

imgprog22.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG22 = setProgStates(imgprog22, KeywordStore.PROG22);
		
	}
});
imgprog23.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG23 = setProgStates(imgprog23, KeywordStore.PROG23);
		
	}
});
imgprog24.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
				
		KeywordStore.PROG24 = setProgStates(imgprog24, KeywordStore.PROG24);
		
	}
});

programslide.setOnTouchListener(this);


	imgheater.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		
			Button btncancel, btnpooltmpup, btnpooltmpdown, btnspatmpup, btnspatmpdown;
			final CheckedTextView checksolar, checkgas, checkheat;
			final TextView txtpoolcelsius, txtspacelsius;
			
			
			
			final Dialog dialog = new Dialog(UserActivity.this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);		
		    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			dialog.setContentView(R.layout.heatinglayout);
			dialog.setCancelable(false);
			
			
				btncancel = (Button) dialog.findViewById(R.id.btncancel);
				btnpooltmpup = (Button) dialog.findViewById(R.id.btnpoolupcelsius);
				btnpooltmpdown = (Button) dialog.findViewById(R.id.btnpooldowncelsius);
				btnspatmpup = (Button) dialog.findViewById(R.id.btnspaupcelsius);
				btnspatmpdown = (Button) dialog.findViewById(R.id.btnspadowncelsius);
				
				
				checksolar = (CheckedTextView) dialog.findViewById(R.id.checksolar) ;
				checkgas = (CheckedTextView) dialog.findViewById(R.id.checkgas) ;
				checkheat = (CheckedTextView) dialog.findViewById(R.id.checkheat) ;
			
				
				txtpoolcelsius = (TextView) dialog.findViewById(R.id.txtpoolcelsius);
				txtspacelsius = (TextView) dialog.findViewById(R.id.txtspacelsius);
			
				txtpoolcelsius.setText(KeywordStore.poolcelsius +"");
				txtspacelsius.setText(KeywordStore.spacelsius +"");
			
				btnpooltmpup.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
				
						
						KeywordStore.poolcelsius = KeywordStore.poolcelsius + 1;
						txtpoolcelsius.setText(KeywordStore.poolcelsius+"");					
											
					}
				});
				
				btnpooltmpdown.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
				
						KeywordStore.poolcelsius = KeywordStore.poolcelsius - 1;
						txtpoolcelsius.setText(KeywordStore.poolcelsius+"");					
											
					}
				});
				
				
				
				btnspatmpup.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
				
						KeywordStore.spacelsius = KeywordStore.spacelsius + 1;
						txtspacelsius.setText(KeywordStore.spacelsius+"");					
											
					}
				});
				
				btnspatmpdown.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						KeywordStore.spacelsius = KeywordStore.spacelsius - 1;
						txtspacelsius.setText(KeywordStore.spacelsius+"");					
											
					}
				});
				
			checksolar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
			
					if(checksolar.isChecked())
					{

						checksolar.setChecked(false);
						checksolar.setTextColor( getResources().getColor(R.color.gray));
						checksolar.setText(getResources().getString(R.string.solardis));
					}
						
					else
					{
						

							checksolar.setChecked(true);
							checksolar.setTextColor( getResources().getColor(R.color.check));
							checksolar.setText(getResources().getString(R.string.solarenb));

					}
				}
			});
			
			checkgas.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
			
					if(checkgas.isChecked())
					{

						checkgas.setChecked(false);
						checkgas.setTextColor( getResources().getColor(R.color.gray));
						checkgas.setText(getResources().getString(R.string.gasheatdis));
					}
						
					else
					{
						

						checkgas.setChecked(true);
						checkgas.setTextColor( getResources().getColor(R.color.check));
						checkgas.setText(getResources().getString(R.string.gasheatenb));

					}				
										
				}
			});
			checkheat.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
			
					if(checkheat.isChecked())
					{

						checkheat.setChecked(false);
						checkheat.setTextColor( getResources().getColor(R.color.gray));
						checkheat.setText(getResources().getString(R.string.heatpumpdis));
					}
						
					else
					{
						

						checkheat.setChecked(true);
						checkheat.setTextColor( getResources().getColor(R.color.check));
						checkheat.setText(getResources().getString(R.string.heatpumpenb));

					}						
										
				}
			});
			
			
			btncancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					dialog.dismiss();
					
				}
			});
			
			dialog.show();
		}
	});

	
	
	
	
	}
		
	
	public void update_all()
	{
		
		
		String response = "0"; 
				
		response = KeywordStore.receivemsg;
		
		if(response.length() > 5)	
		{
			
			KeywordStore.receivemsg = "0";
			
		if(response.charAt(6) == '0' && response.charAt(7) == '0' && response.charAt(8) == '0' && response.charAt(9) == '4')
		{
			String msg = response.charAt(response.length()-2) +""+ response.charAt(response.length()-1);
			
						
			if(msg.equals("00"))
			{
				KeywordStore.LBE = 0;
			}
			else if(msg.equals("01"))
			{
				KeywordStore.LBE = 1;
			}
			else if(msg.equals("02"))
			{
				KeywordStore.LBE = 2;
			}
			
		}
		
		
		else if(response.charAt(6) == '0' && response.charAt(7) == '0' && response.charAt(8) == '0' && response.charAt(9) == '1')
		{
			String msg = response.charAt(response.length()-2) +""+ response.charAt(response.length()-1);
			
						
			if(msg.equals("00"))
			{
				KeywordStore.CPM = 0;
			}
			else if(msg.equals("01"))
			{
				KeywordStore.CPM = 1;
			}
			else if(msg.equals("02"))
			{
				KeywordStore.CPM = 2;
			}
			else if(msg.equals("03"))
			{
				KeywordStore.CPM = 3;
			}
			
		}
		
		
		else if(response.charAt(6) == '0' && response.charAt(7) == '0' && response.charAt(8) == '0' && response.charAt(9) == '2')
		{
			
			String msg = response.charAt(response.length()-2) +""+ response.charAt(response.length()-1);
			
			
			if(msg.equals("00"))
			{
				KeywordStore.FPM = 0;
			}
			else if(msg.equals("01"))
			{
				KeywordStore.FPM = 1;
			}
			else if(msg.equals("02"))
			{
				KeywordStore.FPM = 2;
			}
			else if(msg.equals("03"))
			{
				KeywordStore.FPM = 3;
			}
			else if(msg.equals("04"))
			{
				KeywordStore.FPM = 4;
			}
			else if(msg.equals("05"))
			{
				KeywordStore.FPM = 5;
			}
		}
		
		// Light Control //
		
		if(KeywordStore.LBE == 1)
		{
			imgleb.setImageResource(R.drawable.lightoff);					
			txtlightmode.setText(R.string.lightoff);
			animationDrawable_light.stop();


			//KeywordStore.LBE = 1;
		}
		else if(KeywordStore.LBE == 2)
		{
			String uri = "@drawable/light_on_image_change"; 
			int imageResource = getResources().getIdentifier(uri, null, getPackageName());

			Drawable res = getResources().getDrawable(imageResource);
			imgleb.setImageDrawable(res);
			
			animationDrawable_light = (AnimationDrawable) imgleb.getDrawable();
			animationDrawable_light.setOneShot(false);
			animationDrawable_light.start();
		    
		    txtlightmode.setText(R.string.lighton);
		    
			
			//KeywordStore.LBE = 0;
		}
		
		
		// Filter Pump Control //

			
		
		if(KeywordStore.FPM == 0)
		{
			String uri = "@drawable/filter_on_image_change"; 
			int imageResource = getResources().getIdentifier(uri, null, getPackageName());

			Drawable res = getResources().getDrawable(imageResource);
			imgfpb.setImageDrawable(res);
			
			animationDrawable_filter = (AnimationDrawable) imgfpb.getDrawable();
			animationDrawable_filter.setOneShot(false);
			animationDrawable_filter.start();
		    
		    txtfilteroption.setText(R.string.filteron);
		    
		    
		    //KeywordStore.FPM = 1;
		}
		else if(KeywordStore.FPM == 1)
		{							
			
			String uri = "@drawable/filter_on_image_change"; 
			int imageResource = getResources().getIdentifier(uri, null, getPackageName());

			Drawable res = getResources().getDrawable(imageResource);
			imgfpb.setImageDrawable(res);
			
			animationDrawable_filter = (AnimationDrawable) imgfpb.getDrawable();
			animationDrawable_filter.setOneShot(false);
			animationDrawable_filter.start();

			
			
			txtfilteroption.setText(R.string.filterlow);					
			
			//KeywordStore.FPM = 2;
		}
		else if(KeywordStore.FPM == 2)
		{
			
			String uri = "@drawable/filter_on_image_change"; 
			int imageResource = getResources().getIdentifier(uri, null, getPackageName());

			Drawable res = getResources().getDrawable(imageResource);
			imgfpb.setImageDrawable(res);
			
			animationDrawable_filter = (AnimationDrawable) imgfpb.getDrawable();
			animationDrawable_filter.setOneShot(false);
			animationDrawable_filter.start();

			
			txtfilteroption.setText(R.string.filtermedium);					
			
			//KeywordStore.FPM = 3;
		}
		else if(KeywordStore.FPM == 3)
		{
			
			String uri = "@drawable/filter_on_image_change"; 
			int imageResource = getResources().getIdentifier(uri, null, getPackageName());

			Drawable res = getResources().getDrawable(imageResource);
			imgfpb.setImageDrawable(res);
			
			animationDrawable_filter = (AnimationDrawable) imgfpb.getDrawable();
			animationDrawable_filter.setOneShot(false);
			animationDrawable_filter.start();

			
			txtfilteroption.setText(R.string.filterhigh);					
			
			//KeywordStore.FPM = 4;
		}
		else if(KeywordStore.FPM == 4)
		{
			imgfpb.setImageResource(R.drawable.filterheat);					
			txtfilteroption.setText(R.string.filterheater);
			animationDrawable_filter.stop();
			
			//KeywordStore.FPM = 5;
		}
		else if(KeywordStore.FPM == 5)
		{
								
			imgfpb.setImageResource(R.drawable.filteroff);					
			txtfilteroption.setText(R.string.filteroff);
			
			//KeywordStore.FPM = 0;
		}
		
	
		// Pool Spa Control //
		

		if(KeywordStore.CPM == 0)
		{	
			
			
			//KeywordStore.CPM = 2;
		}
		else if(KeywordStore.CPM == 1)
		{	
			imgcmp.setBackgroundResource(R.drawable.spa);
			txtcmp.setText(R.string.spa);
			
			//KeywordStore.CPM = 2;
		}
		else if(KeywordStore.CPM == 2)
		{
			
			imgcmp.setBackgroundResource(R.drawable.pool_spa);
			txtcmp.setText(R.string.poolspa);
			
			//KeywordStore.CPM = 3;
		}
		else if(KeywordStore.CPM == 3)
		{
			imgcmp.setBackgroundResource(R.drawable.pool);
			txtcmp.setText(R.string.pool);
								
			//KeywordStore.CPM = 1;
		}

		
		
		}
		
	}
	
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	
		 if (keyCode == KeyEvent.KEYCODE_BACK ) {
		     			 
		        return false;
		    } 
		return super.onKeyDown(keyCode, event);
	}
	
	
	public String setCelsius(int val)
	{
		String vl = "";
		if(val<10)
		{
			vl = "0"+val;
		}
		else
		{
			vl = val+"";
		}
		return vl;
	}
	
	
	
	public void chechHeat(CheckedTextView ch)
	{
		if(ch.isChecked())
		{
			ch.setChecked(false);
			ch.setTextColor( getResources().getColor(R.color.gray));
			
		}
		else
		{
			ch.setChecked(true);
			ch.setTextColor( getResources().getColor(R.color.check));
			
		}
	}
	
	public void showUserDialog()
	{
		final Dialog dialog = new Dialog(UserActivity.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);		
	    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setContentView(R.layout.logindialog);
		dialog.setCancelable(false);
		
		final EditText userInput = (EditText) dialog.findViewById(R.id.etdpassword);
		Button btnlogin = (Button) dialog.findViewById(R.id.btnalertlogin);
		Button btncancel = (Button) dialog.findViewById(R.id.btnalertcancle);
		
		btncancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				dialog.cancel();
				userInput.setText("");
			}
		});
		
		btnlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				
				String username = "admin";
            	
            	Cursor cursor =  db.rawQuery("Select * from login where username='"+username+"'and password = '"+userInput.getText().toString().trim()+"'",null);
						
            	if(cursor.getCount() > 0)
				{
				
							Intent intent = new Intent(UserActivity.this, UserchangescreenActivity.class);
							startActivity(intent);
							dialog.cancel();
											  
				} 
            	else
            	{
            		
            		AlertDialog.Builder b1 = new Builder(UserActivity.this);
    				b1.setMessage("Invalid Password");	    				
    				b1.setCancelable(false);
    				
    				b1.setPositiveButton("OK",new DialogInterface.OnClickListener(){

    					@Override
    					public void onClick(DialogInterface dialog, int which) {
    						
    						dialog.cancel();
    						userInput.setText("");
    						    						
    					}
    					  
    				  });
    				
    				AlertDialog a11 = b1.create();				
    				a11.show();
    				
            		
            	}
				
				
				
			}
		});
		
		
		
		dialog.show();
		 
	}
	
	
	
	
	public void showDialog()
	{

	    LayoutInflater li = LayoutInflater.from(UserActivity.this);
	    View promptsView = li.inflate(R.layout.searchprompt, null);
	    //final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UserActivity.this);
	    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(UserActivity.this, R.drawable.round_corner));
	    alertDialogBuilder.setView(promptsView);
	    
	   		
	    
	    
	    
	    final EditText userInput = (EditText) promptsView
	            .findViewById(R.id.editTextDialogUserInput);


	    // set dialog message
	    alertDialogBuilder
	        .setTitle("Login")
	        .setCancelable(false)
	        .setNegativeButton("Go",
	          new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,int id) {

	            	
	            	String username = "admin";
	            	
	            	Cursor cursor =  db.rawQuery("Select * from login where username='"+username+"'and password = '"+userInput.getText().toString().trim()+"'",null);
							
	            	if(cursor.getCount() > 0)
					{
					
								Intent intent = new Intent(UserActivity.this, UserchangescreenActivity.class);
								startActivity(intent);
												  
					} 
	            	else
	            	{
	            		
	            		AlertDialog.Builder b1 = new Builder(UserActivity.this);
	    				b1.setMessage("Invalid Password");	    				
	    				b1.setCancelable(false);
	    				
	    				b1.setPositiveButton("OK",new DialogInterface.OnClickListener(){

	    					@Override
	    					public void onClick(DialogInterface dialog, int which) {
	    						
	    						dialog.cancel();
	    						userInput.setText("");
	    						    						
	    					}
	    					  
	    				  });
	    				
	    				AlertDialog a11 = b1.create();				
	    				a11.show();
	    				
	            		
	            	}
	            	
	                }
	          })
	        .setPositiveButton("Cancel",
	          new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,int id) {
	            dialog.dismiss();
	            }

	          }

	        );

	    // create alert dialog
	    AlertDialog alertDialog = alertDialogBuilder.create();

	    

	    

	    alertDialog.setOnShowListener(new OnShowListener() {
			
			@Override
			public void onShow(DialogInterface dialog) {
			

		        Window view = ((AlertDialog)dialog).getWindow();
		        view.setBackgroundDrawableResource(R.drawable.round_corner);

				
				
			}
		});

	    
	    
	    // show it
	    alertDialog.show();

	}

	
	public static int setProgStates(ImageView img, int states)
	{
		if(states == 0)
		{
			
			img.setBackgroundResource(R.drawable.proon);
			states = 1;
			
			
			
			
			
		}
		else if(states == 1)
		{
			
			
			img.setBackgroundResource(R.drawable.progoff);
			states = 0;
			
			
		}
		
		return states;
	}

	
	public String incrementCounter()
	{
		
		String str;
		
		str = Integer.toHexString(KeywordStore.msgCounter);
		
		
		if(str.length()==1)
		{
			str = "0"+str;
		}
		
		KeywordStore.msgCounter++;
		
		if(KeywordStore.msgCounter == 256)
		{
			KeywordStore.msgCounter = 0;
		}
		
		
		str = "0x"+str;
		
		return str;
		
	}
	
	public String checkValueLength(String str)
	{
				
		if(str.length()==1)
		{
			str = "0"+str;
		}
		
		
		
		str = "0x"+str;
		
		return str;
		
	}
	
	
	
	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		

	    switch(event.getAction())
	    {
	      case MotionEvent.ACTION_DOWN:
	    	  programslide.setBackgroundResource(R.drawable.slideback);
	          x1 = event.getX();                         
	      break;         
	      case MotionEvent.ACTION_UP:
	    	  programslide.setBackgroundResource(0);
	          x2 = event.getX();
	          float deltaX = x2 - x1;

	          if (Math.abs(deltaX) > MIN_DISTANCE)
	          {
	              // Left to Right swipe action
	              if (x2 > x1)
	              {
	            	
	            	  if(KeywordStore.SLIDECOUNT == 1)
	            	  {
	            		  KeywordStore.SLIDECOUNT = 3;
	            	  }
	            	  else
	            	  {
	            		  KeywordStore.SLIDECOUNT =  KeywordStore.SLIDECOUNT - 1;
	            	  }
	      
	            	  
	            	  Animation A = AnimationUtils.loadAnimation(UserActivity.this, R.drawable.fade_out);
	            	  programslide.startAnimation(A);
	            	  programslide.showPrevious();   	            	 
	            	  Animation B = AnimationUtils.loadAnimation(UserActivity.this, R.drawable.fade_in);
	            	  programslide.startAnimation(B);
	            	  
	            	 txtslidCound.setText(KeywordStore.SLIDECOUNT + "");
	            	  
	              }

	              // Right to left swipe action               
	              else 
	              {
	            	 
	            	  if(KeywordStore.SLIDECOUNT == 3)
	            	  {
	            		  KeywordStore.SLIDECOUNT = 1;
	            	  }
	            	  else
	            	  {
	            		  KeywordStore.SLIDECOUNT =  KeywordStore.SLIDECOUNT + 1;
	            	  }
	            	
	            	  Animation A = AnimationUtils.loadAnimation(UserActivity.this, R.drawable.fade_out);
	            	  programslide.startAnimation(A);
	            	  programslide.showNext();  	            	  
	            	  Animation B = AnimationUtils.loadAnimation(UserActivity.this, R.drawable.fade_in);
	            	  programslide.startAnimation(B);
	            	  
	            	  txtslidCound.setText(KeywordStore.SLIDECOUNT + "");
	            	 
	            	 
	              }

	          }
	          else
	          {
	              // consider as something else - a screen tap for example
	          }                          
	      break;   
	    }
	    
	    
	    
	    return true;
		
		
		
	}


	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {


		 final String msg = arg1.toString();
		 Log.d("RECEIVE : ", msg);
		 KeywordStore.receivemsg = msg;
		 
		 
		 runOnUiThread(new Runnable() {
	          @Override
	          public void run() {
	        		
	        	  txtmessage.setText(msg);
	        	  
	          }
	        });
		 	
		
	}
	
	
}





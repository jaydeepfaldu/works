package com.example.waterco;



import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.R.anim;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.inputmethodservice.Keyboard.Key;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import com.myutility.*;

public class MainActivity extends Activity {

	
	Button btncancel, btnlogin;
	EditText edtid, edtpass;
		
	SQLiteDatabase db; 
	
	Button btnlocalmode;
	Button btninternetmode;
	PlayGifView pGif;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainscreen);
		
		
		pGif = (PlayGifView) findViewById(R.id.viewGif);
		pGif.setImageResource(R.drawable.loading);
		
		pGif.setVisibility(View.GONE);
		
		
		db = this.openOrCreateDatabase("WATERCO.DB",MODE_PRIVATE,null);
		
		btninternetmode = (Button) findViewById(R.id.btninternetmode);
		btnlocalmode = (Button) findViewById(R.id.btnlocalmode);
		
				
			
		db.execSQL("CREATE TABLE IF NOT EXISTS login (UserId integer primary key autoincrement, username text, password text)");
		db.execSQL("CREATE TABLE IF NOT EXISTS setting (setid integer primary key autoincrement, sysip text, sysport integer, clientid text)");
		//db.execSQL("DROP TABLE timmer");
		db.execSQL("CREATE TABLE IF NOT EXISTS timmer (timerid integer primary key autoincrement, timername text, timerstarttime text, timerendtime text, timerdays text,  timeractive INTEGER DEFAULT 0)");
		
		
		
		
		Cursor cursor =  db.rawQuery("Select * from login",null);
		if(cursor.getCount() == 0)
		{
			db.execSQL("INSERT INTO login values (1,'admin','admin')");			
			db.execSQL("INSERT INTO setting values (1,'192.168.1.1', 2000, 'admin')");			
		}
		

		
		btninternetmode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				KeywordStore.isInternet = true;
				goNext();
			}
		} );
		
		btnlocalmode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				KeywordStore.isInternet = false;
				
				
				
								
				 goNext();
			}
		});
		
		
				
	}
	
	
	
	public void goNext()
	{
		pGif.setVisibility(View.VISIBLE);
		
		btninternetmode.setVisibility(View.GONE);
		btnlocalmode.setVisibility(View.GONE);
				
		
		 Cursor cursor = db.rawQuery("SELECT sysip, sysport, clientid FROM setting WHERE setid=1", null);
		 

		 cursor.moveToFirst();

		 
		 KeywordStore.ip = cursor.getString(0);
		 KeywordStore.port = cursor.getInt(1);
		 KeywordStore.ClientID = cursor.getString(2);
		 
		
		
		
		new Handler().postDelayed(new Runnable() {
			 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
            	            	
            	String[] ip = getLocalIpAddress().split("\\.");
            	
            	
                        	
            	KeywordStore.myip = checkIPLength(Integer.toHexString(Integer.parseInt(ip[0])))+""+checkIPLength(Integer.toHexString(Integer.parseInt(ip[1])))+""+checkIPLength(Integer.toHexString(Integer.parseInt(ip[2])))+""+checkIPLength(Integer.toHexString(Integer.parseInt(ip[3]))); 
            	KeywordStore.DeviceID = getMacAddress().replace(":", "");
            	
            	
            	
                Intent i = new Intent(MainActivity.this, UserActivity.class);
                startActivity(i); 
                // close this activity
                finish();
                
            }
        }, 10000);
		
	}
	
	public String checkIPLength(String str)
	{
				
		if(str.length()==1)
		{
			str = "0"+str;
		}
		
		
		
		str = "0x"+str;
		
		return str;
		
	}
	
	
	
	public String getMacAddress()
	{
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();
		String mac = wInfo.getMacAddress();
		return mac;
	}
	
	
	
	public String getLocalIpAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
	                    return inetAddress.getHostAddress();
	                }
	            }
	        }
	    } catch (SocketException ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}

}


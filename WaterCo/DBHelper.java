package com.myutility;

import android.app.DownloadManager.Query;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

	public static final String DATABASE_NAME = "WATERCO.db";
	public static String CREART_QRY = "CREATE TABEL DEMO ";
	public static String DROP_QRY;
	
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);		
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREART_QRY);	
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_QRY);
		onCreate(db);
	}

	public boolean insertData()
	   {
		
		 
		
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	    
	      db.insert("contacts", null, contentValues);
	      return true;
	   }
	
	
}

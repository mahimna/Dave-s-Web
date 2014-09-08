package com.example.davesweb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {	
	
	private static final String DATABASE_NAME ="library.db";
	public static final String TITLE = "title";
	public static final String TIME = "time";
	public static final String IMAGE_URL = "image_url";
	
	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME,null,1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE books (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, time TEXT, image_url TEXT)");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		android.util.Log.v("Constants", "Upgrading database, which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS books");
		onCreate(db);
	}
	

}

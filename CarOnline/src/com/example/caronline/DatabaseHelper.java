package com.example.caronline;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "app";
	public static final String TABLE_NAME = "car_details";
	public static final String KEY_ID = "_id";
	public static final String YEAR_OF_MFR = "year_of_mfr";
	public static final String CAR_MODEL = "car_model";
	public static final String MILEAGE = "mileage";
	public static final String LOCATION = "location";
	public static final String PRICE = "price";

	SQLiteDatabase db;

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE = "create table " + TABLE_NAME + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + YEAR_OF_MFR
				+ " INTEGER," + CAR_MODEL + " TEXT," + MILEAGE + " INTEGER,"
				+ LOCATION + " TEXT," + PRICE + " INTEGER)";

		db.execSQL(CREATE_TABLE);
		// db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists " + TABLE_NAME);
		onCreate(db);
	}

}

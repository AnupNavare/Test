package com.example.pedometer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class UserProfileDB extends SQLiteOpenHelper{
	MainActivity mainA;
	private static String DBNAME = "SmartPedoDB";

	private static int VERSION = 1;

	private static String TABLENAME = "Userprofile";

	public static final String FIELD_ROW_ID = "user_id";

	public static final String FIELD_USERNAME = "username";

	public static final String FIELD_CALORIES = "calories";

	public static final String FIELD_STEPS = "steps";

	private SQLiteDatabase mDb;

	public UserProfileDB(Context context) {
		super(context, DBNAME, null, VERSION);
		System.out.println("in onCreate of userprofile");
		//mDb = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.i("in oncreate", "yes..");
		Toast.makeText(mainA, "entered on create", Toast.LENGTH_LONG).show();
		String sqlCreate = "CREATE TABLE " + TABLENAME + " ( " + FIELD_ROW_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIELD_USERNAME
				+ " VARCHAR(100)," + FIELD_CALORIES + " REAL," + FIELD_STEPS 
				+ " INTEGER " + " ) ";
		db.execSQL(sqlCreate);
		//Toast.makeText(mainA, ""+sqlCreate, Toast.LENGTH_LONG).show();
		Log.i("Userprofilecreation", ""+sqlCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Toast.makeText(mainA, "yes", Toast.LENGTH_LONG).show();
		Log.i("Coming in upgrade","Yes");
		String sqlCreate = "CREATE TABLE " + TABLENAME + " ( " + FIELD_ROW_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIELD_USERNAME
				+ " VARCHAR(100)," + FIELD_CALORIES + " REAL," + FIELD_STEPS 
				+ " INTEGER " + " ) ";
		db.execSQL(sqlCreate);
		Toast.makeText(mainA, ""+sqlCreate, Toast.LENGTH_LONG).show();
	}
	
	public void showProfile(){
		SQLiteDatabase db = this.getReadableDatabase();
		Toast.makeText(mainA, "", Toast.LENGTH_LONG).show();
		Log.i("in getValues", "yes..");
		UserLocation[] loc = new UserLocation[2];
		UserLocation startLocation = new UserLocation();
		UserLocation stopLocation = new UserLocation();
		loc[0] = startLocation;
		loc[1] = stopLocation;
		
			String selectQuery = "SELECT calories  FROM " + TABLENAME + " WHERE "
					+ FIELD_USERNAME + " = anup ";

			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					int i = 0;
					Log.i("inside cursor not null","yes..");
					Log.i("calorie", ""+cursor.getDouble(1));
					//Log.i("loc array", ""+loc[i]);
					
					Toast.makeText(mainA, ""+cursor.getDouble(1), Toast.LENGTH_LONG).show();
				} else
					System.out.println("query not built");
			} else
				System.out.println("Cursor is null");

		
		//return loc;

	}
}

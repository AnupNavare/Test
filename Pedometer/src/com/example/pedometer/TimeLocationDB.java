package com.example.pedometer;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class TimeLocationDB extends SQLiteOpenHelper {

	private static String DBNAME = "SmartPedoDB";

	private static int VERSION = 1;

	private static String TABLENAME = "Location";

	public static final String FIELD_ROW_ID = "_id";

	public static final String FIELD_LONGITUDE = "longitude";

	public static final String FIELD_LATITUDE = "latitude";

	public static final String FIELD_TIME = "currenttime";

	private SQLiteDatabase mDb;

	public TimeLocationDB(Context context) {
		super(context, DBNAME, null, VERSION);
		mDb = getWritableDatabase();
	}

	private void open() throws SQLException {
		// TODO Auto-generated method stub
		// mDb = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.i("in oncreate", "yes..");
		String sqlCreate = "CREATE TABLE " + TABLENAME + " ( " + FIELD_ROW_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIELD_LATITUDE
				+ " REAL," + FIELD_LONGITUDE + " REAL," + FIELD_TIME 
				+ " INTEGER " + " ) ";
		db.execSQL(sqlCreate);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public long insert(UserLocation user) {
		SQLiteDatabase db = getWritableDatabase();
		Log.i("in dbInsert", "Yes...reached");
		ContentValues values = new ContentValues();
		values.put(FIELD_TIME, user.getTime());
		values.put(FIELD_LONGITUDE, user.getLongitude());
		values.put(FIELD_LATITUDE, user.getLatitude());
		long rowid = db.insert(TABLENAME, null, values);
		Log.i("row", "Yes...inserted");
		return rowid;
	}

	@Override
	public synchronized void close() {
		// TODO Auto-generated method stub
		Log.i("in close", "yes..");
		super.close();
	}

	public UserLocation[] getValues(long rowid) {
		SQLiteDatabase db = this.getReadableDatabase();

		Log.i("in getValues", "yes..");
		UserLocation[] loc = new UserLocation[2];
		UserLocation startLocation = new UserLocation();
		UserLocation stopLocation = new UserLocation();
		loc[0] = startLocation;
		loc[1] = stopLocation;
		
		int i,j;
		int rowidDifference = (int)(rowid - 1);
		for (i = 0,j = (int) rowid; j >= rowidDifference; j--,i++) {
			String selectQuery = "SELECT * FROM " + TABLENAME + " WHERE "
					+ FIELD_ROW_ID + " = " + j + "";

			Cursor cursor = db.rawQuery(selectQuery, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					Log.i("inside cursor not null","yes..");
					Log.i("latitide", ""+cursor.getDouble(1));
					//Log.i("loc array", ""+loc[i]);
					loc[i].setLatitude(cursor.getDouble(1));
					loc[i].setLongitude(cursor.getDouble(2));
					loc[i].setTime(cursor.getLong(3));
					Log.i("StartLocation--->", "" + loc[i].getLatitude()
							+ " , " + loc[i].getLongitude());
					Log.i("Time Taken -->", ""+loc[i].getTime());
				} else
					System.out.println("query not built");
			} else
				System.out.println("Cursor is null");

		}
		return loc;
	}
}

package com.example.caronline;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseSource {

	public static final String LOGTAG="EXPLORECA";
	
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	private static final String[] allColumns = {
		DatabaseHelper.KEY_ID,
		DatabaseHelper.YEAR_OF_MFR,
		DatabaseHelper.CAR_MODEL,
		DatabaseHelper.MILEAGE,
		DatabaseHelper.LOCATION,
		DatabaseHelper.PRICE};
	
	private static final String[] forListColumns = {
		DatabaseHelper.YEAR_OF_MFR,
		DatabaseHelper.CAR_MODEL,
		DatabaseHelper.PRICE,
		DatabaseHelper.KEY_ID};
	
	
	public DatabaseSource(Context context) {
		dbhelper = new DatabaseHelper(context);
	}
	
	public void open() {
		database = dbhelper.getWritableDatabase();
	}

	public void close() {
				
		dbhelper.close();
	}
	
	public Cardata create(Cardata cardata) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.YEAR_OF_MFR, cardata.getYearOfManufacture());
		values.put(DatabaseHelper.CAR_MODEL,cardata.getCarModel() );
		values.put(DatabaseHelper.MILEAGE,cardata.getMileage() );
		values.put(DatabaseHelper.LOCATION,cardata.getLocation() );
		values.put(DatabaseHelper.PRICE,cardata.getPrice() );
		long insertid = database.insert(DatabaseHelper.TABLE_NAME, null, values);
		cardata.setId(insertid);
		return cardata;
	}
	
	public List<Cardata> findAll() {
		
		
		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, allColumns, 
				null, null, null, null, null);
				
		List<Cardata> cars = cursorToList(cursor);
		return cars;
	}
	
	public ArrayList<String> getLocations(){
		Log.i("here reached", "getLocatioins");
		ArrayList<String> loc = new ArrayList<String>();
		database = dbhelper.getReadableDatabase();
		String sqlGetLocation = "SELECT DISTINCT location FROM " + DatabaseHelper.TABLE_NAME + ";";
		
		//Cursor cursor = null;
		Cursor cursor = database.rawQuery(sqlGetLocation, null);
		Log.v("DatabaseSource", ""+cursor);
		 if (cursor.moveToFirst() && cursor!= null) {
	            do {
	                loc.add(cursor.getString(0));
	            } while (cursor.moveToNext());
	        }
		 
		 cursor.close();
		 database.close();
		 Log.v("DatabaseSource", ""+loc);
		 return loc;
	}
	
public ArrayList<Cardata> findFiltered(String selection) {
		database = dbhelper.getReadableDatabase();
		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, forListColumns, 
				"" + DatabaseHelper.LOCATION + " = ? ", new String[] { selection }, null, null, DatabaseHelper.YEAR_OF_MFR);
		
		Log.i(LOGTAG, "Returned " + cursor.getCount() + " rows");
		ArrayList<Cardata> listOfCarsFiltered = cursorToList(cursor);
		return listOfCarsFiltered;
	}
	
	private ArrayList<Cardata> cursorToList(Cursor cursor) {
		ArrayList<Cardata> cars = new ArrayList<Cardata>();
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Cardata car = new Cardata();
				car.setYearOfManufacture(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.YEAR_OF_MFR)));
				car.setCarModel(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CAR_MODEL)));
				car.setPrice(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PRICE)));
				car.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
				cars.add(car);	
			}
		}
		return cars;
	}

	public Cardata getAll(long selectedCarId) {
		// TODO Auto-generated method stub
		Cardata car = new Cardata();
		database = dbhelper.getReadableDatabase();
		Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, allColumns, 
				"" + DatabaseHelper.KEY_ID + " = ? ", new String[] { ""+selectedCarId }, null, null,null);
		Log.i("Count of getAll", ""+cursor.getCount());
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				
				car.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
				car.setYearOfManufacture(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.YEAR_OF_MFR)));
				car.setCarModel(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CAR_MODEL)));
				car.setMileage(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.MILEAGE)));
				car.setLocation(cursor.getString(cursor.getColumnIndex(DatabaseHelper.LOCATION)));
				car.setPrice(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.PRICE)));
			}
		}
		return car;
	}

	/*public List<Cardata> getCarListFromLocation(String loc) {
		// TODO Auto-generated method stub
		List<Cardata> carList = new ArrayList<Cardata>();
		database = dbhelper.getReadableDatabase();
		String sqlGetLocation = "SELECT " + DatabaseHelper.YEAR_OF_MFR + "," + DatabaseHelper.CAR_MODEL + DatabaseHelper.TABLE_NAME + ";";
		
		//Cursor cursor = null;
		Cursor cursor = database.rawQuery(sqlGetLocation, null);
		Log.v("DatabaseSource", ""+cursor);
		 if (cursor.moveToFirst() && cursor!= null) {
	            do {
	                loc.add(cursor.getString(0));
	            } while (cursor.moveToNext());
	        }
		 
		 cursor.close();
		 database.close();
		 Log.v("DatabaseSource", ""+loc);
		 return loc;
	}*/
	
}
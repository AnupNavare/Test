package com.example.caronline;

import java.util.ArrayList;
import java.util.List;

import android.R.anim;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnItemSelectedListener{

	Spinner spinnerSelectLocation;
	Button btnFindCars;
	String selectedLoc;
	
	DatabaseSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		spinnerSelectLocation = (Spinner) findViewById(R.id.spinnerSelectLocation);
		btnFindCars = (Button) findViewById(R.id.btnSearchCars);
		datasource = new DatabaseSource(this);
		datasource.open();
		
		List<Cardata> cars = datasource.findAll();
		if (cars.size() == 0) {
			readAndInsertData();
			cars = datasource.findAll();
		}
		new GetLocationFromDBTask(this,spinnerSelectLocation).execute();
		spinnerSelectLocation.setOnItemSelectedListener(this);
		btnFindCars.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),CarListActivity.class);
				Log.i("yes",""+selectedLoc);
				intent.putExtra("KEY_LOCATION", selectedLoc);
				MainActivity.this.startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}
	
	//Read Data From JSON File and Insert into db
	private void readAndInsertData() {
		JSONParser parser = new JSONParser();
		List<Cardata> cars = parser.JSONPParsing(this);
		
		for (Cardata car : cars) {
			datasource.create(car);
		}
	
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
		// TODO Auto-generated method stub
		selectedLoc = parent.getItemAtPosition(position).toString();
		Log.i("yesss",""+selectedLoc);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}

class GetLocationFromDBTask extends AsyncTask<Void, Void, ArrayList<String>> {

	private Context myContext;
	private Spinner mySpinner;
	ArrayList<String> locationList = new ArrayList<String>();
	public GetLocationFromDBTask(Context context, Spinner s) {
		// TODO Auto-generated constructor stub
		myContext = context;
		mySpinner = s;
	}

	@Override
	protected ArrayList<String> doInBackground(Void... params) {
		// TODO Auto-generated method stub
		Log.v("MainActivity", "doInBackground");
		//DatabaseHelper dbHelper = new DatabaseHelper(myContext);
		DatabaseSource datasource = new DatabaseSource(myContext);
		locationList = datasource.getLocations();
		//Log.i("values", ""+locationList.get(1));
		
		return locationList;
	}

	@Override
	protected void onPostExecute(ArrayList<String> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(myContext, R.layout.support_simple_spinner_dropdown_item,result);
		mySpinner.setAdapter(locationAdapter);
		//Log.i("values", ""+locationList.get(1));
		//db.close();
		/*
		String[] str = {"Mountain","Santa","Milipitas"};
		ArrayAdapter<String> adap = new ArrayAdapter<String>(myContext, android.R.layout.simple_dropdown_item_1line, str);
		mySpinner.setAdapter(adap);*/
	}
	
	
	
}

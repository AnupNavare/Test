package com.example.caronline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class CarListActivity extends ListActivity implements
		OnItemClickListener {

	DatabaseHelper dbHelper;
	DatabaseSource database;
	ArrayList<Cardata> carData;
	ArrayAdapter<Cardata> adapter;
	ListView listViewCars;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		database = new DatabaseSource(this);
		Log.i("hereee", "" + extras.getString("KEY_LOCATION"));
		carData = new ArrayList<Cardata>();
		listViewCars = getListView();
		if (extras != null) {
			carData = database.findFiltered(extras.getString("KEY_LOCATION"));
			Log.i("number of cars in this location ", "" + carData.size());
		}

		adapter = new MyAdapter(this, R.layout.row_layout, carData);
		listViewCars.setAdapter(adapter);
		listViewCars.setOnItemClickListener(this);

		// listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sort, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem menuitem) {
		ArrayList<Cardata> car = new ArrayList<Cardata>();
		Cardata mCarData = new Cardata();

		switch (menuitem.getItemId()) {

		case R.id.sortByPrice:
			// car = carData;
			car = mCarData.sortCarByPrice(carData);
			// listViewCars = getListView();
			adapter = new MyAdapter(CarListActivity.this, R.layout.row_layout,
					car);
			listViewCars.setAdapter(adapter);
			adapter.notifyDataSetChanged();

			break;

		case R.id.sortByCar:
			car = carData;
			mCarData.sortCarByName(car);
			listViewCars = getListView();
			adapter = new MyAdapter(CarListActivity.this, R.layout.row_layout,
					car);
			listViewCars.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			break;
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Cardata pos = carData.get(position);
		Log.i("car price", "" + pos.getPrice());
		Log.i("car id", "" + pos.getId());
		long selectedId = pos.getId();
		Log.i("car id", "" + selectedId);
		Intent intent = new Intent(this, CarDetailActivity.class);
		intent.putExtra("SELECTED_ID", selectedId);
		startActivity(intent);

	}
}
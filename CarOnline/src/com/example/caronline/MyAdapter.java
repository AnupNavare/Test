package com.example.caronline;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Cardata> {

	Context context;
	ArrayList<Cardata> carDataItem = null;
	int resourceId;

	public MyAdapter(Context ctx, int resourceId, ArrayList<Cardata> data) {

		super(ctx, resourceId, data);
		Log.i("constructor", "reached");
		context = ctx;
		carDataItem = data;
		this.resourceId = resourceId;
		Log.i("Count", "" + getCount());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i("getView", "reached in getView");
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(resourceId, null);
		}
		Log.i("getView", "reached in getView");
		TextView titleCarModel = (TextView) row
				.findViewById(R.id.carModelTitle);
		TextView titleYear_of_mfr = (TextView) row
				.findViewById(R.id.yearOfMfrTitle);
		;
		TextView titlePrice = (TextView) row.findViewById(R.id.priceTitle);

		Cardata carData = carDataItem.get(position);
		Log.v("Adapter Price", "" + carData.getPrice());
		Log.v("car model", "" + carData.getCarModel());
		Log.v("year", "" + carData.getYearOfManufacture());
		Log.v("id", "" + carData.getId());

		titleCarModel.setText(carData.getCarModel());
		titleYear_of_mfr.setText("" + carData.getYearOfManufacture());
		titlePrice.setText("" + carData.getPrice());

		return row;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return carDataItem.size();
	}

}

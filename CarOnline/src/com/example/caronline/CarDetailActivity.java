package com.example.caronline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CarDetailActivity extends Activity {
	DatabaseSource dbSource;
	Cardata car;
	TextView txtYearOfMfr;
	TextView txtCar;
	TextView txtMileage;
	TextView txtLoc;
	TextView txtPrice;
	Button btnBuy;
	Button btnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		long selectedCarId = extras.getLong("SELECTED_ID");
		Log.i("ID", "" + selectedCarId);

		setContentView(R.layout.car_details);

		dbSource = new DatabaseSource(this);
		car = dbSource.getAll(selectedCarId);

		txtYearOfMfr = (TextView) findViewById(R.id.yearOfMfrValue);
		txtCar = (TextView) findViewById(R.id.carValue);
		txtMileage = (TextView) findViewById(R.id.mileageValue);
		txtLoc = (TextView) findViewById(R.id.locValue);
		txtPrice = (TextView) findViewById(R.id.priceValue);

		btnBuy = (Button) findViewById(R.id.btnBuy);
		btnCancel = (Button) findViewById(R.id.btnCancel);

		txtYearOfMfr.setText("" + car.getYearOfManufacture());
		txtCar.setText(car.getCarModel());
		txtMileage.setText("" + car.getMileage());
		txtLoc.setText(car.getLocation());
		txtPrice.setText("" + car.getPrice());

		btnBuy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CarDetailActivity.this,
						DialogActivity.class);
				CarDetailActivity.this.startActivity(intent);
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						MainActivity.class);
				CarDetailActivity.this.startActivity(i);
			}
		});

	}
}

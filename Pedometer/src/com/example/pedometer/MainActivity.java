package com.example.pedometer;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.LocalActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener,
		com.google.android.gms.location.LocationListener {

	ImageView MainADistanceIconImageV;
	ImageView MainATimeIconImageV;
	ImageView MainACaloriesIconImageV;
	ImageView MainAStepsIconImageV;

	TextView MainADistanceTxt;
	public TextView MainATimeTxt;
	TextView MainACaloriesTxt;
	TextView MainAStepsTxt;

	TextView MainADistanceLbl;
	TextView MainATimeLbl;
	TextView MainACaloriesLbl;
	TextView MainAStepsLbl;

	// GetLocation location;
	Button MainAStartBtn;
	Button MainAStopBtn;
	TextView MainAGraphTxtV;

	private LocationClient mGetLocationLocationClient;
	private LocationRequest mGetLocationLocationRequest;
	Location mGetLocationcurrentLocation;

	int ok = 0;
	double climbingStairsMET = 4.0;
	double weight = 62.5;
	double constFactor = 0.0175;

	TimeLocationDB mDb;
	UserProfileDB mUserDb;

	StepCounter stepCounterObj;
	TimerActivity mTimerA = new TimerActivity();

	boolean stopBtnClicked = false;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
				|| !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle("enable location and GPS");
			alertDialog.setMessage("Please enable Location Services and GPS");
			alertDialog.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent intent = new Intent(
									Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							startActivity(intent);

						}

					});

			Dialog GPSalert = alertDialog.create();
			GPSalert.setCanceledOnTouchOutside(false);
			GPSalert.show();
		} else {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle("Fine");
			alertDialog.setMessage("Its Fine");
		}
		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color.rgb(Color.CYAN,
				Color.MAGENTA, Color.GREEN)));

		MainADistanceIconImageV = (ImageView) findViewById(R.id.MainAXmlDistanceIcon);
		MainADistanceTxt = (TextView) findViewById(R.id.MainAXmlDistanceTxt);
		MainADistanceLbl = (TextView) findViewById(R.id.MainAXmlDistanceLbl);

		MainATimeIconImageV = (ImageView) findViewById(R.id.MainAXmlTimeIcon);
		MainATimeTxt = (TextView) findViewById(R.id.MainAXmlTimeTxt);
		MainATimeLbl = (TextView) findViewById(R.id.MainAXmlTimeLbl);

		MainACaloriesIconImageV = (ImageView) findViewById(R.id.MainAXmlCaloriesIcon);
		MainACaloriesTxt = (TextView) findViewById(R.id.MainAXmlCaloriesTxt);
		MainACaloriesLbl = (TextView) findViewById(R.id.MainAXmlCaloriesLbl);

		MainAStepsIconImageV = (ImageView) findViewById(R.id.MainAXmlStepIcon);
		MainAStepsTxt = (TextView) findViewById(R.id.MainAXmlStepTxt);
		MainAStepsLbl = (TextView) findViewById(R.id.MainAXmlStepLbl);

		Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),
				R.drawable.distancecovered);
		Bitmap bitmapOrg2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.timepedo);
		Bitmap bitmapOrg3 = BitmapFactory.decodeResource(getResources(),
				R.drawable.calorie);
		Bitmap bitmapOrg4 = BitmapFactory.decodeResource(getResources(),
				R.drawable.steps);

		int newWidth = 50;
		int newHeight = 50;

		MainADistanceIconImageV.setScaleType(ScaleType.CENTER);
		Bitmap scaledDistanceBitmap = Bitmap.createScaledBitmap(bitmapOrg,
				newWidth, newHeight, true);
		MainADistanceIconImageV.setImageBitmap(scaledDistanceBitmap);
		MainADistanceLbl.setText("Distance");

		MainATimeIconImageV.setScaleType(ScaleType.CENTER);
		Bitmap scaledTimeBitmap = Bitmap.createScaledBitmap(bitmapOrg2,
				newWidth, newHeight, true);
		MainATimeIconImageV.setImageBitmap(scaledTimeBitmap);
		MainATimeLbl.setText("Time");

		Bitmap scaledCalorieBitmap = Bitmap.createScaledBitmap(bitmapOrg3,
				newWidth, newHeight, true);
		MainACaloriesIconImageV.setImageBitmap(scaledCalorieBitmap);
		MainACaloriesLbl.setText("Calories");

		MainAStepsIconImageV.setScaleType(ScaleType.CENTER);
		Bitmap scaledStepsBitmap = Bitmap.createScaledBitmap(bitmapOrg4,
				newWidth, newHeight, true);
		MainAStepsIconImageV.setImageBitmap(scaledStepsBitmap);
		MainAStepsLbl.setText("Steps");

		stepCounterObj = new StepCounter(
				(SensorManager) getSystemService(SENSOR_SERVICE), this);

		MainAStartBtn = (Button) findViewById(R.id.getStartLocation);
		MainAStartBtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("in btnClick", "yes..");
				if (ok == GooglePlayServicesUtil
						.isGooglePlayServicesAvailable(MainActivity.this)) {
					Log.i("In googpleplayservice", "Available..");
					mGetLocationLocationClient = new LocationClient(
							MainActivity.this, MainActivity.this,
							MainActivity.this);
					mGetLocationLocationClient.connect();

				} else
					mGetLocationLocationClient.disconnect();
				mTimerA.startTimer();

			}
		});

		/* ............create location table............ */
		mDb = new TimeLocationDB(this);
		/* .............create userprofile table......... */
		// mUserDb = new UserProfileDB(this);
		// Log.i("userprofile", "created");

		MainAStopBtn = (Button) findViewById(R.id.getStopLocation);
		MainAStopBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("in btnClick", "yes..");
				// Toast.makeText(MainActivity.this, "on stop",
				// Toast.LENGTH_LONG)
				// .show();
				stopBtnClicked = true;
				if (ok == GooglePlayServicesUtil
						.isGooglePlayServicesAvailable(MainActivity.this)) {
					Log.i("In googpleplayservice", "Available..");
					mGetLocationLocationClient = new LocationClient(
							MainActivity.this, MainActivity.this,
							MainActivity.this);
					mGetLocationLocationClient.connect();

				} else
					mGetLocationLocationClient.disconnect();

			}
		});

		MainAGraphTxtV = (TextView) findViewById(R.id.showHistory);
		MainAGraphTxtV.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent graphIntent = new Intent(MainActivity.this,
						GraphActivity.class);
				startActivity(graphIntent);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("in onStart", "yes..");
		// mGetLocationLocationClient.connect();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		// Toast.makeText(this, "onLocationCahnged", Toast.LENGTH_SHORT).show();
		Log.i("in onLocationChanged", "Yes..");
		UserLocation userLoc = new UserLocation();
		UserLocation locValues[] = new UserLocation[2];

		double lat[] = new double[2];
		double longi[] = new double[2];
		double timeTaken[] = new double[2];
		float distanceBetweenRes[] = new float[1];
		long time = Calendar.getInstance().getTimeInMillis();

		if (location != null) {
			// Toast.makeText(this, "location not null",
			// Toast.LENGTH_LONG).show();
			String msg = "Location-->\n"
					+ Double.toString(location.getLatitude()) + ","
					+ Double.toString(location.getLongitude());
			MainADistanceTxt.setText(msg);

			File dbFile = getApplicationContext()
					.getDatabasePath("SmartPedoDB");
			if (!dbFile.exists()) {
				Log.i("Database", "Not Found");
				// Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
			} else {

				// Toast.makeText(this, "found", Toast.LENGTH_SHORT).show();
				Log.i("Latitude received", "" + location.getLatitude());
				Log.i("Longi received", "" + location.getLongitude());
				userLoc.setLatitude(location.getLatitude());
				userLoc.setLongitude(location.getLongitude());
				userLoc.setTime(time);

				long insertedRowId = mDb.insert(userLoc);

				// mDb.close();
				// Toast.makeText(getApplicationContext(), "" + insertedRowId,
				// Toast.LENGTH_LONG).show();

				if (stopBtnClicked) {
					Log.i("StopBtnClicked", "Yes...");
					locValues = mDb.getValues(insertedRowId);
					Log.i("location array length", "" + locValues.length);
					for (int i = 0; i < locValues.length; i++) {
						Log.i("locValues at i", "" + locValues[i]);
						lat[i] = locValues[i].getLatitude();
						longi[i] = locValues[i].getLongitude();
						timeTaken[i] = locValues[i].getTime();
						// System.out.println("Latitiude-->" + lat[i]
						// + ",Longitude -->" + longi[i]);
						// System.out.println("Time-->" + timeTaken[i]);
					}
					for (int i = 0; i < lat.length; i++) {
						/*
						 * Toast.makeText(getApplicationContext(), "Location-->"
						 * + lat[i] + "," + longi[i], Toast.LENGTH_LONG).show();
						 */
					}

					Location.distanceBetween(lat[1], longi[1], lat[0],
							longi[0], distanceBetweenRes);
					// double distanceInMiles = distanceBetweenRes[0] *
					// 0.000621371;
					// Toast.makeText(this, "Distance is " + distanceInMiles,
					// Toast.LENGTH_SHORT).show();
					MainADistanceTxt.setText("Distance in Meters->"
							+ distanceBetweenRes[0]);

					/* .............Calculate the time taken........... */
					double finaltime = timeTaken[0] - timeTaken[1];
					// long finalTimeInMinutes =
					// TimeUnit.MILLISECONDS.toMinutes(finaltime);
					double hours = (finaltime / (1000 * 60 * 60));
					Log.d("TAG", "Hours: " + hours);
					// long finalHours = finaltime%=hours;
					MainATimeTxt.setText("Time In Hours->" + hours);

					/* ..................Calculate calories burned.............. */
					double caloriesBurned = constFactor * climbingStairsMET
							* weight * finaltime;
					MainACaloriesTxt.setText("Calories->" + caloriesBurned);

					if (caloriesBurned < 50000.00) {
						// prepare intent which is triggered if the
						// notification is selected
						Bitmap remote_picture = BitmapFactory.decodeResource(
								getResources(), R.drawable.smartpedoicon);
						int nId = 12345;
						// Toast.makeText(this, "Notification must be sent",
						// Toast.LENGTH_LONG).show();
						Intent intent = new Intent(this,
								NotificationReceiver.class);
						PendingIntent pIntent = PendingIntent.getActivity(this,
								0, intent, 0);

						// build notification
						// Notification.Builder notiBuilder = new ;
						Notification mNoti = new Notification.Builder(this)
								.setContentTitle("Low Calories Burned")
								.setContentText(
										"You burned more calories Yeterday..U need to Improve")
								.setSmallIcon(R.drawable.smartpedoicon)
								.setAutoCancel(true).setContentIntent(pIntent)
								.build();

						NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

						notificationManager.notify(nId, mNoti);
					}
					/*
					 * ..............steps taken shown in
					 * setStepCounter..............
					 */

				} else
					Log.i("StopBtn", "NotClicked");

			}

		} else {
			Log.i("Location", "is null");
			// Toast.makeText(this, "Location null", Toast.LENGTH_LONG).show();
		}
		mDb.close();
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		Log.i("connectionFailed", "yes...shit");

	}

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		Log.i("in onConnected", "yes..here and connected");
		mGetLocationcurrentLocation = mGetLocationLocationClient
				.getLastLocation();
		mGetLocationLocationRequest = LocationRequest.create();
		mGetLocationLocationRequest
				.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		// mGetLocationLocationRequest.setInterval(50);
		mGetLocationLocationClient.requestLocationUpdates(
				mGetLocationLocationRequest, this);

	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		Log.i("in onDisconnected", "yes..here");
	}

	protected void onResume() {
		super.onResume();
		stepCounterObj.onResume();
	}

	protected void onPause() {
		super.onPause();
		stepCounterObj.onPause();
	}

	public void setStepCounter(String str) {
		// TODO Auto-generated method stub
		MainAStepsTxt.setText(str);
	}

}

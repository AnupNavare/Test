package com.example.pedometer;

import android.util.Log;
import android.widget.Toast;

public class UserLocation {
	double longitude;
	double latitude;
	long time;
	
	public UserLocation(){
		longitude = 0.0;
		latitude = 0.0;
		time = 0;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		Log.i("time", ""+time);
		this.time = time;
	}
	
	
}

package com.example.pedometer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.Log;

@TargetApi(19)
public class StepCounter implements SensorEventListener{
	boolean activityRunning;
	private SensorManager sensorManager; 	
	private MainActivity mainObj;
	boolean isActivityRunning = true;
	
	
	public StepCounter(SensorManager sm,MainActivity ma){
		sensorManager = sm;
		mainObj = ma;
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		 if (activityRunning) {
	            mainObj.setStepCounter(String.valueOf(event.values[0]));
	        }


	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
	protected void onResume() {
		//super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            //oast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        	Log.i("countSensor", "It is null");
        }
	}


	protected void onPause(){
		//super.onPause();
		activityRunning = false;
	}
	
}

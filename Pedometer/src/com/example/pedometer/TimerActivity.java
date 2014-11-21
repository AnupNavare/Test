package com.example.pedometer;

import com.example.pedometer.MainActivity;

import android.os.Handler;
import android.os.SystemClock;


public class TimerActivity {
	
	private long startTime = 0L;
	private Handler customHandler = new Handler();
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	
	MainActivity mActivity;
	
	public void startTimer(){
		startTime = SystemClock.uptimeMillis();
		customHandler.postDelayed(updateTimerThread, 0);
	}
	
	private Runnable updateTimerThread = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			mActivity = new MainActivity();
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
			updatedTime = timeSwapBuff + timeInMilliseconds;
			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			secs = secs % 60;	
			int milliseconds = (int) (updatedTime % 1000);
			/*mActivity.MainATimeTxt.setText("" + mins + ":"
			+ String.format("%02d", secs) + ":"
			+ String.format("%03d", milliseconds));*/
			customHandler.postDelayed(this, 0);
			
		}
	};
}

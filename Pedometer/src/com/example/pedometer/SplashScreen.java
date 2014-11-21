package com.example.pedometer;

import com.example.pedometer.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashScreen extends Activity {

	TextView splashtext;

	private static int SPLASH_TIME_OUT = 4000;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		splashtext = (TextView) findViewById(R.id.splashTextV);
		Typeface myFont = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Bold.ttf");
		splashtext.setTypeface(myFont);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(i);

				finish();
			}
		}, SPLASH_TIME_OUT);

	}
}

package com.example.searchevent;

import com.example.searchevent.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SearchEventMainActivity extends Activity{
	TextView cityTxt;
	Button searchBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchxml);
		cityTxt = (TextView)findViewById(R.id.cityTxt);
		searchBtn = (Button)findViewById(R.id.searchBtn);
		searchBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//String city = null;
				//System.out.println("city:"+cityTxt.getText().toString());
				//city = cityTxt.getText().toString();
				if(cityTxt.getText().length() != 0){
					Intent sendCityIntent = new Intent(SearchEventMainActivity.this,SearchActivity.class);
					sendCityIntent.putExtra("city", cityTxt.getText().toString());
					System.out.println(cityTxt.getText());
					startActivity(sendCityIntent);
				}
				else{
					System.out.println("no city");
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(SearchEventMainActivity.this);
					alertDialog.setTitle("Search Incomplete");
					alertDialog.setMessage("Please enter city");
					alertDialog.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
										dialog.cancel();

								}
				

							});
					AlertDialog alert = alertDialog.create();
					alert.setCanceledOnTouchOutside(false);
					alert.show();
				}
					
				
			}
		});
			
			
		
	}
}

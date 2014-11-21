package com.example.caronline;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DialogActivity extends Activity {

	Button sendBtn;
	Button cancelBtn;
	EditText txtphoneNo;

	// EditText txtMessage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
		sendBtn = (Button) findViewById(R.id.send_button);
		cancelBtn = (Button) findViewById(R.id.cancel_button);
		txtphoneNo = (EditText) findViewById(R.id.phone_edit);

		cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Log.i("cancel", "clicked");
				// TODO Auto-generated method stub

				finish();
			}
		});
	}

	public void sendSMSMessage(View view) {
		String phoneNo = txtphoneNo.getText().toString();
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNo, null,
					"Thank you for buying the car ", null, null);
			Toast.makeText(getApplicationContext(),
					"Congratulations!!! You got a new car ", Toast.LENGTH_LONG)
					.show();
			finish();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"SMS faild, please try again.", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

}

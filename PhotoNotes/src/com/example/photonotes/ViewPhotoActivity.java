package com.example.photonotes;

//import android.R;
import com.example.photonotes.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPhotoActivity extends Activity {
	ImageView selectedCaptionPhoto;
	TextView selectedCaption;
	DatabaseHandler viewPhotoADBhandler;
	String sentCaptionFromMainA;
	String sentPhotoPathFromViewPhotoA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewphoto);
		selectedCaption = (TextView) findViewById(R.id.ViewPXmlSelectedCaption);
		selectedCaptionPhoto = (ImageView) findViewById(R.id.viewPXmlSelectedPhoto);

		Intent viewPhotoARecieverIntent = getIntent();
		sentCaptionFromMainA = viewPhotoARecieverIntent.getExtras().getString(
				"SelectedCaption");
		viewPhotoADBhandler = new DatabaseHandler(this);

		viewPhotoADBhandler.open();
		Log.d("after db open", "true");
		sentPhotoPathFromViewPhotoA = viewPhotoADBhandler
				.getPhoto(sentCaptionFromMainA);
		viewPhotoADBhandler.close();

		BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inSampleSize = 4;
		options.outWidth = 50;
		options.outHeight = 50;

		Bitmap pathToBitmap = BitmapFactory.decodeFile(
				sentPhotoPathFromViewPhotoA, options);

		selectedCaptionPhoto.setImageBitmap(pathToBitmap);
		selectedCaption.setText(sentCaptionFromMainA);
	}

}

package com.example.photonotes;

import java.io.File;

import java.net.URI;
import java.util.Date;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddPhoto extends Activity implements OnClickListener {

	public static final int IMAGE_CAPTURE_REQ_CODE = 100;
	public static final int SEND_IMAGE_RESULT_CODE = 120;
	ImageView photoPreview_addPhotoA;
	EditText addCaption_addPhotoA;
	Bitmap capturedPhotoBitmap;
	Button savePhoto_addPhotoA;
	Button capturePhoto_addPhotoA;
	File capturedImageFileAddPhotoA;
	String imagePath;
	String selectedImagePath;
	Tab photoNotesListTabAddPhotoA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addphoto);

		addCaption_addPhotoA = (EditText) findViewById(R.id.addCaption_addPhotoXml);
		capturePhoto_addPhotoA = (Button) findViewById(R.id.takePhoto_addPhotoXml);
		savePhoto_addPhotoA = (Button) findViewById(R.id.savePhoto_addPhotoXml);

		addCaption_addPhotoA.setVisibility(View.INVISIBLE);
		capturePhoto_addPhotoA.setOnClickListener(this);

	}

	/* .....Method to set the photo path to Uri....... */
	public Uri setImageUri() {
		capturedImageFileAddPhotoA = new File(Environment
				.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator + "image" + new Date().getTime() + ".jpeg");
		Uri imageUri = Uri.fromFile(capturedImageFileAddPhotoA);
		this.imagePath = capturedImageFileAddPhotoA.getAbsolutePath();
		return imageUri;
	}

	public String getImagePath() {
		return imagePath;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated f stub
		try {
			Intent newPhotoIntentMainA = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			newPhotoIntentMainA
					.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
			newPhotoIntentMainA.putExtra("return-data", true);
			// Bitmap photo = Media.getBitmap(getContentResolver(),
			// Uri.fromFile(capturedImageFileAddPhotoA) );
			if (newPhotoIntentMainA.resolveActivity(getPackageManager()) != null) {
				Log.v("PackageManager!null", "success");
				startActivityForResult(newPhotoIntentMainA,
						IMAGE_CAPTURE_REQ_CODE);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		photoPreview_addPhotoA = (ImageView) findViewById(R.id.photoPreview_addPhotoXml);
		Log.v("result", "activityresult");
		if (requestCode == IMAGE_CAPTURE_REQ_CODE) {
			if (resultCode == RESULT_OK) {
				Uri selectedImageUriAddPhotoA;
				Log.v("result-correct", "all correct activityresult");

				/* .....When data returns in intent........ */
				if (data != null) {
					Bundle extras = data.getExtras();
					selectedImageUriAddPhotoA = (Uri) extras.get("data");
					Toast.makeText(getApplicationContext(),
							"" + selectedImageUriAddPhotoA, Toast.LENGTH_SHORT)
							.show();
					Log.v("ImagePath", "imagePathError");

					// capturedPhotoBitmap = (Bitmap) extras.get("data");
					// photoPreview_addPhotoA.setImageBitmap(capturedPhotoBitmap);
					// addCaption_addPhotoA.setVisibility(View.VISIBLE);
				}

				/* ...........When data returns in uri specified....... */
			} else
				Log.v("ImagePath", "data-null");
			selectedImagePath = getImagePath();

			Log.v("ImagePath", "after getData");
			capturedPhotoBitmap = BitmapFactory.decodeFile(selectedImagePath);
			photoPreview_addPhotoA.setImageBitmap(capturedPhotoBitmap);
			addCaption_addPhotoA.setVisibility(View.VISIBLE);
			Log.v("ImagePath", "after getCapturedImageInImageView");
		}

		savePhoto_addPhotoA.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * Intent sendCapturedImageIntent = new Intent();
				 * sendCapturedImageIntent
				 * .putExtra("Captured Image",capturedPhotoBitmap);
				 * setResult(SEND_IMAGE_RESULT_CODE, sendCapturedImageIntent);
				 * finish();
				 */

				// Toast.makeText(getApplicationContext(),
				// ""+addCaption_addPhotoA.getText(), Toast.LENGTH_LONG).show();
				Toast.makeText(getApplicationContext(), "" + selectedImagePath,
						Toast.LENGTH_LONG).show();

				DatabaseHandler db = new DatabaseHandler(
						getApplicationContext());
				long newRecordId;
				Log.d("in addphoto save click after db object", "yes...");
				CapturedPhoto cpObj = new CapturedPhoto(addCaption_addPhotoA
						.getText().toString(), selectedImagePath);

				newRecordId = db.addCapturedPhoto(cpObj);

				Log.d("Reading photo entries", "reading getPhoto......");
				Log.d("before sending to getCaptured()", "" + newRecordId);
				CapturedPhoto cp = db.getCapturedPhoto(newRecordId);
				String str = "ID :: " + cp.get_id() + "Caption :: "
						+ cp.getCaption() + "";
				Log.d("entry", str);
				finish();
			}
		});

	}

}

// }

package com.example.photonotes;

import java.util.ArrayList;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint.Cap;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity implements ActionBar.TabListener {

	static final int REQUEST_CODE = 1; // The request code
	static final int SEND_IMAGE_RESULT_CODE = 120;
	int selectedMainA;
	ImageView getPreview_MainA;
	ArrayList<String> MainAPhotoCaptionList;
	DatabaseHandler dbHandle;
	TextView MainAListTxt;
	Tab photoNotesListTabMainA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dbHandle = new DatabaseHandler(this);
		MainAListTxt = (TextView) findViewById(R.id.mainxmlTxt);
		Log.d("Before getAllPhotoCaption", "Yes..here");
		ActionBar actionBarMainA = getActionBar();
		actionBarMainA.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		photoNotesListTabMainA = actionBarMainA.newTab();
		photoNotesListTabMainA.setText("Photo Notes");
		photoNotesListTabMainA.setIcon(R.drawable.ic_action_view_as_list);
		photoNotesListTabMainA.setTabListener(this);
		actionBarMainA.addTab(photoNotesListTabMainA);

		Tab addNewPhotoTabMainA = actionBarMainA.newTab();
		addNewPhotoTabMainA.setText("New Photo Note");
		addNewPhotoTabMainA.setIcon(R.drawable.ic_action_add_to_queue);
		addNewPhotoTabMainA.setTabListener(this);
		actionBarMainA.addTab(addNewPhotoTabMainA);

		Tab addExistingPhotoTabMainA = actionBarMainA.newTab();
		addExistingPhotoTabMainA.setText("Gallery");
		addExistingPhotoTabMainA.setIcon(R.drawable.ic_action_picture);
		addExistingPhotoTabMainA.setTabListener(this);
		actionBarMainA.addTab(addExistingPhotoTabMainA);

		/* .........Database code to show list......... */
		dbHandle.open();
		MainAPhotoCaptionList = dbHandle.getAllPhotoCaption();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, MainAPhotoCaptionList);
		setListAdapter(adapter);
		dbHandle.close();
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photonotes_actions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.photo_searchAction:
			Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.photoNotes_refreshAction:
			Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.add_photoAction:

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if (tab.getText() == "New Photo Note") {

			Intent getNewAddedPhoto = new Intent(this, AddPhoto.class);
			getNewAddedPhoto.putExtra("req_code", REQUEST_CODE);
			startActivityForResult(getNewAddedPhoto, REQUEST_CODE);
			// startActivityForResult(getNewAddedPhoto);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		super.onActivityResult(requestCode, resultCode, data);
		Log.d("In mainAc onActivityResult", "yes..success");

		/* ......Database code to get new updated list............. */
		dbHandle.open();
		Log.d("Before getAllPhotoCaption", "Yes..here");
		MainAPhotoCaptionList = dbHandle.getAllPhotoCaption();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, MainAPhotoCaptionList);
		setListAdapter(adapter);
		dbHandle.close();

	
	}

	public void onListItemClick(ListView parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(parent, view, position, id);
		String captionText = MainAPhotoCaptionList.get(position);
		Intent viewPhotoIntent = new Intent(MainActivity.this,
				ViewPhotoActivity.class);
		viewPhotoIntent.putExtra("SelectedCaption", captionText);
		startActivity(viewPhotoIntent);
	}

}

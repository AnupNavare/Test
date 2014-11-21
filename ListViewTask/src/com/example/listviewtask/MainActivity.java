package com.example.listviewtask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	JSONArray jArray = null;
	private static final String TITLE = "title";
	private static final String TEXT = "text";
	private static final String DATE_CREATED = "created_date";
	private static final String OBJECTS = "objects";
	private static String url = "http://mydeatree.appspot.com/api/v1/public_ideas/";
	ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
	ListView listView;
	TextView listRowTitle;
	TextView listRowText;
	TextView listRowDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.list);
		new processJson().execute(url);
	}

	private class processJson extends AsyncTask<String, String, JSONObject> {
		ProgressDialog pDialog = new ProgressDialog(MainActivity.this);

		@Override
		protected void onPreExecute() {

			super.onPreExecute();

			pDialog.setMessage("Getting Data.......");
			pDialog.setCanceledOnTouchOutside(true);
			pDialog.setIndeterminate(false);
			pDialog.show();
		}

		@Override
		protected JSONObject doInBackground(String... params) {
			// TODO Auto-generated method stub

			JSONParser parser = new JSONParser();
			JSONObject jObject = parser.getJson(url);
			System.out.println("Object::" + jObject);
			return jObject;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();

			JSONObject jObjTemp = new JSONObject();
			try {
				jArray = result.getJSONArray(OBJECTS);
				for (int i = 0; i < jArray.length(); i++) {
					jObjTemp = jArray.getJSONObject(i);
					String getTitle = jObjTemp.getString("title");
					String getText = jObjTemp.getString("text");
					String getDate = jObjTemp.getString("created_date");
					HashMap<String, String> hMapTemp = new HashMap<String, String>();
					hMapTemp.put(TITLE, getTitle);
					hMapTemp.put(TEXT, getText);
					hMapTemp.put(DATE_CREATED, getDate);
					aList.add(hMapTemp);
				}

				ListAdapter MainAListAdapter = new SimpleAdapter(
						getApplicationContext(), aList, R.layout.listrow,
						new String[] { TITLE, TEXT, DATE_CREATED }, new int[] {
								R.id.titleValue, R.id.textValue,
								R.id.date_created_Value });
				listView.setAdapter(MainAListAdapter);

			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sort, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem menuitem) {
		ArrayList<HashMap<String, String>> aListForSorting = new ArrayList<HashMap<String, String>>();
		aListForSorting = aList;
		switch (menuitem.getItemId()) {

		/* ......................To sort by Title................... */
		case R.id.sortByTitle:
			Toast.makeText(MainActivity.this, "Sorted Using Title",
					Toast.LENGTH_LONG).show();
			Collections.sort(aListForSorting,
					new Comparator<HashMap<String, String>>() {

						@Override
						public int compare(HashMap<String, String> lhs,
								HashMap<String, String> rhs) {
							// TODO Auto-generated method stub
							Log.i("lhsName", lhs.get(TITLE));
							// Toast.makeText(MainActivity.this,
							// "lhsName"+lhs.get(TITLE),
							// Toast.LENGTH_LONG).show();
							// Toast.makeText(MainActivity.this,
							// "rhsName"+rhs.get(TITLE),
							// Toast.LENGTH_LONG).show();
							Log.i("rhsName", rhs.get(TITLE));
							return lhs.get(TITLE).compareToIgnoreCase(
									rhs.get(TITLE));
						}
					});
			ListAdapter MainASortByTitle = new SimpleAdapter(
					getApplicationContext(), aListForSorting, R.layout.listrow,
					new String[] { TITLE, TEXT, DATE_CREATED }, new int[] {
							R.id.titleValue, R.id.textValue,
							R.id.date_created_Value });
			listView.setAdapter(MainASortByTitle);
			break;

		/* ......................To sort by oldest Date................... */
		case R.id.sortByDateOldest:
			Toast.makeText(MainActivity.this, "Sorted Using Oldest Date",
					Toast.LENGTH_LONG).show();
			Collections.sort(aListForSorting,
					new Comparator<HashMap<String, String>>() {

						@Override
						public int compare(HashMap<String, String> lhs,
								HashMap<String, String> rhs) {
							// TODO Auto-generated method stub
							Log.i("lhsName", lhs.get(DATE_CREATED));
							Log.i("rhsName", rhs.get(DATE_CREATED));
							return lhs.get(DATE_CREATED).compareTo(
									rhs.get(DATE_CREATED));
						}
					});
			ListAdapter MainASortByDateOldest = new SimpleAdapter(
					getApplicationContext(), aListForSorting, R.layout.listrow,
					new String[] { TITLE, TEXT, DATE_CREATED }, new int[] {
							R.id.titleValue, R.id.textValue,
							R.id.date_created_Value });
			listView.setAdapter(MainASortByDateOldest);
			break;
		/* ......................To sort by latest Date................... */
		case R.id.sortByDateLatest:
			Toast.makeText(MainActivity.this, "Sorted Using Latest Date",
					Toast.LENGTH_LONG).show();
			Collections.sort(aListForSorting,
					new Comparator<HashMap<String, String>>() {

						@Override
						public int compare(HashMap<String, String> lhs,
								HashMap<String, String> rhs) {
							// TODO Auto-generated method stub
							Log.i("lhsName", lhs.get(DATE_CREATED));
							Log.i("rhsName", rhs.get(DATE_CREATED));
							return rhs.get(DATE_CREATED).compareTo(
									lhs.get(DATE_CREATED));
						}
					});
			ListAdapter MainASortByDateLatest = new SimpleAdapter(
					getApplicationContext(), aListForSorting, R.layout.listrow,
					new String[] { TITLE, TEXT, DATE_CREATED }, new int[] {
							R.id.titleValue, R.id.textValue,
							R.id.date_created_Value });
			listView.setAdapter(MainASortByDateLatest);
			break;
		}
		return false;
	}

}

package com.example.searchevent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

//import com.example.photonotes.R;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

//@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SearchActivity extends ListActivity implements TabListener{

	static String URL; 
	static final String KEY_CITY = "city";
	static final String KEY_EVENTID = "id";
	static final String KEY_EVENT = "event";
	static final String KEY_NAME = "name";
	static final String KEY_CATEGORY = "category";

	ArrayList<HashMap<String, String>> eventList = new ArrayList<HashMap<String, String>>();
	
	Tab listViewTab;
	Tab mapViewTab;
	String getCity;
	String fullname;
	
	//@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_search);
		
		Intent getData = getIntent();
		getCity = getData.getStringExtra("city");
		
		/*........splitting string...........*/
		String[] splitStr = getCity.split(" ");
		String firstpart = splitStr[0];
		String secondpart = splitStr[1];
		fullname = firstpart + "+" + secondpart;
		
		System.out.println("in searchActivity"+getCity);
		URL = "https://www.eventbrite.com/xml/event_search?city=" + fullname + "&app_key=PKBYSGP46VMJW6ONXR";
		ActionBar actionBarMainA = getActionBar();
		actionBarMainA.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		listViewTab = actionBarMainA.newTab();
		listViewTab.setText("List View");
		listViewTab.setIcon(R.drawable.ic_action_view_as_list);
		listViewTab.setTabListener(this); 
		actionBarMainA.addTab(listViewTab);

		Bitmap mapBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.mapview);
		Bitmap scaledBitmap = Bitmap
				.createScaledBitmap(mapBitmap, 50, 50, true);
		Drawable mapDrawable = new BitmapDrawable(getResources(), scaledBitmap);
		mapViewTab = actionBarMainA.newTab();
		mapViewTab.setText("Map View");
		mapViewTab.setIcon(mapDrawable);
		mapViewTab.setTabListener(this); 	
		actionBarMainA.addTab(mapViewTab,false);

		XmlParseTask parseTask = new XmlParseTask(this);
		parseTask.execute(URL);
			}
	
	private class XmlParseTask extends AsyncTask<String, Void, String>{
	private Activity context;
	
		public XmlParseTask(Activity context){
			this.context = context;
		}
	
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String xml = null;
			for(String cUrl : params){
				xml = getXMLFromUrl(cUrl);	
			}
			return xml;
		}
		
		@TargetApi(Build.VERSION_CODES.FROYO)
		@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				
				super.onPostExecute(result);
				//System.out.println("in onPostExecute");
				//System.out.println(result);
				SearchEventXMLParser parser = new SearchEventXMLParser();
				//String xml = parser.getXMLFromUrl(URL); // getting XML
				Document doc = parser.getDomElement(result); // getting DOM element

				NodeList n1 = doc.getElementsByTagName(KEY_EVENT);
				for(int i = 0; i < n1.getLength(); i++){
					HashMap<String, String> hMap = new HashMap<String, String>();
					Element element = (Element)n1.item(i);
					String test = n1.item(i).getTextContent();
					//System.out.println(test);
					//.....adding each child node to hashmap.......
					hMap.put(KEY_NAME, parser.getValue(element, KEY_NAME));
					hMap.put(KEY_EVENTID, parser.getValue(element, KEY_EVENTID));
					hMap.put(KEY_CATEGORY, parser.getValue(element, KEY_CATEGORY));
					eventList.add(hMap);
				}
				ListAdapter eventListAdapter = new SimpleAdapter(SearchActivity.this, eventList	,
		                R.layout.listrow,
		                new String[] { KEY_EVENTID, KEY_NAME, KEY_CATEGORY }, new int[] {
		                        R.id.eventId, R.id.eventname,R.id.eventcategory });
					setListAdapter(eventListAdapter);
				

			}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
	
	private String getXMLFromUrl(String url){
		String xml = null;
		try {
            // defaultHttpClient
            
			DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity);
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return XML
		//System.out.println(xml);
		return xml;
        
    }

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		if(tab.getText() == "Map View"){
			Intent i = new Intent(SearchActivity.this,EventMapView.class);
			i.putExtra("fullname", fullname);
			startActivity(i);
		}
		/*if(tab.getText() == "List View"){
			Intent i = new Intent(SearchActivity.this,SearchActivity.class);
			startActivity(i);
		}*/
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}


}

package com.example.searchevent;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

//import android.app.Fragment;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class EventMapView extends MapActivity{
	MapView mapView;
	GoogleMap map;
	
	static String URL; 
	static final String KEY_CITY = "city";
	static final String KEY_VENUE = "venue";
	static final String KEY_LATITUDE = "latitude";
	static final String KEY_LONGITUDE = "longitude";
	static final String KEY_ADDRESS = "address";
	static final String KEY_EVENT = "event";
	static final String KEY_NAME = "name";

	EventOverlay eventOverlay;
	List<Overlay> mapOverlays;
	String getCity;
	ArrayList<OverlayItem> eventLocationOverlayItem = new ArrayList<OverlayItem>();
	Button backBtn;
	Tab listViewTab,mapViewTab;
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapviewxml);

		Intent getData = getIntent();
		getCity = getData.getExtras().getString("fullname");
		backBtn = (Button)findViewById(R.id.backBtn);
		
		System.out.println("in searchActivity"+getCity);
		URL = "https://www.eventbrite.com/xml/event_search?city=" + getCity + "&app_key=PKBYSGP46VMJW6ONXR";
		
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		
		/* ...........Getting bitmap......... */
		Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),
				R.drawable.loc);

		/* ...........Add overlay items........... */
		mapOverlays = mapView.getOverlays();
		
		/* ............scaling bitmap.......... */
		Bitmap scaledBitmap = Bitmap
				.createScaledBitmap(bitmapOrg, 40, 40, true);
		/* ..........casted from bitmap to drawable.......... */
		Drawable drawable = new BitmapDrawable(getResources(), scaledBitmap);
		eventOverlay = new EventOverlay(drawable, this);

		/*
		 * ........GeoPoint coordinates are specified in microdegrees (degrees *
		 * 1e6)...
		 */
		XmlParseTask parseTask = new XmlParseTask(this);
		parseTask.execute(URL);
		
	}

		@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
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
			
			@Override
			protected void onPostExecute(String xml) {
				// TODO Auto-generated method stub
				
				super.onPostExecute(xml);

				SearchEventXMLParser parser = new SearchEventXMLParser();
				Document doc = parser.getDomElement(xml); // getting DOM element

				NodeList n1 = doc.getElementsByTagName(KEY_VENUE);
				String[] address = new String[n1.getLength()];
				 String[] eventname = new String[n1.getLength()];
				 int[] lat = new int[n1.getLength()];
					int[] longi = new int[n1.getLength()];
					
				for(int i = 0; i < n1.getLength(); i++){
					//System.out.println(n2);
					
					HashMap<String, String> hMap = new HashMap<String, String>();
					Element element = (Element)n1.item(i);
					//String test = n1.item(i).getTextContent();
					//System.out.println("Address" + parser.getValue(element,KEY_ADDRESS));
					double lati = Double.valueOf(parser.getValue(element, KEY_LATITUDE));
					lat[i] = (int)lati;
					double longitude = Double.valueOf(parser.getValue(element, KEY_LONGITUDE));
					longi[i] = (int)longitude;
					
					 
					 address[i] = parser.getValue(element, KEY_ADDRESS);
					 eventname[i] = parser.getValue(element, KEY_NAME);
					 /*
					GeoPoint geoPoint = new GeoPoint(latTemp, longTemp);
					eventLocationOverlayItem = new OverlayItem(geoPoint, nameTemp,
							addressTemp);
					*/
				}
				GeoPoint[] geopoint = new GeoPoint[n1.getLength()];
				try{
				for(int j = 0; j < n1.getLength(); j++){
					System.out.println("inside for loop");
					if(address[j] != null && eventname[j] != null){
						
						System.out.println("Adress -> " + address[j]);
						geopoint[j] = new GeoPoint(lat[j], longi[j]);
						//eventLocationOverlayItem = new OverlayItem(geopoint[j], eventname[j],
							//	address[j]);
						eventLocationOverlayItem.add(new OverlayItem(geopoint[j], eventname[j],	address[j]));
						//eventOverlay.addOverlay(eventLocationOverlayItem[j]);*/
						//mapOverlays.add(eventOverlay);
					}
				}
				}catch(NullPointerException e){
					e.printStackTrace();
				}
				
				eventOverlay.addOverlayList(eventLocationOverlayItem);
				mapOverlays.add(eventOverlay);
				
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
				return xml;
		        
		    }

	}

}

package com.example.searchevent;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class EventOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	Context mActivityContext;
	private GeoPoint geoPoint = null;
	/*public EventOverlay(Drawable d) {
		super(boundCenterBottom(d));
		// TODO Auto-generated constructor stub
	}*/
	
	/* second constructor to handle touch events on the overlay items. */
	public EventOverlay(Drawable defaultMarker, Context context){
		super(boundCenterBottom(defaultMarker));
		mActivityContext = context;
	}

	/*
	 * When the populate() method executes, it will call createItem in the
	 * ItemizedOverlay to retrieve each OverlayItem
	 */
	@Override
	protected OverlayItem createItem(int index) {
		// TODO Auto-generated method stub
		return mOverlays.get(index);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlays.size();
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		/*
		 * call for populate() will read each of the OverlayItem objects and
		 * prepare them to be drawn.
		 */
		populate();
	}
	public void addOverlayList(List<OverlayItem> overlayitems) {
	    
		Object temp[] = overlayitems.toArray();
	    try{
	        for(int i = 0;i<temp.length;i++)
	        {
	            mOverlays.add((OverlayItem)temp[i]);
	        }
	    }catch(Error e)
	    {
	        Toast.makeText(mActivityContext, "Something happened when adding the overlays:"+e.getMessage() ,
	                Toast.LENGTH_LONG).show();
	    }

	    populate();
	    }
	
		
	/*
	 public void addOverlayItem(OverlayItem overlayItem) {
	        mOverlays.add(overlayItem);
	        populate();
	    }
	 
	 public void clear() {
	        mOverlays.clear();
	        populate();
	    }

	    public void addOverlayItem(int lat, int lon, String title) {
	        try {
	            geoPoint = new GeoPoint(lat, lon);
	            OverlayItem overlayItem = new OverlayItem(geoPoint, title, null);
	            addOverlayItem(overlayItem);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	*/
	@Override
	protected boolean onTap(int location) {
		// TODO Auto-generated method stub
		System.out.println("now inside ontap");
		OverlayItem item = mOverlays.get(location);
		System.out.println("item"+ mOverlays.get(location));
		  AlertDialog.Builder dialog = new AlertDialog.Builder(mActivityContext);
		  dialog.setTitle(item.getTitle());
		  dialog.setMessage(item.getSnippet());
		  dialog.show();
		return true;
	}

}

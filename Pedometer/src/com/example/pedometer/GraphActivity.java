package com.example.pedometer;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class GraphActivity extends Activity{
	public enum day {
		MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY
	} 
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.graphs);

	        LinearLayout graphLayout = (LinearLayout) findViewById(R.id.calorieGraph);
	        // init example series data
	        //int i = day.MONDAY.ordinal();
	        
	        GraphViewSeries calorieSeries = new GraphViewSeries(new GraphViewData[] {
	                new GraphViewData(day.MONDAY.ordinal(),50.0d)
	                , new GraphViewData(day.TUESDAY.ordinal(), 40.0d)
	                , new GraphViewData(day.WEDNESDAY.ordinal(), 60.0d) // another frequency
	                , new GraphViewData(day.THURSDAY.ordinal(), 30.0d)
	                , new GraphViewData(day.FRIDAY.ordinal(), 70d)
	                
	        });
	        
	        GraphView graphView;
	        graphView = new BarGraphView(this, "weekly Analysis for March");
	        ((BarGraphView) graphView).setDrawValuesOnTop(true);
	        graphLayout.addView(graphView);
	        graphView.addSeries(calorieSeries);
	 }
}
	 

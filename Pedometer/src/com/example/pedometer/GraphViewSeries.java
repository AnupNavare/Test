package com.example.pedometer;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewDataInterface;
import com.jjoe64.graphview.ValueDependentColor;

public class GraphViewSeries {
	
		/**
		* graph series style: color and thickness
		*/
		static public class GraphViewSeriesStyle {
		public int color = 0xff0077cc;
		public int thickness = 3;
		private ValueDependentColor valueDependentColor;

		public GraphViewSeriesStyle() {
		super();
		}
		public GraphViewSeriesStyle(int color, int thickness) {
		super();
		this.color = color;
		this.thickness = thickness;
		}

		public ValueDependentColor getValueDependentColor() {
		return valueDependentColor;
		}

		/**
		* the color depends on the value of the data.
		* only possible in BarGraphView
		* @param valueDependentColor
		*/
		public void setValueDependentColor(ValueDependentColor valueDependentColor) {
		this.valueDependentColor = valueDependentColor;
		}
		}

		final String description;
		final GraphViewSeriesStyle style;
		GraphViewDataInterface[] values;
		private final List<GraphView> graphViews = new ArrayList<GraphView>();

		public GraphViewSeries(GraphViewDataInterface[] values) {
		Log.i("in graphview para cons","yes reached");
		description = null;
		style = new GraphViewSeriesStyle();
		this.values = values;
		}

		/*public GraphViewSeries(String description, GraphViewSeriesStyle style, GraphViewDataInterface[] values) {
		super();
		this.description = description;
		if (style == null) {
		style = new GraphViewSeriesStyle();
		}
		this.style = style;
		this.values = values;
		}*/

		/**
		* this graphview will be redrawn if data changes
		* @param graphView
		*/
		public void addGraphView(GraphView graphView) {
		this.graphViews.add(graphView);
		}

		

		
}

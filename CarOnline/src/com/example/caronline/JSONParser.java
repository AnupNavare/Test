package com.example.caronline;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

public class JSONParser {

	Context context;

	public List<Cardata> JSONPParsing(Context context) {

		JSONArray contacts = null;
		String jsonStr = null;
		List<Cardata> listOfData = new ArrayList<Cardata>();
		try {

			InputStream is = context.getResources().openRawResource(
					R.raw.cardata);

			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			jsonStr = new String(buffer, "UTF-8");

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);

					// Getting JSON Array node
					contacts = jsonObj.getJSONArray(Constants.TAG_OBJECTS);

					for (int i = 0; i < contacts.length(); i++) {
						JSONObject object = contacts.getJSONObject(i);

						int year_of_mfr = object
								.getInt(Constants.TAG_YEAR_OF_MFR);
						String carModel = object.getString(Constants.TAG_MODEL);
						int mileage = object.getInt(Constants.TAG_MILEAGE);
						String location = object
								.getString(Constants.TAG_LOCATION);
						int price = object.getInt(Constants.TAG_PRICE);

						Cardata data = new Cardata();
						data.setYearOfManufacture(year_of_mfr);
						data.setCarModel(carModel);
						data.setMileage(mileage);
						data.setLocation(location);
						data.setPrice(price);
						listOfData.add(data);
					}
				} catch (Exception e) {
					e.printStackTrace();

				}
				return listOfData;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

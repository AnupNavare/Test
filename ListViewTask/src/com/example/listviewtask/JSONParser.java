package com.example.listviewtask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	static JSONObject jObj = null;
	static InputStream iStream = null;
	static String jsonStr = "";

	public JSONParser() {

	}

	public JSONObject getJson(String Url) {
		/* .................get data from url in string............... */
		try {
			Log.i("URL", Url);
			HttpClient hClient = new DefaultHttpClient();
			HttpGet hGet = new HttpGet(
					"http://mydeatree.appspot.com/api/v1/public_ideas/");
			
			HttpResponse hResponse = hClient.execute(hGet);
			// System.out.println(hResponse);
			// String respond = hResponse.getStatusLine().getReasonPhrase();
			// System.out.println(respond);

			System.out.println("Responselength::"
					+ hResponse.getEntity().getContentLength());
			HttpEntity hEntity = hResponse.getEntity();
			iStream = hEntity.getContent();
			// Log.i("istream", iStream.toString());
			// Scanner scanner = new Scanner(iStream).useDelimiter("\\A");
			/*
			 * while(scanner.hasNext()){ String responseContent =
			 * scanner.next(); System.out.println(responseContent); }
			 */
			String responseContent = new Scanner(iStream).useDelimiter("\\A")
					.next();
			System.out.println("ResponseC" + responseContent);
			// jsonStr = hEntity.getContent().toString();
			/*StatusLine statusLine = hResponse.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			System.out.println(statusCode);
			if (statusCode == 200) {
				System.out.println("statuscode=200");

				BufferedReader br = new BufferedReader(new InputStreamReader(
						iStream));
				System.out.println("Buffered" + br);
				StringBuilder sb = new StringBuilder();
				String currentLine = null;
				System.out.println("brrr.." + br.readLine());
				/*
				 * while((currentLine = br.readLine())!=null){
				 * System.out.println("CurrentLine"+currentLine);
				 * Log.i("CurrentLine", currentLine); sb.append(currentLine +
				 * "n");
				 * 
				 * }
				 */
				iStream.close();
				// jsonStr = sb.toString();
				jsonStr = responseContent;
				System.out.println("JSONStr..." + jsonStr);
			}
		 catch (UnsupportedEncodingException e) {
			System.out.println("unsupported");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			System.out.println("clientprotocol");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ioexception");
			e.printStackTrace();
		}

		/* ...................get data from string into json object............. */
		/*
		 * try{ BufferedReader br = new BufferedReader(new
		 * InputStreamReader(iStream,"iso-8859-1"), 8); StringBuilder sb = new
		 * StringBuilder(); String currentLine = null; while((currentLine =
		 * br.readLine())!=null){ Log.i("CurrentLine", currentLine);
		 * sb.append(currentLine + "n"); } iStream.close(); jsonStr =
		 * sb.toString(); } catch(Exception e){ Log.e("Buffer Error",
		 * "Error converting result " + e.toString()); }
		 */

		try {
			Log.i("JSONStr", jsonStr);
			jObj = new JSONObject(jsonStr);

		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		return jObj;
	}
}

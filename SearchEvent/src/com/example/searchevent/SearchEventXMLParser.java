package com.example.searchevent;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import android.provider.DocumentsContract.Document;
import android.renderscript.Element;
import android.util.Log;

public class SearchEventXMLParser {
	/*
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public String getXMLFromUrl(String url){
		String xml = null;
		try {
			
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

			StrictMode.setThreadPolicy(policy); 
			
			
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
        return xml;
    }
	*/
	public org.w3c.dom.Document getDomElement(String xml){
        org.w3c.dom.Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
 
            DocumentBuilder db = dbf.newDocumentBuilder();
 
            InputSource is = new InputSource(new StringReader(xml));
            
                //is.setCharacterStream(new StringReader(xml));
            System.out.println(is);
                doc = db.parse(is);
 
            } catch (ParserConfigurationException e) {
                //Log.e("Error: ", e.getMessage());
                return null;
            } catch (SAXException e) {
                //Log.e("Error: ", e.getMessage());
                return doc;
            } catch (Exception e) {
            	e.printStackTrace();
                //Log.e("Error: ", e.getMessage());
                return doc;
            }
                // return DOM
            return doc;
    }
	
	public String getValue(org.w3c.dom.Element item, String str) {     
	    NodeList n = item.getElementsByTagName(str);
	   
	    	 return this.getElementValue(n.item(0));
	}
	 
	public final String getElementValue( Node elem ) {
	         Node child;
	         if( elem != null){
	             if (elem.hasChildNodes()){
	                 for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
	                     if( child.getNodeType() == Node.TEXT_NODE  ){
	                         return child.getNodeValue();
	                     }
	                 }
	             }
	         }
	         return "";
	  } 
	
}

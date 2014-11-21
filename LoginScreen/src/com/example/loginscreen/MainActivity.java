package com.example.loginscreen;

//import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.app.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.*;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	final String Twitter_consumer_key = "1hW3M2FRGmnaoHDBv0tA5Q"; 
    final String Twitter_consumer_secret = "reOoiAUTTnQ2hEsFlpv98wTkzPwx4VQAuNRL3g5Ts";
    
    String PREFERENCE_NAME = "twitter_oauth";
    final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
    final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
    final String PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";
	
    //twitter variables
    private static Twitter twitter;
    private static RequestToken requestToken;
    
    static final String TWITTER_CALLBACK_URL = "oauth://t4jsample";
    
 // Twitter oauth urls
    static final String URL_TWITTER_AUTH = "auth_url";
    static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
    static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";
 
    SharedPreferences sharedPreferences;
    ConnectionDetector cd;
    Button redirect_twitter; 
    
    public void onResume(Bundle savedInstanceState){
    	Log.d("Test","onResume");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    		
    	super.onCreate(savedInstanceState);
    	Log.e("Anup","OnCreat!!!!************************");
        setContentView(R.layout.activity_main);
        redirect_twitter = (Button)findViewById(R.id.twitter_btn);
    	sharedPreferences = this.getSharedPreferences("twitterLoginPreference",MODE_PRIVATE);
        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
        redirect_twitter.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					//loginToTwitter();
					LoginThread loginThread = new LoginThread();
					loginThread.start();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
        MyThread myThread = new MyThread();
        myThread.start();
    }  
    
    class LoginThread extends Thread{
    	public void run(){
    		Log.d(TAG,"logging in using another thread");
    		try {
				loginToTwitter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    class MyThread extends Thread{
    	public void run(){
    		Log.d(TAG,"Performing network operations on separate thread and not on main thread");
    	    try{ 
    		if (!isTwitterLoggedInAlready()) {
    	            Uri uri = getIntent().getData();
    	            if (uri != null && uri.toString().startsWith(TWITTER_CALLBACK_URL)) {
    	                // oAuth verifier
    	                String verifier = uri
    	                        .getQueryParameter(URL_TWITTER_OAUTH_VERIFIER);
    	     
    	                try {
    	                    // Get the access token
    	                    AccessToken accessToken = twitter.getOAuthAccessToken(
    	                            requestToken, verifier);
    	                    
    	                    // Shared Preferences
    	                    Editor e = sharedPreferences.edit();
    	     
    	                    // After getting access token, access token secret
    	                    // store them in application preferences
    	                    e.putString(PREF_KEY_OAUTH_TOKEN, accessToken.getToken());
    	                    e.putString(PREF_KEY_OAUTH_SECRET,
    	                            accessToken.getTokenSecret());
    	                    // Store login status - true
    	                    e.putBoolean(PREF_KEY_TWITTER_LOGIN, true);
    	                    e.commit(); // save changes
    	     
    	                    Log.e("Twitter OAuth Token", "> " + accessToken.getToken());
    	                    
    	                    redirect_twitter.setVisibility(View.GONE);
    	                }
    	                catch (Exception e) {
    	                    // Check log for login errors
    	                    Log.e("Twitter Login Error", "> " + e.getMessage());
    	                }
    	  }}
    	    }
    	    catch(Exception e){
    	    	Log.e("Twitter Login Error", "> " + e.getMessage());
    	    }
    	}
    	
    }
   
 
    private void loginToTwitter() throws IOException{
    	//check if already logged in
    	if(!isTwitterLoggedInAlready()){
    		ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setOAuthConsumerKey(Twitter_consumer_key);
            builder.setOAuthConsumerSecret(Twitter_consumer_secret);
            Configuration configuration = builder.build();
             
            TwitterFactory factory = new TwitterFactory(configuration);
            twitter = factory.getInstance();
            Log.d("anup","Test****");
            try {
                requestToken = twitter
                        .getOAuthRequestToken(TWITTER_CALLBACK_URL);
                this.startActivity(new Intent(Intent.ACTION_VIEW, Uri
                        .parse(requestToken.getAuthenticationURL())));
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        } 
    	
    	else {
            // user already logged into twitter
            Toast.makeText(getApplicationContext(),
                    "Already Logged into twitter", Toast.LENGTH_LONG).show();
        }
    }
    	
    
    private boolean isTwitterLoggedInAlready(){
		
    	return sharedPreferences.getBoolean(PREF_KEY_TWITTER_LOGIN, false);
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}

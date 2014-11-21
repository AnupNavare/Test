				EVENTSEARCH - Using EventBrite API

	This application is meant to take location as a keyword and shows events in list and their 	location in map.
	
	This application uses Google Maps for showing the location on map and has to be configured 	with the project to successfully run it.

					Setting your Application
	
1. Download and configure Google Play Services package from Android SDK Manager.
2. Attach maps.jar under Google add-ons folder to your project.
3. Include library in manifest file as <uses-library android:name="com.google.android.maps"/>
4. Add permissions as <uses-permission android:name="android.permission.INTERNET"/>
5. Obtain Google API v2 key using following link
https://developers.google.com/maps/documentation/android/start
6. Include the app_key in manifest.xml file.
7. Get an official Eventbrite developer App Key which will be used to search events.
8. Now shoot the application.

					Supported Android Version

1. Minimum SDK Version :: 11
2. Target SDK Version :: 18

					Libraries Referenced
1. google-play-services.jar
2. maps.jar

					Shortcoming
1. Loading of activity takes time due to XML Parsing.
2. ListView dont show name and other details if not specified in XML page.
  
					Tasks Accomplished
1. Parsed Evetbrite eventsearch XML using DOM parser and shown results in listview
2. Listview shows EventID, EventName and category if specified
3. Takes input as location to find events in that particular location.
4. Use of  AsyncTask for long running processes to avoid blocking execution of Main UI thread.
5.  Use of Modern Action Bar Navigation Tabs. 
6. Followed Design standards.
7. Validation for empty input string for city.

					Problems Faced
1. How to use the eventbrite API's. This was solved by getting the app key and parsing xml file in android.
2. Best way to show both ListView and MapView. I achieved by using Navigation tabs for both the activities.
3. Way to show location. Fused Location Provider was not appropriate as longitude and latitude were already known and main point was to show the exact location given latitude and longitude.  This was accomplished by MapView.

				Task Not Accomplished
1. MapView not showing all overlay items. As along with ListView I was trying to show location in map by sending longitude and latitude using GeoPoint, it is unable to populate multiple overlay items.
2. The data is perfectly parsed and is stored properly into list but there is some issue while populating multiple markers inside ItemizedOverlay class.
3. I am storing list of overlayitems and passing it to ItemizedOverlay class before making call to populate but still it is taking only last overlayitem.
4. The only problem is from where to call populate() as of now it is overwriting previous overlay items. I tried calling populate() outside addOverlayist() but didn't worked.
5. I tried hard to show this extra feature along with ListView and assure to fix the bug if I get some time as I know what is going wrong. 

				Tested Locations(city)
Letter cases are ignored
1. san francisco
2. santa clara
3. los angeles


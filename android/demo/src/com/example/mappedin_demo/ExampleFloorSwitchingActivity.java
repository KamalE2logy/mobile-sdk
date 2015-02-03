package com.example.mappedin_demo;

import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.mappedin.api.MappedInAPI;
import com.mappedin.api.objects.MappedInLocation;
import com.mappedin.api.objects.MappedInMap;
import com.mappedin.api.objects.MappedInNode;
import com.mappedin.sdk.MappedInMapActivityConfiguration;
import static com.example.mappedin_demo.Constants.MAPPEDIN_API_SECRET;
import static com.example.mappedin_demo.Constants.MAPPEDIN_SHOPPING_ID;

/**
 * In this activity you will learn how to change floors in a multi floor venue.
 * </br></br>
 * By default MappedIn Map loads the default map specified in the Venue data. 
 * However if there is no default map specified, then it will load the very first map it gets from the server.
 * 
 * @author Hassaan Aamir
 *
 */
public class ExampleFloorSwitchingActivity extends ExampleBaseActivity implements ActionBar.OnNavigationListener {

	private final MappedInMapActivityConfiguration mConfiguration = new MappedInMapActivityConfiguration();
	
	/**
	 * You can now use this API object to get data from MappenIn servers.
	 */
	private MappedInAPI mAPI;
	
	private ArrayList<MappedInMap> mMaps;
	
	/**
	 * Example of how to set the various configuration settings needed by <code>MappedInMapActivity</code> to start up properly.
	 * </br></br>
	 * Here we are passing the MappedIn API object instead of a secret key. 
	 * This or a secret key is needed by the <code>MappedInMapActivity</code> to work properly.
	 * </br></br>
	 * A MappedIn demo venue is also being set to load automatically.
	 * </br></br>
	 * To learn more about what other configuration setting you can modify and/or use
	 * @see com.mappedin.sdk.MappedInMapActivityConfiguration
	 */
	public ExampleFloorSwitchingActivity() {
		super();
		
		mConfiguration.setVenueToLoad(MAPPEDIN_SHOPPING_ID, this);
	}

	@Override
	protected MappedInMapActivityConfiguration getMappedInMapActivityConfiguration() {
		mConfiguration.setMappedInAPI(mAPI);
		return mConfiguration;
	}
	
	/**
	 * Instead of creating a API object inside an activity's onCreate like in this sample code below, 
	 * you should try and create it statically in your app's Applications class. 
	 * This way you can reuse your API object throughout the app.
	 * </br></br>
	 * Example:
	 * <p><blockquote><pre>
	 * public class DemoApp extends Application {
	 *
	 *    private static MappedInAPI mAPI;
	 *	
	 *    public onCreate() {
	 *       mAPI = new MappedInAPI(this, MAPPEDIN_API_SECRET_KEY);
	 *    }
	 *
	 *    public static MappedInAPI getAPI() {
	 *       return mAPI;
	 *    }
	 * }
	 * </pre></blockquote><p>
	 * Then you can call the API object from your activities simply by call: <code>DemoApp.getAPI()</code>
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		mAPI = new MappedInAPI(this, MAPPEDIN_API_SECRET);
		
		super.onCreate(savedInstanceState);

		/**
		 * If a venue already exists, which means that we are simply recovering from a configuration change or sleep,
		 * then setup the ActionBar navigation list immediately as the floor data is already available 
		 * and we do not have to wait for onVenueLoadingSuccess() as it will not be called anyway.
		 */
		if (getVenue() != null) {
			setupActionbarDropdown();
		}
	}
	
	/**
	 * Here we are handling the floor switching logic. When a user selects a floor from the ActionBar dropdown menu,
	 * it will get processed here.
	 */
	@Override
	public boolean onNavigationItemSelected(int position, long id) {

		// Get the map object the user selected
		MappedInMap map = mMaps.get(position);
		
		// Make the map load the new map
		showMap(map);
		
		return true;
	}
	
	/**
	 * Method to set up the action bar to show a dropdown list of all the floors available to switch between 
	 */
	private void setupActionbarDropdown() {
		
		// Save the list of maps so we can reuse them in the <code>onNavigationItemSelected()</code> callback method
		mMaps = getAllMaps();
		
		// No need to show a list to switch floors between if there is only 1 floor in this venue
		if (mMaps.size() <= 1) {
			return;
		}
		
		/**
		 * Here we are going to be using the ActionBar navigation list to show our floor switching UI.
		 * You can use any type of UI that suits your app's theme to handle floor switching.
		 */
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		
		ArrayList<String> mapNames = new ArrayList<String>();
		
		int i = 0, position = 0;
		
		// Here we are building a list of names for the floor that will be displayed in the ActionBar dropdown list
		for (MappedInMap map : mMaps) {
			String name = map.getShortName();

			if (!TextUtils.isEmpty(name)) {
				name += " - ";
			}

			name += map.getName();

			mapNames.add(name);
			
			// Saving the current map location so we can set the initial selected navigation item properly
			if (getCurrentMap().equals(map)) {
				position = i;
			}
			
			i++;
		}
		
		// Creating a simple array adapter of all the floor names that the user can select and switch between
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mapNames);
		
		// Setting the floor name adapter and setting this activity as the callback
		actionBar.setListNavigationCallbacks(adapter, this);
		
		// Setting the current map as the selected floor in the navigation list
		actionBar.setSelectedNavigationItem(position);
	}

	/**
	 * Example of how to listen to the various callbacks from the MappedIn Map when it is loading venue
	 * 
	 * @see com.mappedin.sdk.interfaces.VenueLoadingCallback
	 */
	@Override
	public void onVenueLoadingStarted() {
		super.onVenueLoadingStarted();
		
		// The venue is being loaded
		Toast.makeText(getApplicationContext(), "MappedIn Mall is being loaded...", Toast.LENGTH_SHORT).show();
	}
		
	@Override
	public void onVenueLoadingSuccess() {
		super.onVenueLoadingSuccess();
		
		// The venue finished loading all needed resources.
		Toast.makeText(getApplicationContext(), "MappedIn Mall loaded successfully!", Toast.LENGTH_LONG).show();
		
		setupActionbarDropdown();
	}
	
	@Override
	public void onVenueLoadingFail() {
		super.onVenueLoadingFail();
		
		// Internet probably went down or the data from the MappedIn servers is corrupted or something else bad happened while loading the venue :/
		Toast.makeText(getApplicationContext(), "An error occured while loading the venue...", Toast.LENGTH_LONG).show();
	}

	/**
	 * Example of how to handle a user click on the directions button that is shown in the Sliding Up Panel with the location details.
	 * Run this app in your Android device or emulator to see what I mean.
	 * </br></br>
	 * Here we are handling the button click by showing a <code>Toast</code> message about the user clicking the button,
	 * and then calling the <code>loadDirections</code> method to make the Map load and start displaying directions on the screen.
	 * 
	 * @see com.mappedin.sdk.MappedInMapActivity#loadDirections
	 */
	@Override
	public void onDirectionButtonClick(MappedInLocation location, MappedInNode node) {
		Toast.makeText(this, "You want to get direction to " + location.getName(), Toast.LENGTH_SHORT).show();
		
		MappedInLocation origin;
		
		// Picking a random location from all the maps in this multi level building as our origin to show you how to display directions within the map
		Iterator<MappedInLocation> list = getAllLocations().iterator();
		while (list.hasNext()) {
			
			origin = list.next();
			
			if (origin.getLocationId().equals(location.getLocationId()) == false) {
				loadDirections(origin, origin.getNodes().values().iterator().next(), location, node, null);
				
				break;
			}
		}
	}
}

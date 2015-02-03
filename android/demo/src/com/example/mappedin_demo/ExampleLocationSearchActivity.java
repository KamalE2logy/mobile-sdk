package com.example.mappedin_demo;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.widget.Toast;

import com.mappedin.api.objects.MappedInLocation;
import com.mappedin.api.objects.MappedInNode;
import com.mappedin.sdk.MappedInMapActivityConfiguration;

import static com.example.mappedin_demo.Constants.MAPPEDIN_API_SECRET;
import static com.example.mappedin_demo.Constants.MAPPEDIN_MALL_ID;

/**
 * In this activity you will learn how to view, search and filter locations available within the map.
 * </br></br>
 * This sample is divided into two parts. First part is this main <code>ExampleLocationSearchActivity</code> activity. 
 * Second part is the <code>ExampleListFragment</code> fragment which handles all the logic for displaying and searching the various locations in the map.
 * </br></br>
 * You will also learn how manage the back button callback before the activity closes.
 * 
 * @author Hassaan Aamir
 *
 */
public class ExampleLocationSearchActivity extends ExampleBaseActivity implements TabListener, ExampleListActivityInterface {

	// Setting up constants that will be used throughout this sample activity
	private static final String TAG_LOCATIONS_LIST = "TAG_LOCATIONS_LIST", KEY_TAB_POSITION = "KEY_TAB_POSITION";
	
	/**
	 * Setting up the index values of the "Map" and "List" tabs.
	 */
	public static final int TAB_MAP = 0, TAB_LIST = 1;
	
	private final MappedInMapActivityConfiguration mConfiguration = new MappedInMapActivityConfiguration();
	
	private ExampleListFragment mList;
	
	/**
	 * Example of how to set the various configuration settings needed by <code>MappedInMapActivity</code> to start up properly.
	 * </br></br>
	 * Here we are passing the MappedIn Secret key which is needed to make <code>MappedInMapActivity</code> work properly.
	 * We can also pass our own <code>MappedInAPI</code> object without having to set a secret key.
	 * </br></br>
	 * To learn more about what other configuration setting you can modify and/or use
	 * @see com.mappedin.sdk.MappedInMapActivityConfiguration
	 */
	public ExampleLocationSearchActivity() {
		super();
		mConfiguration.setMappedInAPISecret(MAPPEDIN_API_SECRET);
		mConfiguration.setVenueToLoad(MAPPEDIN_MALL_ID, this);
	}

	@Override
	protected MappedInMapActivityConfiguration getMappedInMapActivityConfiguration() {
		return mConfiguration;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mList = (ExampleListFragment) getSupportFragmentManager().findFragmentByTag(TAG_LOCATIONS_LIST);
		
		/**
		 * If a venue already exists, which means that we are simply recovering from a configuration change or sleep,
		 * so we can immediately setup the ActionBar tabs as the venue data is already available 
		 * and we do not have to wait for onVenueLoadingSuccess() as it will not be called anyway.
		 */
		if (getVenue() != null) {
			setupActionBarTabs();
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		if (getSupportActionBar().getTabCount() > 0) {
			// Save the current tab so that we can recover in the proper tab location after any configuration change
			outState.putInt(KEY_TAB_POSITION, getSupportActionBar().getSelectedNavigationIndex());
		}
	}
	
	/**
	 * Adding checks to make sure the activity recovers properly
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
		if (savedInstanceState.containsKey(KEY_TAB_POSITION)) {
			// Recover the saved selected tab's index value
			int index = savedInstanceState.getInt(KEY_TAB_POSITION);
			
			// Set the initial selected tab depending on the saved value if any.
			getSupportActionBar().setSelectedNavigationItem(index);
		}
	}
	
	/**
	 * Adding a check to make sure that the ListFragment gets a top padding applied properly
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		
		if (mList != null) {
			mList.addTopPadding(getSupportActionBar().getHeight());
			
			hideMapView(true);
		}
	}
	
	/**
	 * This method is used to setup our ActionBar tabs for switching between list and map views.
	 */
	private void setupActionBarTabs() {
		// Setup action bar for tabs
	    ActionBar actionBar = getSupportActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    
		Tab tab = actionBar.newTab().setText("Map").setTabListener(this);
		actionBar.addTab(tab, TAB_MAP, false);

		tab = actionBar.newTab().setText("List").setTabListener(this);
		actionBar.addTab(tab, TAB_LIST, false);
	}

	/**
	 * Handling <code>TabListener</code> callbacks
	 */
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// Setting this transaction to fade in and out fragments when show or hiding them respectively.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) { // Only fade in/out if using Android Honeycomb or above
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		}
		
		// Checking which tab was selected
		switch (tab.getPosition()) {
		case TAB_MAP: // The Map tab was selected
			// Remove the locations list from displaying
			if (mList != null) {
				ft.remove(mList);
			}
			
			// Show the map. This will fade in the map
			showMapView(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB);
			
			break;
		case TAB_LIST: // The List tab was selected
			// Check if we have a ListFragment object available, otherwise make a new one
			if (mList == null) {
				mList = new ExampleListFragment();
			}
			
			// If the list fragment is not already displaying then add it into the fragment container
			if (getSupportFragmentManager().findFragmentByTag(TAG_LOCATIONS_LIST) == null) {
				ft.replace(R.id.map_fragment_container, mList, TAG_LOCATIONS_LIST);
			}
	        
			/**
			 * If the map was not in a READY state, reset it so that it goes back to the READY state.
			 * Call this function will hide the sliding details panel if it is showing directions or location data.
			 */
	        resetMapState();
	        
	        // Hide the map from displaying. This will also try and hide the sliding details panel from showing too.
	        hideMapView(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB);
	        
			break;
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// User selected the already selected tab. Usually do nothing.
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// Do nothing...
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
		
		// Setup the ActionBar tabs for switching between location list and map view.
		setupActionBarTabs();
		
		// Set the initial selected tab to be the Map tab
		getSupportActionBar().setSelectedNavigationItem(TAB_MAP);
	}
	
	@Override
	public void onVenueLoadingFail() {
		super.onVenueLoadingFail();
		
		// Internet probably went down or the data from the MappedIn servers is corrupted or something else bad happened while loading the venue :/
		Toast.makeText(getApplicationContext(), "An error occured while loading the venue...", Toast.LENGTH_LONG).show();
	}
	
	/**
	 * Checking intent for adding voice searching ability into locations list fragment's SearchView
	 */
	@Override
    protected void onNewIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			if (mList != null) {
				mList.doSearch(query);
			}
		}
    }
	
	/**
	 * Example of how to stop the activity of finishing when the user presses the back button because you want to run some additional logic
	 */
	@Override
	public boolean shouldExitMap() {
		// If we are currently displaying the list, switch to showing the map
		if (getSupportActionBar().getSelectedNavigationIndex() == TAB_LIST) {
			getSupportActionBar().setSelectedNavigationItem(TAB_MAP);
			return false;
		}
		
		// Let the activity exit
		return super.shouldExitMap();
	}

	/**
	 * Example of how to handle a user click on the directions button that is shown in the Sliding Up Panel with the location details.
	 * Run this app in your Android device or emulator to see what I mean.
	 * </br></br>
	 * Here we are handling the button click by showing a <code>Toast</code> message about the user clicking the button.
	 * 
	 * @see com.mappedin.sdk.MappedInMapActivity#loadDirections
	 */
	@Override
	public void onDirectionButtonClick(MappedInLocation location, MappedInNode node) {
		Toast.makeText(this, "You want to get direction to " + location.getName(), Toast.LENGTH_SHORT).show();
	}
}

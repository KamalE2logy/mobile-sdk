package com.example.mappedin_demo;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.mappedin.api.objects.MappedInLocation;
import com.mappedin.api.objects.MappedInNode;
import com.mappedin.sdk.MappedInMapActivityConfiguration;
import com.mappedin.sdk.interfaces.SimplePanelSlideListener;

import static com.example.mappedin_demo.Constants.MAPPEDIN_API_SECRET;
import static com.example.mappedin_demo.Constants.MAPPEDIN_SHOPPING_ID;

/** 
 * In this activity you will learn how to get and show groups of different locations on the map at the same time
 * </br></br>
 * You will also learn how to read the different types of categories available within a certain venue.
 * 
 * @author Hassaan Aamir
 *
 */
public class ExampleMapLocationActivity extends ExampleBaseActivity implements ActionBar.OnNavigationListener {
	
	private static final String KEY_LOCATION = "ExampleMapLocationActivity.KEY_BUNDLE_LOCATION";
	
	private static final int CATEGORY_NONE = 0, CATEGORY_ALL = 1;

	private final MappedInMapActivityConfiguration mConfiguration = new MappedInMapActivityConfiguration();
	
	private ArrayList<String> mCategories = new ArrayList<String>();
	
	/**
	 * This location will be used for restoring the sliding panel after a configuration change or activity restart.
	 */
	private MappedInLocation mCurrentLocation;
	
	private int mLastPosition = -1;
	
	/**
	 * Example of how to set the various configuration settings needed by <code>MappedInMapActivity</code> to start up properly.
	 * </br></br>
	 * Here we are passing the MappedIn Secret key which is needed to make <code>MappedInMapActivity</code> work properly.
	 * We can also pass our own <code>MappedInAPI</code> object without having to set a secret key.
	 * </br></br>
	 * We are also setting a <code>PanelSlideListener</code> that we will be using to know when the panel is closed.
	 * </br></br>
	 * To learn more about what other configuration setting you can modify and/or use
	 * @see com.mappedin.sdk.MappedInMapActivityConfiguration
	 */
	public ExampleMapLocationActivity() {
		super();
		mConfiguration.setMappedInAPISecret(MAPPEDIN_API_SECRET);
		mConfiguration.setVenueToLoad(MAPPEDIN_SHOPPING_ID, this);
		mConfiguration.setSlidingPanelListener(mPanelListener);
	}

	@Override
	protected MappedInMapActivityConfiguration getMappedInMapActivityConfiguration() {
		return mConfiguration;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 * If a venue already exists, which means that we are simply recovering from a configuration change or sleep,
		 * then setup the ActionBar navigation list immediately as the floor data is already available 
		 * and we do not have to wait for onVenueLoadingSuccess() as it will not be called anyway.
		 */
		if (getVenue() != null) {
			setupActionbarDropdown();
		} else {
			setSupportProgressBarIndeterminateVisibility(true);
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		// Saving the currently displayed location is any
		outState.putParcelable(KEY_LOCATION, mCurrentLocation);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
		// Restoring the previously displayed location if any
		mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
	}
	
	/**
	 * Here we are overriding one of the callbacks used by the sliding panel to store the current location being displayed to the user.
	 */
	@Override
	public void onLocationChanged(MappedInLocation newLocation, MappedInNode node) {
		super.onLocationChanged(newLocation, node);
		
		mCurrentLocation = newLocation;
	}
	
	/**
	 * Here we are handling the location mapping logic. When a user selects a filtering option from the ActionBar dropdown menu,
	 * it will get processed here.
	 */
	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		if (mLastPosition == position) {
			return false;
		}
		
		if (position == CATEGORY_NONE) {
			// Checking if there are any locations mapped in the map already
			if (getMappedNodesSize() > 0) {
				// Remove all mapped locations and hide the details panel
				removeMappedLocations();
				hideDetailsPanel(true);
			}
			
			mCurrentLocation = null;
		} else {
			/**
			 * Making a temporary location variable to keep a reference to our previously displayed location. This is needed 
			 * to restore the location details panel to the same location after it has been populated.
			 */
			final MappedInLocation recoverLocation = mCurrentLocation;
			
			if (position == CATEGORY_ALL) {
				// Mapping all known locations within the currently loaded venue
				mapLocations(new ArrayList<MappedInLocation>(getAllLocations()));
			} else {
				// Getting the category name that will be used to get the locations to show in the map
				String catergoryName = mCategories.get(position);
	
				// Mapping all the locations that we got back from the getLocationsByCategory() method
				mapLocations(new ArrayList<MappedInLocation>(getLocationsByCategory(catergoryName)));
			}
			
			/**
			 * Checking if we are recovering from a fresh activity start and if our previously displayed location is not null
			 */
			if (mLastPosition == -1 && recoverLocation != null) {
				/**
				 * Looks like we need to restore the location being displayed by the sliding details panel. Now that the panel has been populated,
				 * we are going to ask the panel what is the exact position of the location we need to display in the panel. We are then
				 * going to force the panel to display that location.
				 */
				getSlidingDetailsPanel().slideToLocation(recoverLocation);
			}
		}
		
		mLastPosition = position;
		
		return true;
	}
	
	/**
	 * Method to set up the action bar to show a dropdown list of all known categories and extra "All" plus "None" options.
	 * This dropdown will be used to map the selected category locations within the map
	 */
	private void setupActionbarDropdown() {
		
		// Save the list of categories so we can reuse them in the <code>onNavigationItemSelected()</code> callback method
		mCategories = new ArrayList<String>(getCategories());

		mCategories.add(CATEGORY_NONE, "None");
		mCategories.add(CATEGORY_ALL, "All");
		
		/**
		 * Here we are going to be using the ActionBar navigation list to show our list of location filtering options.
		 */
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		
		// Creating a simple array adapter of all the location filtering options that the user can select and switch between
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mCategories);
		
		// Setting the newly created adapter and setting this activity as the callback
		actionBar.setListNavigationCallbacks(adapter, this);
	}
	
	/**
	 * Setting up a Simple Sliding Panel Listener to know when the panel closes so that we can set the ActionBar 
	 * dropdown to "None" since all locations get removed from the map when the panel closes.
	 */
	private final SimplePanelSlideListener mPanelListener = new SimplePanelSlideListener() {
		public void onPanelHidden(View panel) {
			getSupportActionBar().setSelectedNavigationItem(0);
		};
	};

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
		
		// Setup the ActionBar buttons now that the venue has fully loaded
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
	 * Here we are handling the button click by showing a <code>Toast</code> message about the user clicking the button.
	 * 
	 * @see com.mappedin.sdk.MappedInMapActivity#loadDirections
	 */
	@Override
	public void onDirectionButtonClick(MappedInLocation location, MappedInNode node) {
		Toast.makeText(this, "You want to get direction to " + location.getName(), Toast.LENGTH_SHORT).show();
	}
}

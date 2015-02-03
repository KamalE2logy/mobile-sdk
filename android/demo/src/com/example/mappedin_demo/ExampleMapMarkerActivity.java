package com.example.mappedin_demo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mappedin.api.objects.MappedInAmenity;
import com.mappedin.api.objects.MappedInLocation;
import com.mappedin.api.objects.MappedInNode;
import com.mappedin.sdk.MappedInMapActivityConfiguration;
import static com.example.mappedin_demo.Constants.MAPPEDIN_API_SECRET;
import static com.example.mappedin_demo.Constants.MAPPEDIN_MALL_ID;

/**
 * In this activity you will learn how to show/hide amenity icons or markers in the map. For example the washroom icons.
 * </br></br>
 * You will also learn how to show/hide the events markers too. 
 * </br></br>
 * By default MappedIn Map shows all amenity and events markers on the map. You can however disable them as soon as the 
 * venue has fully loaded. Then the SDK will remember the previous settings unless you specific not to save the settings.
 * 
 * @author Hassaan Aamir
 *
 */
public class ExampleMapMarkerActivity extends ExampleBaseActivity implements OnClickListener {
	
	private static final String WASHROOM_AMENITY_VALUE = "washroom";

	private final MappedInMapActivityConfiguration mConfiguration = new MappedInMapActivityConfiguration();

	private Button mWashroomButton, mEventsButton;
	
	private MappedInAmenity mWashroomAmenity;
	
	/**
	 * Example of how to set the various configuration settings needed by <code>MappedInMapActivity</code> to start up properly.
	 * </br></br>
	 * Here we are passing the MappedIn Secret key which is needed to make <code>MappedInMapActivity</code> work properly.
	 * We can also pass our own <code>MappedInAPI</code> object without having to set a secret key.
	 * </br></br>
	 * To learn more about what other configuration setting you can modify and/or use
	 * @see com.mappedin.sdk.MappedInMapActivityConfiguration
	 */
	public ExampleMapMarkerActivity() {
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

		/**
		 * If a venue already exists, which means that we are simply recovering from a configuration change or sleep,
		 * then setup the toggle button immediately as the venue data is already available 
		 * and we do not have to wait for onVenueLoadingSuccess() as it will not be called anyway.
		 */
		if (getVenue() != null) {
			setupButtons();
		} else {
			setSupportProgressBarIndeterminateVisibility(true);
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_demo_washrooms:
			
			// Here we are simply toggling the washroom map markers to show on the map or not
			showAmenityMarker(mWashroomAmenity, !isAmenityMarkersShowing(mWashroomAmenity));
			
			break;
		case R.id.button_demo_events:

			// Here we are simply toggling the notification map markers to show on the map or not
			showNotificationsMarker(!isNotificationMarkersShowing());
			
			break;
		}
		
		updateButtonTexts();
	}
	
	/**
	 * This method is used to change the text of the toggle buttons to better communicate 
	 * to the user exactly what clicking the buttons will do. 
	 */
	private void updateButtonTexts() {
		if (isAmenityMarkersShowing(mWashroomAmenity)) {
			mWashroomButton.setText("Hide Washrooms");
		} else {
			mWashroomButton.setText("Show Washrooms");
		}

		if (isNotificationMarkersShowing()) {
			mEventsButton.setText("Hide Events");
		} else {
			mEventsButton.setText("Show Events");
		}
	}
	
	/**
	 * This method is used to dynamically create buttons and inject them into a LinearLayout that will be shown at the top of the screen and under the ActionBar.
	 * These buttons will be used for toggling (showing/hiding) the washrooms and events map markers
	 */
	private void setupButtons() {
		
		// Creating our LinearLayout in which we will insert our buttons
		LinearLayout buttonLayout = new LinearLayout(this);
		buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
		
		((RelativeLayout)findViewById(R.id.mappedin__root)).addView(buttonLayout);
		
		// Positioning the LinearLayout right below the ActionBar near the top of the screen
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) buttonLayout.getLayoutParams();
		params.topMargin = getActionBarHeight();
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		buttonLayout.setLayoutParams(params);

		/**
		 * Creating the washrooms' button dynamically
		 */
		mWashroomButton = new Button(this);
		mWashroomButton.setId(R.id.button_demo_washrooms);
		mWashroomButton.setOnClickListener(this);
		
		// Checking if there are any washrooms in this venue
		if (doWashroomsExist()) {
			// Adding the washrooms' button into out LinearLayout because there are washrooms located inside this venue
			buttonLayout.addView(mWashroomButton);
		}

		/**
		 * Creating a events' button dynamically
		 */
		mEventsButton = new Button(this);
		mEventsButton.setId(R.id.button_demo_events);
		mEventsButton.setOnClickListener(this);
		
		// Only show the Events toggle button if there are actually events in this venue
		if (getNotifications().size() > 0) {
			buttonLayout.addView(mEventsButton);
		}
		
		updateButtonTexts();
	}
	
	/**
	 * Method to check if the washroom amenity exists in this venue. 
	 * This will be used to check if we should display the toggle button to show/hide the washroom markers on the map
	 * @return <code>true</code> if the Washroom amenity exists in the venue. Otherwise <code>false</code>.
	 */
	private boolean doWashroomsExist() {
		// Getting all amenities available within this venue
		for (MappedInAmenity amenity : getAmenities()) {
			// Checking if the current amenity is the Washroom amenity
			if (amenity.getValue().equals(WASHROOM_AMENITY_VALUE)) {
				// The washroom amenity has been found. Saving a reference to it as it will be needed to toggle the washroom map makers on/off.
				mWashroomAmenity = amenity;
				
				return true;
			}
		}
		
		return false;
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
		
		setupButtons();
	}
	
	@Override
	public void onVenueLoadingFail() {
		super.onVenueLoadingFail();
		
		// Internet probably went down or the data from the MappedIn servers is corrupted or something else bad happened while loading the venue :/
		Toast.makeText(getApplicationContext(), "An error occured while loading the venue...", Toast.LENGTH_LONG).show();
	}
	
	/**
	 * Example of how to override base functions. Here we are just showing/hiding the buttons that we have created when showing/hiding the actionbar.
	 */
	@Override
	public void showActionBar() {
		super.showActionBar();

		if (mWashroomButton != null) {
			mWashroomButton.setVisibility(View.VISIBLE);
		}
		
		if (mEventsButton != null) {
			mEventsButton.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	public void hideActionBar() {
		super.hideActionBar();

		if (mWashroomButton != null) {
			mWashroomButton.setVisibility(View.GONE);
		}
		
		if (mEventsButton != null) {
			mEventsButton.setVisibility(View.GONE);
		}
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

package com.example.mappedin_demo;

import java.util.Iterator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mappedin.api.objects.MappedInLocation;
import com.mappedin.api.objects.MappedInNode;
import com.mappedin.sdk.MappedInMapActivityConfiguration;
import com.mappedin.sdk.interfaces.DirectionsLoadingCallback;
import com.mappedin.sdk.interfaces.PanelSlideListener;
import static com.example.mappedin_demo.Constants.MAPPEDIN_API_SECRET;
import static com.example.mappedin_demo.Constants.MAPPEDIN_MALL_ID;

public class ExampleMapActivity extends ExampleBaseActivity implements PanelSlideListener, OnClickListener, DirectionsLoadingCallback {
	
	private static final String TAG = "EXAMPLE_MAP";
	
	private Button mButton;

	private final MappedInMapActivityConfiguration mConfiguration = new MappedInMapActivityConfiguration();
	
	/**
	 * Example of how to set the various configuration settings needed by <code>MappedInMapActivity</code> to start up properly.
	 * </br></br>
	 * Here we are passing the MappedIn Secret key which is needed to make <code>MappedInMapActivity</code> work properly.
	 * We can also pass our own <code>MappedInAPI</code> object without having to set a secret key.
	 * We are also setting up the sliding up panel's callback listener too.
	 * </br></br>
	 * To learn more about what other configuration setting you can modify and/or use
	 * @see com.mappedin.sdk.MappedInMapActivityConfiguration
	 */
	public ExampleMapActivity() {
		super();
		mConfiguration.setMappedInAPISecret(MAPPEDIN_API_SECRET);
		mConfiguration.setSlidingPanelListener(this);
	}

	@Override
	protected MappedInMapActivityConfiguration getMappedInMapActivityConfiguration() {
		return mConfiguration;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (savedInstanceState != null) {
			return;
		}

		/**
		 * Creating a Button dynamically to show you how to set and start loading the map's venue dynamically.
		 */
		mButton = new Button(this);
		mButton.setText("Click to dynamically load MappedIn Mall");
		mButton.setId(R.id.button_demo_map);
		mButton.setOnClickListener(this);
		
		((RelativeLayout)findViewById(R.id.mappedin__root)).addView(mButton);

		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mButton.getLayoutParams();
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule( RelativeLayout.CENTER_HORIZONTAL);
		mButton.setLayoutParams(params);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_demo_map:
			
			loadVenue(MAPPEDIN_MALL_ID, this);
			
			break;
		}
	}

	/**
	 * Example of how to listen to the various callbacks from the MappedIn Map when it is loading venue
	 * 
	 * @see com.mappedin.sdk.interfaces.VenueLoadingCallback
	 */
	@Override
	public void onVenueLoadingStarted() {
		super.onVenueLoadingStarted();
		
		// Hide the button when the loading starts
		((ViewGroup) mButton.getParent()).removeView(mButton);
		
		// The venue is being loaded
		Toast.makeText(getApplicationContext(), "MappedIn Mall is being loaded...", Toast.LENGTH_SHORT).show();
	}
		
	@Override
	public void onVenueLoadingSuccess() {
		super.onVenueLoadingSuccess();
		
		// The venue finished loading all needed resources.
		Toast.makeText(getApplicationContext(), "MappedIn Mall loaded successfully!", Toast.LENGTH_LONG).show();
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
		
		// Picking a random location from the map as our origin to show you how to display directions within the map
		Iterator<MappedInLocation> list = getCurrentMapLocations().iterator();
		while (list.hasNext()) {
			
			origin = list.next();
			
			if (origin.getLocationId().equals(location.getLocationId()) == false) {
				loadDirections(origin, origin.getNodes().values().iterator().next(), location, node, this);
				
				break;
			}
		}
	}

	/**
	 * Example of how to listen to the various callbacks from the <code>loadDirections()</code> method
	 * 
	 * @see com.mappedin.sdk.MappedInMapActivity#loadDirections
	 */
	@Override
	public void onDirectionsLoadingSuccess() {
		Toast.makeText(getApplicationContext(), "Directions should be showing now", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDirectionsLoadingFail(FailureReason reason) {
		Toast.makeText(getApplicationContext(), "An error occured while loading directions...", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onDirectionsLoadingStarted() {
		Toast.makeText(getApplicationContext(), "Directions are being loaded...", Toast.LENGTH_SHORT).show();
	}

	/**
	 * Example of how to listen to the various callbacks from the MappedIn Sliding Up Panel using <code>PanelSlideListener</code>
	 * 
	 * @see com.mappedin.sdk.interfaces.PanelSlideListener
	 */
	@Override
	public void onPanelSlide(View panel, float slideOffset) {
		Log.d(TAG, "Panel is Sliding with offset: " + slideOffset);
	}

	@Override
	public void onPanelCollapsed(View panel) {
		Toast.makeText(this, "Panel is Collapsed", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPanelExpanded(View panel) {
		Toast.makeText(this, "Panel is Expanded", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPanelAnchored(View panel) {
		Toast.makeText(this, "Panel is Anchored", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPanelHidden(View panel) {
		// You probably exited directions or location(s) mode
		Toast.makeText(this, "Sliding Panel is Hidden!", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPanelShown(View panel) {
		Toast.makeText(this, "Sliding Panel is Visible!", Toast.LENGTH_SHORT).show();
	}
}

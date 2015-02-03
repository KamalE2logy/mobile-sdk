package com.example.mappedin_demo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import com.mappedin.sdk.MappedInMapActivity;
import com.mappedin.sdk.interfaces.VenueLoadingCallback;

/**
 * Building a base activity that all other sample activities will extend from unless otherwise coded.
 * This base activity is mainly being used to manage when to show or hide the venue loading indeterminate 
 * ProgressBar, and when to enable/disable debug mode.
 * 
 * @author Hassaan Aamir
 *
 */
public abstract class ExampleBaseActivity extends MappedInMapActivity implements VenueLoadingCallback {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Due to the way the ActionBar is drawn in Android Gingerbread, we have to manually set 
		 * the background drawable to white. Otherwise it will be transparent. 
		 */
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			ColorDrawable background = new ColorDrawable(Color.WHITE);
			getSupportActionBar().setBackgroundDrawable(background);
		}
	}
	
	@Override
	public void setContentView(int layoutResID) {
		
		/**
		 * Since it takes a few seconds for the venue to fully load all data from the servers, we are going to 
		 * show an indeterminate ProgressBar within the window to let the user know that the activity is still loading data.
		 */
		supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		super.setContentView(layoutResID);
	}

	/**
	 * Example of how to listen to the various callbacks from the MappedIn Map when it is loading venue
	 * 
	 * @see com.mappedin.sdk.interfaces.VenueLoadingCallback
	 */
	@Override
	public void onVenueLoadingStarted() {
		// Show the indeterminate ProgressBar to let the user know the activity is still active
		setSupportProgressBarIndeterminateVisibility(true);
	}
		
	@Override
	public void onVenueLoadingSuccess() {
		// Hide the indeterminate ProgressBar as venue is done loading
		setSupportProgressBarIndeterminateVisibility(false);
	}
	
	@Override
	public void onVenueLoadingFail() {
		// Hide the indeterminate ProgressBar as an error has occurred
		setSupportProgressBarIndeterminateVisibility(false);
	}
	
	@Override
	public boolean isDebug() {
		return BuildConfig.DEBUG;
	}
}

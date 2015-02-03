package com.example.mappedin_demo;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.mappedin.api.objects.MappedInLocation;

/**
 * Creating a sample list fragment to load locations inside and display them to the user. 
 * The fragment also manages the ActionBar search widget, for searching through locations.
 * </br></br>
 * Note: In this fragment we are using the <code>ExampleListActivityInterface</code> 
 * interface to communicate with our map activity.
 * 
 * @author Hassaan Aamir
 *
 */
public class ExampleListFragment extends ListFragment implements OnActionExpandListener, OnQueryTextListener, OnScrollListener {
	
	private static final String KEY_BUNDLE_SEARCH_QUERY = "BUNDLE_SEARCH_QUERY";
	
	private SearchView mSearchView;
	
	private ExampleListActivityInterface mListener;
	
	private ArrayAdapter<String> mAdapter;
	
	/**
	 * This array will be used to store all the location IDs of the different locations we are going to display within this fragment.
	 * The IDs will be stored in the same order as they appear within the list.
	 */
	private ArrayList<String> mLocationIDs = new ArrayList<String>();
	
	/**
	 * This variable will be used to store our last search query.
	 */
	private String mLastQuery = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Setting this fragment to display ActionBar menu items
		setHasOptionsMenu(true);
		
		// Setting this fragment to retain information so that it can be restored properly to its original state.
		setRetainInstance(true);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		/**
		 * Setting up essential variables now that this fragment has been attached to an activity and has a context.
		 */
		mListener = (ExampleListActivityInterface) getActivity();
		
		getListView().setOnScrollListener(this);

		addTopPadding(mListener.getSupportActionBar().getHeight());
		
		/**
		 * Recover any saved data, otherwise initialize this fragment as usual.
		 */
		if (savedInstanceState != null) {
			mLastQuery = savedInstanceState.getString(KEY_BUNDLE_SEARCH_QUERY);
		} else {
			mListener.getSupportActionBar().setSelectedNavigationItem(1);
		}
		
		if (mLastQuery == null) {
			resetLocationsList();
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		outState.putString(KEY_BUNDLE_SEARCH_QUERY, mLastQuery);
	}
	
	/**
	 * Example of a very basic location filtering algorithm while performing searches.
	 * @param query The search query to use for filtering locations
	 */
	@SuppressLint("DefaultLocale")
	public synchronized void filterLocationsList(String query) {
		// If this fragment hasn't been initialized or the query is bad, do nothing
		if (getActivity() == null || query == null || query.equals(mLastQuery)) {
			return;
		}
		
		// Save the new search query for future reference
		mLastQuery = query;
		
		// Check if this fragment has been setup with an adapter yet. If not, then create a new adapter.
		if (getListAdapter() == null) {
			mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<String>());
			setListAdapter(mAdapter);
		}
		
		// Clear the adapter and location IDs list
		mAdapter.clear();
		mLocationIDs.clear();
		
		// Using just the location name, find all the locations that contain the search query
		for (MappedInLocation location : mListener.getAllLocations()) {
			if (TextUtils.isEmpty(query)
					|| location.getName().toLowerCase().contains(query.toLowerCase())) {
				mAdapter.add(location.getName());
				mLocationIDs.add(location.getLocationId());
			}
		}
	}

	/**
	 * A method to reset the location list to its default state of showing all locations
	 */
	public void resetLocationsList() {
		filterLocationsList("");
		mLastQuery = null;
	}
	
	/**
	 * Here we are setting up the SearchView ActionBar widgets and how to handle recovery when in the middle of a search
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		
		// Create a new SearchView
		mSearchView = new SearchView(getActivity());
		
		// Set this SearchView to have a voice input button
		SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
		mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
		
		// Set this activity to handle all the query input callbacks
		mSearchView.setOnQueryTextListener(this);
		
		// Create a new search button MenuItem and add it into the ActionBar.
		MenuItem  searchItem = menu.add(0, R.id.action_search, 0, "Search");
		searchItem.setIcon(android.R.drawable.ic_menu_search);
		MenuItemCompat.setActionView(searchItem, mSearchView);
		MenuItemCompat.setShowAsAction(searchItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS|MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		
		// Set this activity to handle all the action view related callbacks
		MenuItemCompat.setOnActionExpandListener(searchItem, this);
		
		// If we were searching before the screen orientation changed or the app went to sleep...
		if (mLastQuery != null) {
			// Restore the search query to what it was originally and expand the Search action view
			String query = mLastQuery;
			MenuItemCompat.expandActionView(searchItem);
			mSearchView.setQuery(query, false);
		}
	}
	
	/**
	 * Handling the <code>OnQueryTextListener</code> callbacks
	 */
	
	@Override
	public boolean onQueryTextSubmit(String query) {
		
		// Filter the locations using the query
		onQueryTextChange(query);
		
		// Close the keyboard
		mSearchView.clearFocus();
		
		return true;
	}
	
	@Override
	public boolean onQueryTextChange(String query) {
		
		// Filter the locations using query
		filterLocationsList(query);
		
		return true;
	}

	/**
	 * Handling the <code>OnActionExpandListener</code> callbacks
	 */
	
	@Override
	public boolean onMenuItemActionExpand(MenuItem item) {
		return true;
	}
	
	@Override
	public boolean onMenuItemActionCollapse(MenuItem item) {
		
		resetLocationsList();
		
		mLastQuery = null;
		
		return true;
	}
	
	/**
	 * Here we handle what to do when a user clicks on a location within the list. In our case we communicate the click 
	 * with the Map activity so that it can display the location within the map.
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		mLastQuery = null;
		
		// Show the location within the map
		mListener.showLocation(mListener.getLocationById(mLocationIDs.get(position)));

		// Switch over to the Map's tab
		mListener.getSupportActionBar().setSelectedNavigationItem(ExampleLocationSearchActivity.TAB_MAP);
	}
	
	/**
	 * A public method to set the search query within this fragment. This method is needed to make the voice searching usable.
	 * 
	 * @param query The search query.
	 */
	public void doSearch(String query) {
		mSearchView.setQuery(query, true);
	}

	/**
	 * This method is used to add padding to the top of this Fragment's ListView so that the ActionBar does not overlap the list.
	 * 
	 * @param padding The amount of padding in pixels to add to the top of this fragment's ListView
	 */
	public void addTopPadding(int padding) {
		// Do not try to modify anything if this fragment is in the process of being removed or anything like it.
		if (getActivity() == null)
			return;
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getListView().getLayoutParams();
			params.topMargin = padding;
			getListView().setLayoutParams(params);
		} else {
			// Modifing the padding instead of margin due to a Gingerbread related layout bug.
			getListView().setPadding(getListView().getPaddingLeft(), 
					padding, // Setting the top padding
					getListView().getPaddingRight(),
					getListView().getPaddingBottom());
		}
	}

	/**
	 * Setting up OnScrollListener methods for fragment's ListView
	 */
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (mSearchView.hasFocus()) {
			mSearchView.clearFocus();
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		
	}
}
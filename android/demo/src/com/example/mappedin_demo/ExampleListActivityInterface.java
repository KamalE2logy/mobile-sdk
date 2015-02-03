package com.example.mappedin_demo;

import java.util.Collection;

import com.mappedin.api.objects.MappedInLocation;

import android.support.v7.app.ActionBar;

public interface ExampleListActivityInterface {
	ActionBar getSupportActionBar();
	Collection<MappedInLocation> getAllLocations();
	MappedInLocation getLocationById(String id);
	void showLocation(MappedInLocation location);
}

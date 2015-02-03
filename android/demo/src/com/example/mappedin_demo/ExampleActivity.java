package com.example.mappedin_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class ExampleActivity extends ActionBarActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_example);
		
		findViewById(R.id.buttonMap).setOnClickListener(this);
		
		findViewById(R.id.buttonMapMarker).setOnClickListener(this);
		
		findViewById(R.id.buttonMapFloors).setOnClickListener(this);
		
		findViewById(R.id.buttonMapLocations).setOnClickListener(this);
		
		findViewById(R.id.buttonSearchLocations).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		final int id = v.getId();
		
		Intent intent;
		
		switch (id) {
		case R.id.buttonMap:
			intent = new Intent(this, ExampleMapActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonMapMarker:
			intent = new Intent(this, ExampleMapMarkerActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonMapFloors:
			intent = new Intent(this, ExampleFloorSwitchingActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonMapLocations:
			intent = new Intent(this, ExampleMapLocationActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonSearchLocations:
			intent = new Intent(this, ExampleLocationSearchActivity.class);
			startActivity(intent);
			break;
		}
	}
}

MappedIn SDK for Android (Alpha)
===============================

This library allows you to integrate MappedIn into your Android app.

Currently this SDK is in alpha stage and there might be some breaking changes in future updates. This SDK also requires Google Play Services to work properly, as the map is built on top of the Google Maps fragment.

#### TRY IT OUT

1. Before you can use the SDK, please obtain a MappedIn API key by contacting support@mappedin.ca
2. You will also need to obtain a Google Maps Android API v2 key from the [Google Deveoper Console](http://console.developers.google.com/)
3. Check-out the demo app on how to setup the keys and how to use the various MappedIn SDK components in your app.
    - Both the MappedIn API key and Google Maps Android API v2 key need to be placed in the `AndroidManifest.xml` file
4. Start coding!

#### USEFUL LINKS

- Learn the basic concepts behind MappedIn's API structure [here](http://docs.mappedin.com/concepts/).

#### THEMEING

When creating custom themes for MappedInMapActivity, you will need to set the following four attributes

1. `edgeEffectColor`
2. `pageBackgroundColor`
3. `selectableBackground`
4. `slidingPanelBackground`

Sample values for these attributes are available in the demo app's [_styles.xml_](https://github.com/MappedIn/mobile-sdk/blob/master/android/demo/res/values/styles.xml) file.

#### GRADLE & ANDROID STUDIO

Since the SDK is being provided in [AAR](http://tools.android.com/tech-docs/new-build-system/aar-format) format, you will need to reference the `aar` file plus all of the SDK's dependencies in your _build.gradle_ file like so:

```
dependencies {
	// The following are the dependencies needed to run MappedIn SDK
	compile 'com.android.support:support-v4:20.0.0'
	compile 'com.android.support:appcompat-v7:20.0.0'
	compile 'com.android.support:gridlayout-v7:20.0.0'
	compile 'com.google.android.gms:play-services-maps:6.5.87'
	compile 'com.flaviofaria:kenburnsview:1.0.6'
	compile 'com.squareup.okhttp:okhttp:2.0.0'
	compile 'com.squareup.okhttp:okhttp-urlconnection:2.0.0'
	compile 'com.squareup.okio:okio:1.0.0'
	compile 'com.squareup.picasso:picasso:2.3.2'

	// Loading the MappedIn SDK
	compile 'com.mappedin:MappedIn-SDK-0.1@aar'
}
```

It is recommended that you place the `aar` file in your app module's `libs` folder and set the folder as a repository in _build.gradle_ like so:

```
repositories {
	flatDir {
		dirs 'libs'
	}
}
```

You may use the the demo app's [_build.gradle_](https://github.com/MappedIn/mobile-sdk/blob/master/android/demo/build.gradle) file as a reference.

#### AUTHOR

Hassaan Aamir, hassaan@mappedin.ca

#### LICENSE

Copyright 2015 MappedIn 

Except as otherwise noted, the MappedIn SDK for Android is licensed under the Apache License, Version 2.0 (the "License"); you may not use this SDK except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the specific language governing permissions and limitations under the License.

All icons, images and drawable resources within the MappedIn SDK folder are the property of MappedIn unless otherwise noted and they shall not be used outside of the `MappedInMapActivity` class without MappedIn's approval.

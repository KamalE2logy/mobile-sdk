MappedIn SDK for Android (Alpha)
===============================

This library allows you to integrate MappedIn into your Android app.

Currently this SDK is in alpha stage and there might be some breaking changes in future updates. This SDK also requires Google Play Services to work properly, as the map is built on top of the Google Maps fragment.

#### TRY IT OUT

1. Before you can use the SDK, please obtain a MappedIn API key from [here](http://portal.mappedin.com/client-key/)
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

Sample values for these attributes are available in the demo app's _styles.xml_ file.

#### AUTHOR

Hassaan Aamir, hassaan@mappedin.ca

#### LICENSE

Copyright 2014 MappedIn 

Except as otherwise noted, the MappedIn SDK for Android is licensed under the Apache License, Version 2.0 (the "License"); you may not use this SDK except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the specific language governing permissions and limitations under the License.

All icons, images and drawable resources within the MappedIn SDK folder are the property of MappedIn unless otherwise noted and they shall not be used outside of the `MappedInMapActivity` class without MappedIn's approval.

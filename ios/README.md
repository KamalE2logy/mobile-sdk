# MappedIn iOS SDK

## Requirements

Installation requires [CocoaPods](http://cocoapods.org/)

Project requires ARC and iOS 7.0+

You will also need to obtain a MappedIn API key by contacting support@mappedin.ca

## Installation

Clone this repo in the directory above your app's project directory.

In your own project Podfile, include these following lines (be sure to modify `:git` for `MappedIn`, e.g. `'../mappedin-sdk-ios'`):


```
pod 'Mapbox', :podspec => 'https://raw.githubusercontent.com/DanLite/mapbox-ios-sdk/1.2.0-ui-non-blocking/Mapbox.podspec'
pod 'FXPageControl', :podspec => 'https://raw.githubusercontent.com/DanLite/FXPageControl/custom-podspec/FXPageControl.podspec'
pod 'MappedIn', :git => 'RELATIVE_PATH_TO_MAPPEDIN_SDK_PROJECT'
```

You may also include `:tag => '0.1.3'` to specify, for example, version 0.1.3 of the MappedIn SDK.

## Usage

To run the example project, clone the repo, and run `pod install` from the Example directory first.

## Author

Hassaan Aamir, hassaan@mappedin.ca

## License

MappedInSDK is available under the MIT license. See the LICENSE file for more info.


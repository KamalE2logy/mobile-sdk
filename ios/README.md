# MappedIn iOS SDK

## Requirements

Installation requires [CocoaPods](http://cocoapods.org/)

Project requires ARC and iOS 7.0+

You will also need to obtain a MappedIn API key by contacting support@mappedin.ca

## Installation

Clone this repo in the directory above your app's project directory.

In your own project's Podfile, include these following lines (be sure to modify `:git` for `MappedIn`, e.g. `'../mobile-sdk/ios'`):

```
pod 'MappedIn', :git => 'RELATIVE_PATH_TO_MAPPEDIN_SDK_PROJECT'
```

You may also include `:tag => '0.1.3'` to specify, for example, version 0.1.3 of the MappedIn SDK.

## Usage

To run the example project, clone the repo, and run `pod install` from the Example directory first.

You will then have to edit the `Pods-MappedInDemo-resources.sh` file and remove or comment out all lines importing any `Info.plist` files into the app. 

Like in this image:
![screen shot 2015-03-12 at 6 32 27 pm](https://cloud.githubusercontent.com/assets/424121/6629619/6a7e149a-c8e6-11e4-8f28-646c525b895c.png)

This needs to be done to get around a build [issue](https://github.com/MappedIn/mobile-sdk/issues/1) with Xcode.

**Note**: You will have to edit this shell script every time you run `pod update` or `pod install`. Project cleaning might be needed after editing the script too.

## Author

Hassaan Aamir, hassaan@mappedin.ca

## License

MappedInSDK is available under the MIT license. See the LICENSE file for more info.


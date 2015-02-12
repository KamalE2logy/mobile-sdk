//
//  MIAppDelegate.m
//  MappedInSDK
//
//  Created by CocoaPods on 07/25/2014.
//  Copyright (c) 2014 MappedIn. All rights reserved.
//

#import "MIAppDelegate.h"

#import <MappedIn/MIConstants.h>
#import <MappedIn/MIConfig.h>

@implementation MIAppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  // Show event markers on the map
  [[NSUserDefaults standardUserDefaults] registerDefaults:@{ MINotificationMarkersActive: @YES }];
  
  UIColor *tint = [UIColor redColor];
  UIFont *font = [UIFont fontWithName:@"HelveticaNeue-Light" size:[UIFont labelFontSize]];
  
  // Set default UI font and colours used throughout the MappedIn views
  [[MIConfig sharedConfig] setValue:tint forKey:MIConfigViewTintColor];
  [[MIConfig sharedConfig] setValue:font forKey:MIConfigDefaultFont];
  
  // Set the same tint colour/font for other controls used in the MappedIn views.
  // Use your discretion here, as this modifies the appearance of the entire app.
  [[UIButton appearance] setTintColor:tint];
  [[UINavigationBar appearance] setTintColor:tint];
  [[UIImageView appearance] setTintColor:tint];
  [[UIToolbar appearance] setTintColor:tint];
  
  [[UILabel appearance] setFont:font];
  [[UITextField appearance] setFont:font];
  
  return YES;
}

@end

//
//  MIDemoViewController.m
//  MappedInSDK
//
//  Created by Daniel Lichty on 07/25/2014.
//  Copyright (c) 2014 MappedIn. All rights reserved.
//

#import "MIDemoViewController.h"

#import <MappedIn/MIConstants.h>
#import <MappedIn/MIMapViewController.h>

#import <MappedIn/MIVenue.h>
#import <MappedIn/MILocation.h>

#define kAPIKeyDefaultKey @"MI_APIKeyDefault"
#define kSecretKeyDefaultKey @"MI_SecretKeyDefault"
#define kInitialVenueSlugDefaultKey @"MI_InitialVenueSlugDefault"

#ifndef kDesiredLocationIdentifier
#define kDesiredLocationIdentifier @""
#endif

@interface MIDemoViewController ()

@end

@implementation MIDemoViewController

- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context
{
  if ([keyPath isEqualToString:@"venue.assetsLoading"])
  {
    MIMapViewController *mapVC = object;
    
    if (!mapVC.venue || mapVC.venue.assetsLoading)
      return;
    
    // At this point, the venue's maps, locations, amenities, and categories have been loaded
      
    [mapVC removeObserver:self forKeyPath:keyPath];
    
    NSDictionary *locations = mapVC.venue.locations;
    MILocation *location = locations[kDesiredLocationIdentifier];
    
    if (location)
    {
      NSLog(@"Starting map view controller with location '%@'", location.name);
      
      NSArray *locations = @[location];
      
      [mapVC performActionWhenViewIsVisible:^{
        [mapVC controller:nil didSelectLocations:locations withSearchText:nil asCategory:NO];
      }];
    }
  }
}

- (void)viewDidLoad
{
  self.apiKeyField.text = [[NSUserDefaults standardUserDefaults] stringForKey:kAPIKeyDefaultKey];
  self.secretKeyField.text = [[NSUserDefaults standardUserDefaults] stringForKey:kSecretKeyDefaultKey];
  self.venueSlugField.text = [[NSUserDefaults standardUserDefaults] stringForKey:kInitialVenueSlugDefaultKey];
}

- (void)showMap:(id)sender
{
  MIMapViewController *mapViewController = [[MIMapViewController alloc] init];
  
  // Observe the loading status of the venue to find and set a location once locations have loaded
  [mapViewController addObserver:self forKeyPath:@"venue.assetsLoading" options:kNilOptions context:nil];
  
  // Set the venue that is loaded when the view appears
  mapViewController.initialVenueSlug = self.venueSlugField.text;
  
  [mapViewController setAPIKey:self.apiKeyField.text secret:self.secretKeyField.text version:nil];
  
  // Add events button to navigation bar for revealing the events list
  mapViewController.navigationItem.rightBarButtonItem = mapViewController.eventsBarButtonItem;
  
  [[NSUserDefaults standardUserDefaults] setObject:self.apiKeyField.text forKey:kAPIKeyDefaultKey];
  [[NSUserDefaults standardUserDefaults] setObject:self.secretKeyField.text forKey:kSecretKeyDefaultKey];
  [[NSUserDefaults standardUserDefaults] setObject:self.venueSlugField.text forKey:kInitialVenueSlugDefaultKey];
  
  [self.navigationController pushViewController:mapViewController animated:YES];
}

@end

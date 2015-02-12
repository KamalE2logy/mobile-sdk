//
//  MIVenue.h
//  MappedInCocoa
//
//  Created by Dan Lichty on 2013-10-23.
//  Copyright (c) 2013 MappedIn, Inc. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

@class MIMap;
@class MIAPI;
@class MILocation;

#define kVenueDistanceUnknown -1

@interface MIVenue : NSObject

@property (nonatomic, readonly) NSString *slug;

@property (nonatomic, readonly) NSString *name;
@property (nonatomic, readonly) NSString *address;
@property (nonatomic, readonly) NSString *city;
@property (nonatomic, readonly) NSString *state;

@property (nonatomic, readonly) NSString *type;

@property (nonatomic, readonly) NSString *coverPhotoURL;
@property (nonatomic, readonly) NSString *thumbnailPhotoURL;

@property (nonatomic, readonly) NSString *emailSuffix;
@property (nonatomic, readonly) NSArray *emailSuffixes;
@property (nonatomic) BOOL locked;

@property (nonatomic, readonly) NSString *defaultMapID;
@property (nonatomic, strong) NSString *currentMapID;
@property (nonatomic, readonly) MIMap *currentMap;

@property (nonatomic, readonly) BOOL qrScanningEnabled;

@property (nonatomic, readonly) CLLocationCoordinate2D coordinate;
@property (nonatomic) CLLocationDistance distance;

@property (nonatomic, readonly) NSArray *amenities;
@property (nonatomic, readonly) NSArray *categories;
@property (nonatomic, readonly) NSArray *notifications;
@property (nonatomic, readonly) NSDictionary *locations;
@property (nonatomic, readonly) NSMutableDictionary *maps;
@property (nonatomic, readonly) NSMutableDictionary *mapGroups;
@property (nonatomic, readonly) NSMutableArray *mapGroupNames;

// { map.id -> { amenity.id -> locations } }
@property (nonatomic, readonly) NSDictionary *mapAmenityLists;

@property (nonatomic) BOOL notificationMarkersActive;

@property (nonatomic, readonly) BOOL assetsLoading;

@property (nonatomic, strong) MIAPI *api;

+ (NSArray *)allVenues;
+ (void)setAllVenues:(NSArray *)venues;
+ (void)sortAllVenuesByDistance;

- (id)initWithDictionary:(NSDictionary *)dict;

- (BOOL)isEqual:(MIVenue *)venue;

- (MIMap *)mapWithID:(NSString *)mapID;

- (NSMutableDictionary *)locationsInMapGroup:(NSString *)mapGroupID;
- (MILocation *)locationWithID:(NSString *)identifier;

- (void)fetchMaps;
- (void)fetchLocationsForMapGroup:(NSString *)mapGroupID;
- (void)fetchNotifications;
- (void)fetchCategories;
- (void)fetchAmenities;

- (CLLocationDistance)distanceFromLocation:(CLLocation *)location;
- (NSString *)formattedDistance:(CLLocationDistance)distance;

- (NSInteger)unseenNotificationCount;

@end

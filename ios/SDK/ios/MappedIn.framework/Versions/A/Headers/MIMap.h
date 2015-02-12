//
//  MIMap.h
//  MappedInCocoa
//
//  Created by Dan Lichty on 2013-10-23.
//  Copyright (c) 2013 MappedIn, Inc. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

@class MILocation;
@class MIPerspectiveTransform;

@interface MIMap : NSObject

@property (nonatomic, readonly) NSString *identifier;

@property (nonatomic, readonly) NSString *name;
@property (nonatomic, readonly) NSString *shortName;

@property (nonatomic, readonly) NSDictionary *group;

@property (nonatomic, readonly) NSInteger width;
@property (nonatomic, readonly) NSInteger height;

@property (nonatomic, readonly) CGFloat minZoom;
@property (nonatomic, readonly) CGFloat maxZoom;

@property (nonatomic, readonly) NSString *tileURL;

@property (nonatomic, readonly) BOOL useGPS;

@property (nonatomic, readonly) MIPerspectiveTransform *georeferenceTransform;

@property (nonatomic, readonly) NSString *perspective;

@property (nonatomic, strong) NSDictionary *uniqueNodeLocations;

@property (nonatomic, readonly) CLCircularRegion *boundsRegion;

- (id)initWithDictionary:(NSDictionary *)dict;

- (NSDictionary *)nearestLocationAndNodeToPoint:(CGPoint)venuePoint inList:(NSArray *)locations;
- (CGRect)rectContainingLocations:(NSArray *)locations;

- (CGPoint)coordinateToVenuePoint:(CLLocationCoordinate2D)coord;
- (CLLocationCoordinate2D)venuePointToCoordinate:(CGPoint)point;

- (BOOL)matchesText:(NSString *)text;

@end

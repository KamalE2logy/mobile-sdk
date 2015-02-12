//
//  MILocation.h
//  MappedInCocoa
//
//  Created by Dan Lichty on 2013-10-23.
//  Copyright (c) 2013 MappedIn, Inc. All rights reserved.
//

#import <Foundation/Foundation.h>

@class MIVenue;
@class MIMap;
@class MIAssetImage;
@class MINotification;

@interface MILocation : NSObject

@property (nonatomic, readonly) NSString *identifier;

@property (nonatomic, readonly) NSString *name;
@property (nonatomic, readonly) NSArray *searchableNames;
@property (nonatomic, readonly) NSString *desc;

@property (nonatomic, readonly) MIVenue *venue;

@property (nonatomic, readonly) NSString *toMapID;

@property (nonatomic, readonly) NSString *type;
@property (nonatomic, readonly) NSString *amenityID;

@property (nonatomic, readonly) NSArray *nodes;

@property (nonatomic, readonly) NSDictionary *phone;
@property (nonatomic, readonly) NSDictionary *social;

@property (nonatomic, readonly) NSArray *categories;
@property (nonatomic, readonly) NSString *category;

@property (nonatomic, readonly) MIAssetImage *logo;
@property (nonatomic, readonly) MIAssetImage *picture;

@property (nonatomic, readonly) NSArray *notifications;

@property (nonatomic, readonly) NSArray *tags;

// location does not have all required properties
// i.e. it was built from a notification's `location` property
// Should look up location from venue
@property (nonatomic, getter = isStub) BOOL stub;

- (id)initWithDictionary:(NSDictionary *)dict venue:(MIVenue *)venue;

- (NSDictionary *)smallestDistanceAndNodeFromPoint:(CGPoint)venuePoint onMap:(MIMap *)map;
- (CGPoint)nodeMidpointOnMap:(MIMap *)map;
- (NSArray *)nodesOnMap:(MIMap *)map;

- (void)addNotification:(MINotification *)notification;

- (BOOL)matchesText:(NSString *)text matchingCategory:(BOOL)includeCategory;
- (BOOL)matchesText:(NSString *)text;

- (UIImage *)amenityMarkerImage;

- (NSString *)mapList;

@end

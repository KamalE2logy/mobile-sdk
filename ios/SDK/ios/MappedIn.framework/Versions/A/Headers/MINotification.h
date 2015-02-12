//
//  MINotification.h
//  MappedInCocoa
//
//  Created by Dan Lichty on 2013-10-23.
//  Copyright (c) 2013 MappedIn, Inc. All rights reserved.
//

#import <Foundation/Foundation.h>

@class MILocation;
@class MIAssetImage;
@class MIVenue;

@interface MINotification : NSObject

@property (nonatomic, readonly) NSString *identifier;

@property (nonatomic, readonly) NSString *name;
@property (nonatomic, readonly) NSString *desc;

@property (nonatomic, strong) MILocation *location;

@property (nonatomic, readonly) MIAssetImage *image;

@property (nonatomic, readonly) NSDate *startDate;
@property (nonatomic, readonly) NSDate *endDate;

@property (nonatomic) BOOL hasBeenSeen;

- (id)initWithDictionary:(NSDictionary *)dict venue:(MIVenue *)venue;

- (BOOL)matchesText:(NSString *)text;

@end

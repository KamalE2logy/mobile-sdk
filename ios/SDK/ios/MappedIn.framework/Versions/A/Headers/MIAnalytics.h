//
//  MIAnalytics.h
//  MappedIn
//
//  Created by Daniel Lichty on 2014-04-10.
//
//

#import <Foundation/Foundation.h>

@class MIAPI;

@interface MIAnalytics : NSObject

+ (void)observeVenueChanges:(id)object;
+ (void)stopObservingVenueChanges:(id)object;

+ (void)resetSession:(MIAPI *)api;
+ (void)trackAction:(NSString *)verb on:(NSString *)noun payload:(NSDictionary *)payload;

@end

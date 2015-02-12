//
//  MILocationManager.h
//  MappedIn
//
//  Created by Daniel Lichty on 2/21/2014.
//
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>

@interface MILocationManager : NSObject <CLLocationManagerDelegate>

@property (nonatomic, readonly) CLLocation *location;
@property (nonatomic) BOOL isLocating;

+ (BOOL)willRequestAuthorization;
+ (CLAuthorizationStatus)authorizationStatus;

- (void)useVenueMode;
- (void)useMapMode;

- (void)start;
- (void)stop;

@end

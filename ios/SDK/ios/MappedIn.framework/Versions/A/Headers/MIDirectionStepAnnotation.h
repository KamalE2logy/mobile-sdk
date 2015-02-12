//
//  MIDirectionStepAnnotation.h
//  MappedIn
//
//  Created by Daniel Lichty on 2014-04-30.
//
//

#import "RMAnnotation.h"

@class MIDirectionStep;

@interface MIDirectionStepAnnotation : RMAnnotation

@property (nonatomic) BOOL selected;
@property (nonatomic, readonly) MIDirectionStep *step;

- (id)initWithMapView:(RMMapView *)mapView coordinate:(CLLocationCoordinate2D)aCoordinate step:(MIDirectionStep *)step;

@end

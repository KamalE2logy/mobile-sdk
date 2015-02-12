//
//  MIPolygonalRegion.h
//  MappedIn
//
//  Created by Daniel Lichty on 2014-03-30.
//
//

#import <CoreLocation/CoreLocation.h>

@interface MIPolygonalRegion : CLCircularRegion

- (id)initWithVertices:(NSArray *)vertices identifier:(NSString *)identifier;

@end

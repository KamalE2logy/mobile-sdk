//
//  MIPerspectiveTransform.h
//  MappedIn
//
//  Created by Daniel Lichty on 2014-03-26.
//
//

#import <Foundation/Foundation.h>

@interface MIPerspectiveTransform : NSObject

+ (MIPerspectiveTransform *)perspectiveWithFromPoints:(NSArray *)fromPoints toPoints:(NSArray *)toPoints;

- (CGPoint)transformPoint:(CGPoint)point;
- (CGPoint)inverseTransformPoint:(CGPoint)point;

@end

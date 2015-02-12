//
//  MIBounds.h
//  MappedIn
//
//  Created by Daniel Lichty on 12/23/2013.
//
//

#import <Foundation/Foundation.h>

@interface MIBounds : NSObject

@property (nonatomic, readonly) CGRect rect;
@property (nonatomic, readonly) BOOL hasPosition;
@property (nonatomic, readonly) BOOL hasSize;

+ (MIBounds *)emptyBounds;
+ (MIBounds *)boundsWithPoint:(CGPoint)point;
+ (MIBounds *)boundsWithRect:(CGRect)rect;

- (void)extendByAddingPoint:(CGPoint)point;
- (void)extendWithBounds:(MIBounds *)bounds;

- (MIBounds *)boundsByAddingPoint:(CGPoint)point;
- (MIBounds *)boundsByExtendingWithBounds:(MIBounds *)bounds;

@end

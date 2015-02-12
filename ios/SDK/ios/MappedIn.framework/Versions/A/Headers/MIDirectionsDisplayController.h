//
//  MIDirectionsDisplayController.h
//  MappedIn
//
//  Created by Daniel Lichty on 12/23/2013.
//
//

#import <Foundation/Foundation.h>

@class MIAPI;
@class MIVenue;
@class MILocation;
@class MINode;
@class MIDirectionStep;

#define MIDirectionOverviewStepIndex 0

@interface MIDirectionsDisplayController : NSObject

@property (nonatomic, strong) MIAPI *api;
@property (nonatomic, strong) MIVenue *venue;
@property (nonatomic, readonly) MIDirectionStep *currentStep;
@property (nonatomic, readonly) MIDirectionStep *overviewStep;
@property (nonatomic) NSInteger currentStepIndex;
@property (nonatomic, readonly) NSUInteger totalSteps;
@property (nonatomic, readonly) NSArray *instructionSteps;
@property (nonatomic, readonly) NSArray *annotationSteps;

- (void)setDestinationLocation:(MILocation *)destLocation destinationNode:(MINode *)destNode originLocation:(MILocation *)originLocation;
- (void)fetchDirectionsWithMethod:(NSString *)method arguments:(NSDictionary *)args;
- (void)cancel;
- (void)reset;

@end

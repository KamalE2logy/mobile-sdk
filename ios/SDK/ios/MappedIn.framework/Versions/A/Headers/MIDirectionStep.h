//
//  MIDirectionStep.h
//  MappedIn
//
//  Created by Daniel Lichty on 1/9/2014.
//
//

#import <Foundation/Foundation.h>

@class MINode;
@class MILocation;
@class MIVenue;

typedef NS_ENUM(NSUInteger, MIDirectionStepType)
{
  MIDirectionStepDirection,
  MIDirectionStepVortex,
  MIDirectionStepCheckpoint,
  MIDirectionStepArrival,
  MIDirectionStepOverview
};

typedef NS_ENUM(NSUInteger, MIDirectionClimb)
{
  MIDirectionClimbFlat,
  MIDirectionClimbUp,
  MIDirectionClimbDown
};

typedef NS_ENUM(NSUInteger, MIDirectionVortexType)
{
  MIDirectionVortexDoor,
  MIDirectionVortexStairs,
  MIDirectionVortexElevator,
  MIDirectionVortexEscalator
};

@interface MIDirectionStep : NSObject

@property (nonatomic, strong) MINode *node;
@property (nonatomic, strong) MILocation *location;
@property (nonatomic, strong) NSString *locationID;
@property (nonatomic, strong) NSString *instruction;
@property (nonatomic, strong) NSString *turn;
@property (nonatomic) MIDirectionStepType type;
@property (nonatomic) MIDirectionClimb climb;
@property (nonatomic) MIDirectionVortexType vortex;
@property (nonatomic) NSInteger index;

- (id)initWithDictionary:(NSDictionary *)dictionary venue:(MIVenue *)venue;
- (id)initWithOverviewInstruction:(NSString *)instruction node:(MINode *)node;
- (UIImage *)largeIcon;
- (UIImage *)markerIcon;

@end
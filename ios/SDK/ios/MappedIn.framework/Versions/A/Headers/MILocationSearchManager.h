//
//  MILocationSearchManager.h
//  MappedIn
//
//  Created by Daniel Lichty on 3/13/2014.
//
//

#import <Foundation/Foundation.h>

@class MIAPI;
@class MIVenue;

typedef NS_ENUM(NSUInteger, MILocationSearchStatus)
{
  MILocationSearchWillNotFetch,
  MILocationSearchWaitingForInput,
  MILocationSearchFetching,
  MILocationSearchResults,
  MILocationSearchNoResults
};

@protocol MILocationSearchDelegate;

@interface MILocationSearchManager : NSObject

@property (nonatomic) NSTimeInterval inputTimeoutInterval;
@property (nonatomic, readonly) MILocationSearchStatus searchStatus;
@property (nonatomic, readonly) NSArray *locations;
@property (nonatomic, assign) id<MILocationSearchDelegate> delegate;

- (id)initWithAPI:(MIAPI *)api venue:(MIVenue *)venue;
- (void)setQuery:(NSString *)query withFilteredLocationIDs:(NSArray *)locationIDs;
- (void)cancel;

@end

@protocol MILocationSearchDelegate <NSObject>

- (void)locationSearchManager:(MILocationSearchManager *)manager didUpdateFromStatus:(MILocationSearchStatus)previousStatus;

@optional

- (void)locationSearchManager:(MILocationSearchManager *)manager didReceiveQueryInput:(NSString *)query;

@end

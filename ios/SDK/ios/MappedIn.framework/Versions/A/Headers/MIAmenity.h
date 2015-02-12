//
//  MIAmenity.h
//  MappedIn
//
//  Created by Daniel Lichty on 12/16/2013.
//
//

#import <Foundation/Foundation.h>

@interface MIAmenity : NSObject

@property (nonatomic, readonly) NSString *name;
@property (nonatomic, readonly) NSString *identifier;
@property (nonatomic) BOOL active;

+ (NSMutableDictionary *)sharedAmenities;
+ (instancetype)amenityWithIdentifier:(NSString *)identifier name:(NSString *)name;

- (id)initWithName:(NSString *)name identifier:(NSString *)identifier;
- (UIImage *)iconImage;

@end

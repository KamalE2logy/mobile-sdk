//
//  MINode.h
//  MappedIn
//
//  Created by Daniel Lichty on 12/4/2013.
//
//

#import <Foundation/Foundation.h>

@interface MINode : NSObject

@property (nonatomic, readonly) NSString *identifier;
@property (nonatomic, readonly) NSString *mapID;
@property (nonatomic, readonly) CGFloat x, y;

- (id)initWithDictionary:(NSDictionary *)dict;

@end

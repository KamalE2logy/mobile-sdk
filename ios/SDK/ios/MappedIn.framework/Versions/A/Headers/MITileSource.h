//
//  MITileSource.h
//  MappedIn
//
//  Created by Dan Lichty on 2013-10-28.
//
//

#import "RMAbstractWebMapSource.h"

@class MIMap;

@interface MITileSource : RMAbstractWebMapSource

- (id)initWithMap:(MIMap *)map;

@end

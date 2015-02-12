//
//  MIConfig.h
//  MappedIn
//
//  Created by Daniel Lichty on 2014-07-31.
//
//

#import <Foundation/Foundation.h>

extern NSString *const MIConfigViewTintColor;
extern NSString *const MIConfigDefaultFont;

@interface MIConfig : NSObject
{
  NSMutableDictionary *_config;
}

+ (instancetype)sharedConfig;

@end

//
//  MIFloorControl.h
//  MappedInSDK
//
//  Created by Daniel Lichty on 2014-07-31.
//  Copyright (c) 2014 MappedIn. All rights reserved.
//

#import <UIKit/UIKit.h>

@class MIMap;

@interface MIFloorControl : UIControl

@property (nonatomic, strong) NSArray *maps;
@property (nonatomic, strong) MIMap *selectedMap;

@end

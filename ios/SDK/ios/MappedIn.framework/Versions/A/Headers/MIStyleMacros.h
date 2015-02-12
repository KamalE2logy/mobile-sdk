//
//  MIStyleMacros.h
//  MappedIn
//
//  Created by Daniel Lichty on 2014-07-24.
//
//

#ifndef MIStyleMacros_h
#define MIStyleMacros_h

#import "UIColor+HexString.h"
#import "UIFont+CustomDefault.h"

#define DARK_TEXT_COLOR       [UIColor colorWithHexString:@"242629"]
#define KEYLINE_COLOR         [UIColor colorWithHexString:@"b2b2b2"]
#define CELL_SEPARATOR_COLOR  [UIColor colorWithHexString:@"c9ced9"]
#define MAP_BACKGROUND_COLOR  [UIColor colorWithHexString:@"ebebeb"]

#define TABLE_CELL_HEIGHT 66.0

#define HUD_Y_OFFSET (120.0 - ([UIScreen mainScreen].bounds.size.height < 568 ? 22 : 0))

#endif

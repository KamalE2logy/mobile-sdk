//
//  MILocationDetailContainer.h
//  MappedIn
//
//  Created by Daniel Lichty on 12/12/2013.
//
//

#import <Foundation/Foundation.h>

typedef NS_ENUM(NSUInteger, MILocationDetailPosition)
{
  MILocationDetailHidden,
  MILocationDetailPreview,
  MILocationDetailExpanded,
  MILocationDetailFullscreen
};

@protocol MILocationDetailContainer <NSObject>

@property (nonatomic, readonly) MILocationDetailPosition locationDetailPosition;

@end

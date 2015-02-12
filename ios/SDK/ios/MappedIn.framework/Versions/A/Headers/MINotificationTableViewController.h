//
//  MINotificationTableViewController.h
//  MappedIn
//
//  Created by Daniel Lichty on 2014-06-16.
//
//

#import <UIKit/UIKit.h>

#import "MIDirectorySelectionDelegate.h"

@class MIVenue;

@interface MINotificationTableViewController : UITableViewController

@property (nonatomic, strong) MIVenue *venue;
@property (nonatomic, assign) id<MIDirectorySelectionDelegate> directorySelectionDelegate;

@end

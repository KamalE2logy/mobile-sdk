//
//  MICategoryLocationViewController.h
//  MappedIn
//
//  Created by Daniel Lichty on 11/21/2013.
//
//

#import <UIKit/UIKit.h>

#import "MIDirectorySelectionDelegate.h"

@interface MICategoryLocationViewController : UITableViewController

@property (nonatomic, strong) NSArray *locations;
@property (nonatomic, strong) NSString *categoryName;
@property (nonatomic, assign) id<MIDirectorySelectionDelegate> directorySelectionDelegate;

- (IBAction)markerButtonTapped;

@end

//
//  MIDirectionsViewController.h
//  MappedIn
//
//  Created by Daniel Lichty on 12/18/2013.
//
//

#import <UIKit/UIKit.h>

#import "MILocationSearchManager.h"

@class MIAPI;
@class MILocation;

@interface MIDirectionsViewController : UIViewController <UITableViewDataSource, UITableViewDelegate, UISearchBarDelegate, MILocationSearchDelegate>

@property (nonatomic, readonly) IBOutlet UITableView *tableView;
@property (nonatomic, readonly) IBOutlet UILabel *destinationLabel;
@property (nonatomic, readonly) IBOutlet NSLayoutConstraint *topContentVerticalSpace;

@property (nonatomic, strong) MIAPI *api;
@property (nonatomic, strong) MILocation *destinationLocation;

- (IBAction)cancelTapped;

@end

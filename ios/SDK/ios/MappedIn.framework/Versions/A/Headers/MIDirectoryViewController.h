//
//  MIDirectoryViewController.h
//  MappedIn
//
//  Created by Daniel Lichty on 11/15/2013.
//
//

#import <UIKit/UIKit.h>

#import "MIDirectorySelectionDelegate.h"
#import "MILocationSearchManager.h"

@class MIAPI;
@class MIVenue;

@interface MIDirectoryViewController : UIViewController <UISearchBarDelegate, UISearchDisplayDelegate, UITableViewDataSource, UITableViewDelegate, MILocationSearchDelegate>

@property (nonatomic, strong) MIAPI *api;
@property (nonatomic, strong) MIVenue *venue;
@property (nonatomic, strong) NSString *searchTerm;
@property (nonatomic, assign) id<MIDirectorySelectionDelegate> directorySelectionDelegate;
@property (nonatomic, strong) IBOutlet UITableView *tableView;
@property (nonatomic, readonly) NSArray *sortedLocations;

- (NSArray *)locationsForCategory:(NSString *)categoryName;

@end

//
//  MIVenueTableViewController.h
//  MappedIn
//
//  Created by Dan Lichty on 2013-07-23.
//
//

#import <UIKit/UIKit.h>

@protocol MIVenueTableDelegate;

@interface MIVenueTableViewController : UITableViewController <UISearchBarDelegate, UISearchDisplayDelegate>

@property (nonatomic, strong) IBOutlet UISearchBar *searchBar;

@property (nonatomic, strong) NSArray *venues;
@property (nonatomic, assign) id<MIVenueTableDelegate> venueTableDelegate;

- (IBAction)closeTapped;

@end
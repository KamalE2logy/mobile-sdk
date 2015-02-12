//
//  MILayerTableViewController.h
//  MappedIn
//
//  Created by Dan Lichty on 2013-07-23.
//
//

#import <SLExpandableTableView.h>

@protocol MILayerTableDelegate;
@class MIVenue;

@interface MILayerTableViewController : UIViewController <SLExpandableTableViewDatasource, SLExpandableTableViewDelegate>

@property (nonatomic, strong) MIVenue *venue;
@property (nonatomic, strong) IBOutlet UITableView *tableView;
@property (nonatomic, assign) id<MILayerTableDelegate> layerDelegate;

@end

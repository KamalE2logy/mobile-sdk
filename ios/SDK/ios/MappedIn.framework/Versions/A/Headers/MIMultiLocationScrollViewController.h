//
//  MIMultiLocationScrollViewController.h
//  MappedIn
//
//  Created by Daniel Lichty on 12/11/2013.
//
//

#import <UIKit/UIKit.h>

#import "MILocationDetailContainer.h"

@class MILocationDetailViewController;
@class MIAPI;
@class MINotification;

@interface MIMultiLocationScrollViewController : UIViewController <MILocationDetailContainer, UIScrollViewDelegate>

@property (nonatomic, readonly) IBOutlet UIScrollView *scrollView;
@property (nonatomic, strong) NSArray *locations;
@property (nonatomic, readonly) MILocationDetailViewController *currentDetailVC;
@property (nonatomic, strong) MIAPI *api;
@property (nonatomic, readonly) NSInteger currentPage;

- (void)setNotification:(MINotification *)notification;

@end

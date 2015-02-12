//
//  MILocationDetailViewController.h
//  MappedIn
//
//  Created by Dan Lichty on 2013-09-05.
//
//

#import <UIKit/UIKit.h>

@class MILocationContentScrollView;
@class MIAPI;
@class MILocation;
@class MINotification;
@class RCSLabeledIconButton;

@interface MILocationDetailViewController : UIViewController

@property (nonatomic, strong) IBOutlet UIView *textContainer;
@property (nonatomic, strong) IBOutlet UILabel *nameLabel;
@property (nonatomic, strong) IBOutlet UILabel *mapLabel;
@property (nonatomic, strong) IBOutlet UIImageView *logoView;
@property (nonatomic, strong) IBOutlet UIView *contentSpacer;
@property (nonatomic, strong) IBOutlet MILocationContentScrollView *scrollView;
@property (nonatomic, strong) IBOutlet RCSLabeledIconButton *directionsButton;
@property (nonatomic, strong) IBOutlet RCSLabeledIconButton *mapButton;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *leftMarginConstraint, *rightMarginConstraint, *nameMapSpacingConstraint;

@property (nonatomic, strong) MIAPI *api;
@property (nonatomic, strong) MILocation *location;
@property (nonatomic, strong) MINotification *selectedNotification;

- (IBAction)directionsTapped;
- (IBAction)mapTapped;

- (void)scrollToSelectedNotificationAnimated:(BOOL)animated;

@end

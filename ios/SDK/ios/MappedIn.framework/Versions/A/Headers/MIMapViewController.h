//
//  MIMapViewController.h
//  MappedIn
//
//  Created by Dan Lichty on 2013-07-11.
//
//

#import <ECSlidingViewController.h>

#import <FXPageControl.h>

#import "MILayerTableDelegate.h"
#import "MIVenueTableDelegate.h"
#import "MIDirectorySelectionDelegate.h"
#import "MILocationDetailContainer.h"

@class MIVenue;
@class MIAPI;
@class MIMapView;
@class MIVenueFooterView;
@class MINearbyVenueFooterView;
@class MIFloorControl;
@class FXPageControl;

@interface MIMapViewController : UIViewController <
  MIVenueTableDelegate,
  MILayerTableDelegate,
  MIDirectorySelectionDelegate,
  MILocationDetailContainer,
  UIGestureRecognizerDelegate,
  FXPageControlDelegate,
  UISearchBarDelegate
>

@property (nonatomic, strong) IBOutlet UIView *locationDetailContainerView, *errorContainerView;
@property (nonatomic, strong) IBOutlet UILabel *errorMessageLabel;
@property (nonatomic, strong) IBOutlet UIButton *errorActionButton;
@property (nonatomic, strong) IBOutlet FXPageControl *pageControl;
@property (nonatomic, strong) IBOutlet MIVenueFooterView *venueFooterView;
@property (nonatomic, strong) IBOutlet MINearbyVenueFooterView *nearbyVenueFooterView;

@property (nonatomic, strong) MIVenue *venue;
@property (nonatomic, readonly) MIAPI *api;
@property (nonatomic, readonly) MIMapView *mapView;
@property (nonatomic, readonly) MIFloorControl *floorControl;

// bar button item to display events list; has numeric badge for unseen events
@property (nonatomic, readonly) UIBarButtonItem *eventsBarButtonItem;

// bar button item to display menu, if MIMapViewController is top view of ECSlidingViewController
@property (nonatomic, readonly) UIBarButtonItem *menuBarButtonItem;

// whether to allow dragging the navigation bar to reveal the menu
@property (nonatomic) BOOL allowMenuPanning;

// which direction to slide the map view to show the menu
@property (nonatomic) ECSide menuSlideDirection;

// slug of the venue to load at startup
@property (nonatomic, strong) NSString *initialVenueSlug;

// whether to use location services to determine nearby venues and whether to allow the venue list to appear (default: NO)
@property (nonatomic) BOOL allowVenueList;

// whether to overlay a control for changing maps in the default map group (default: NO)
@property (nonatomic) BOOL useFloorControl;

- (id)init;

- (void)setAPIKey:(NSString *)apiKey secret:(NSString *)apiSecret version:(NSString *)version;

- (void)presentVenueList;
- (void)shouldResetSlidingPosition;

- (void)fetchDirectionsWithMethod:(NSString *)method arguments:(NSDictionary *)params;

- (BOOL)checkEULAVersion;
- (void)handleAcceptedEULA:(NSNotification *)note;

- (void)performActionWhenViewIsVisible:(void (^)(void))action;
- (void)performActionWhenViewIsVisible:(dispatch_block_t)action withDelay:(CGFloat)delay;

@end

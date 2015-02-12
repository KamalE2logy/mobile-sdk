//
//  MINearbyVenueFooterView.h
//  MappedIn
//
//  Created by Daniel Lichty on 2014-08-28.
//
//

#import <UIKit/UIKit.h>

@class MIVenue;

@interface MINearbyVenueFooterView : UIView

@property (nonatomic, readonly) IBOutlet UILabel *descriptionLabel;
@property (nonatomic, readonly) IBOutlet UIButton *cancelButton, *changeButton;

- (void)setVenue:(MIVenue *)venue;

@end

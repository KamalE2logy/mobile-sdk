//
//  MIVenueCell.h
//  MappedIn
//
//  Created by Daniel Lichty on 2/3/2014.
//
//

#import <UIKit/UIKit.h>

@interface MIVenueCell : UITableViewCell

@property (nonatomic, readonly) UIImageView *coverPhotoView;
@property (nonatomic, readonly) UILabel *nameLabel;
@property (nonatomic, readonly) UILabel *addressLabel;

- (void)setDistanceText:(NSString *)distance;

@end

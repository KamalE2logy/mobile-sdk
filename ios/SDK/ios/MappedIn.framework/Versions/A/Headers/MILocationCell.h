//
//  MILocationCell.h
//  MappedIn
//
//  Created by Daniel Lichty on 11/18/2013.
//
//

#import <UIKit/UIKit.h>

@interface MILocationCell : UITableViewCell

@property (nonatomic, strong) UIColor *logoBackgroundColor;

@property (nonatomic, readonly) IBOutlet UIImageView *logoImageView;
@property (nonatomic, readonly) IBOutlet UILabel *nameLabel;
@property (nonatomic, readonly) IBOutlet UILabel *mapLabel;
@property (nonatomic, readonly) IBOutlet NSLayoutConstraint *nameMapSpacingConstraint;

- (void)setNameMapSpacingConstraintEnabled:(BOOL)enable;

@end

//
//  MINotificationView.h
//  MappedIn
//
//  Created by Daniel Lichty on 2/13/2014.
//
//

#import <UIKit/UIKit.h>

@interface MIDarkenButton : UIButton
@end

@interface MINotificationView : UIView

@property (nonatomic, readonly) IBOutlet UIImageView *imageView;
@property (nonatomic, readonly) IBOutlet UILabel *nameLabel;
@property (nonatomic, readonly) IBOutlet UILabel *descLabel;
@property (nonatomic, readonly) IBOutlet UILabel *dateLabel;
@property (nonatomic, readonly) IBOutlet UIButton *imageButton;

+ (MINotificationView *)viewFromNib;

@end

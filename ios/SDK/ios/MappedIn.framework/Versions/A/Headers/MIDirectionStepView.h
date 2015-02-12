//
//  MIDirectionStepView.h
//  MappedIn
//
//  Created by Daniel Lichty on 1/12/2014.
//
//

#import <UIKit/UIKit.h>

@class MIDirectionStep;

@interface MIDirectionStepView : UIView

@property (nonatomic, strong) MIDirectionStep *step;
@property (nonatomic, readonly) IBOutlet UIView *contentView;
@property (nonatomic, readonly) IBOutlet UIImageView *iconView;
@property (nonatomic, readonly) IBOutlet UILabel *instructionLabel;

- (void)loadContentView;

@end

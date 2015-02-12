//
//  MIMapCell.h
//  MappedIn
//
//  Created by Daniel Lichty on 11/19/2013.
//
//

#import <UIKit/UIKit.h>
#import <SLExpandableTableView.h>

@interface MIMapCell : UITableViewCell <UIExpandingTableViewCell>

@property (nonatomic) BOOL selectable;
@property (nonatomic) BOOL chosen;
@property (nonatomic) BOOL partial;

@property (nonatomic, readonly) IBOutlet UILabel *shortNameLabel;
@property (nonatomic, readonly) IBOutlet NSLayoutConstraint *shortNameLabelWidthConstraint, *shortNameLabelMarginConstraint;

@end

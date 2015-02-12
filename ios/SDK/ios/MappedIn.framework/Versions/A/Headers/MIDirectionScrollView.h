//
//  MIDirectionScrollView.h
//  MappedIn
//
//  Created by Daniel Lichty on 1/12/2014.
//
//

#import <UIKit/UIKit.h>

@class MIDirectionsDisplayController;

@interface MIDirectionScrollView : UIScrollView <UIScrollViewDelegate>

@property (nonatomic, strong) MIDirectionsDisplayController *directionsController;

@property (nonatomic, readonly, getter = indexOfCurrentPage) NSInteger currentPage;

- (void)populateViews;

@end

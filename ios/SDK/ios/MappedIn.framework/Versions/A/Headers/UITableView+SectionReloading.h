//
//  UITableView+SectionReloading.h
//  MappedIn
//
//  Created by Daniel Lichty on 3/14/2014.
//
//

#import <UIKit/UIKit.h>

@interface UITableView (SectionReloading)

- (void)reloadSection:(NSUInteger)section numberOfRows:(NSUInteger)numberOfRows animation:(UITableViewRowAnimation)animation;

@end

//
//  MIDirectorySelectionDelegate.h
//  MappedIn
//
//  Created by Daniel Lichty on 12/6/2013.
//
//

#import <Foundation/Foundation.h>

@class MINotification;
@class MIMap;

@protocol MIDirectorySelectionDelegate <NSObject>

- (void)controller:(id)controller didSelectLocations:(NSArray *)locations withSearchText:(NSString *)searchText asCategory:(BOOL)asCategory;
- (void)controller:(id)controller didSelectNotification:(MINotification *)notification;
- (void)controller:(id)controller didSelectMap:(MIMap *)map;

@end

//
//  MILayerTableDelegate.h
//  MappedIn
//
//  Created by Dan Lichty on 2013-07-26.
//
//

@class MILayerTableViewController;
@class MIMap;

@protocol MILayerTableDelegate <NSObject>

- (void)layerTableDidTapChangeVenue:(MILayerTableViewController *)layerTable;
- (void)layerTableDidTapScanQR:(MILayerTableViewController *)layerTable;
- (void)layerTable:(MILayerTableViewController *)layerTable didSelectMap:(MIMap *)map;

@end

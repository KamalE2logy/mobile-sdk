//
//  MIVenueTableDelegate.h
//  MappedIn
//
//  Created by Dan Lichty on 2013-07-24.
//
//

@class MIVenueTableViewController;
@class MIVenue;

@protocol MIVenueTableDelegate <NSObject>

- (void)venueTableDidTapClose:(MIVenueTableViewController *)venueTable;
- (void)venueTable:(MIVenueTableViewController *)venueTable didSelectVenue:(MIVenue *)venue;

@end

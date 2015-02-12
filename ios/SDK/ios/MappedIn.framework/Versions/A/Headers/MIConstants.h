//
//  MIConstants.h
//  MappedIn
//
//  Created by Daniel Lichty on 12/23/2013.
//
//

NSBundle * MIResourceBundle();
NSString * MILocalizedString(NSString *, NSString *);

#pragma mark - BEGIN AUTOGEN (constant_vigilance generates everything below)

#pragma mark API methods

extern NSString *const MIGetDirectionsToLocation;
extern NSString *const MIGetDirectionsToLocations;
extern NSString *const MIGetDirectionsToNode;
extern NSString *const MIGetAllVenues;
extern NSString *const MIGetVenueByURL;
extern NSString *const MIGetCategoriesByVenue;
extern NSString *const MIGetMobileNotificationsByVenue;
extern NSString *const MIGetLocationsByGroup;
extern NSString *const MIGetLocationsByQuery;
extern NSString *const MIGetMapsByVenue;
extern NSString *const MIGetAmenitiesByVenue;
extern NSString *const MIGetPortkeyByCode;
extern NSString *const MIGetLocationByID;
extern NSString *const MIVerifyEmail;
extern NSString *const MITrackFromMobile;

#pragma mark User defaults

extern NSString *const MIAcceptedEULAVersion;
extern NSString *const MINotificationMarkersActive;
extern NSString *const MIExplainedSwipeToDismissCard;
extern NSString *const MIExplainedTapMapToShowLocation;
extern NSString *const MIExplainedScrollThroughDirections;
extern NSString *const MIExplainedScrollThroughLocations;
extern NSString *const MILatestVenueSlug;

#pragma mark Settings

extern NSString *const MISettingUseAccessibleDirections;

#pragma mark Misc

extern NSInteger const MIPackagedEULAVersion;
extern NSString *const MIMobilePerspectiveKey;
extern NSString *const MIDefaultMapGroupName;

#pragma mark Notifications

extern NSString *const MIWillFetchDirections;
extern NSString *const MIDidGetDirectionsMarkers;
extern NSString *const MIDidFetchDirectionsSteps;
extern NSString *const MIDidFetchDirectionsPath;
extern NSString *const MIDirectionsFetchFailed;
extern NSString *const MILocationManagerDidUpdateLocation;
extern NSString *const MILocationManagerDidStopUpdating;
extern NSString *const MIDirectionsOriginLocationSelected;
extern NSString *const MIVenueAuthenticated;
extern NSString *const MIDidAcceptEULA;
extern NSString *const MIMapLocationSelected;
extern NSString *const MIDidArchiveCard;
extern NSString *const MIDidScrollToNewLocationPage;
extern NSString *const MIDidScrollToNewDirectionsStepPage;
extern NSString *const MIShouldResetMapViewControllerSlidingPosition;

#pragma mark Math

extern NSInteger const MIMapTileZoomOffset;

#pragma mark Card identifiers

extern NSString *const MITutorialCardPullToDismiss;
extern NSString *const MITutorialCardTapForInfo;
extern NSString *const MITutorialCardSlideToHide;

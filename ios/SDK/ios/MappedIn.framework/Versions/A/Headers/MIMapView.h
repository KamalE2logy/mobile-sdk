//
//  MIMapView.h
//  MappedIn
//
//  Created by Dan Lichty on 2013-10-24.
//
//

#import "RMMapView.h"

@class MIVenue;
@class MILocation;
@class MINode;
@class MIDirectionsDisplayController;
@class MIDirectionStepAnnotation;

@interface MIMapView : RMMapView <RMMapViewDelegate>

@property (nonatomic, strong) MIVenue *venue;
@property (nonatomic, strong) MIDirectionsDisplayController *directionsController;

@property (nonatomic, getter = isShowingDirections) BOOL showingDirections;
@property (nonatomic) CGRect visibleRect;

@property (nonatomic, strong) MILocation *selectedLocation;
@property (nonatomic, strong) MINode *selectedNode;
@property (nonatomic, strong) NSArray *selectedLocations;

@property (nonatomic, readonly) NSArray *selectedLocationAnnotations;
@property (nonatomic, readonly) RMAnnotation *selectedNodeAnnotation;
@property (nonatomic, readonly) NSArray *dotAnnotations;

@property (nonatomic, strong) MINode *directionsOriginNode;
@property (nonatomic, strong) NSArray *directionsDestinationNodes;

@property (nonatomic, readonly) RMAnnotation *directionsOriginAnnotation;
@property (nonatomic, readonly) NSArray *directionsDestinationAnnotations;

@property (nonatomic, strong) NSDictionary *mapPolylines;
@property (nonatomic, readonly) NSArray *polylineAnnotations;

@property (nonatomic, strong) NSDictionary *mapDirectionsLandmarks;
@property (nonatomic, readonly) NSArray *directionsLandmarkAnnotations;
@property (nonatomic, readonly) MIDirectionStepAnnotation *selectedLandmarkAnnotation;

- (void)centerOnSelectedLocationInRect:(CGRect)rect animated:(BOOL)animated;
- (void)centerOnSelectedNodeInRect:(CGRect)rect animated:(BOOL)animated;
- (void)centerOnDotsInRect:(CGRect)rect animated:(BOOL)animated;
- (void)centerOnDirectionsPathInRect:(CGRect)rect animated:(BOOL)animated;
- (void)centerOnMapInRect:(CGRect)rect animated:(BOOL)animated;
- (void)setCenterCoordinate:(CLLocationCoordinate2D)coordinate inRect:(CGRect)rect animated:(BOOL)animated;

- (void)showTapExplanationHUD;

@end

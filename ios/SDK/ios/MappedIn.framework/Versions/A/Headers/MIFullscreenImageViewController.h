//
//  MIFullscreenImageViewController.h
//  MappedIn
//
//  Created by Daniel Lichty on 2014-08-18.
//
//

#import <UIKit/UIKit.h>

@interface MIFullscreenImageViewController : UIViewController

- (id)initWithThumbnail:(UIImage *)thumbnailImage withSize:(CGSize)thumbnailSize andCenter:(CGPoint)centerPoint backgroundColor:(UIColor *)backgroundColor loadImage:(NSURL *)fullSizeImageURL;

@end

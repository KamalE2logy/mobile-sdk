//
//  MIWebViewController.h
//  MappedIn
//
//  Created by Daniel Lichty on 12/3/2013.
//
//

#import <UIKit/UIKit.h>

@interface MIWebViewController : UIViewController <UIBarPositioningDelegate, UIWebViewDelegate>

@property (nonatomic, readonly) UIWebView *webView;

- (void)loadURL:(NSURL *)url;

@end

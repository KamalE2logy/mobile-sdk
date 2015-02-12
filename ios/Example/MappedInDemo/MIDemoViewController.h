//
//  MIDemoViewController.h
//  MappedInSDK
//
//  Created by Daniel Lichty on 07/25/2014.
//  Copyright (c) 2014 MappedIn. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MIDemoViewController : UIViewController

@property (nonatomic, strong) IBOutlet UITextField *apiKeyField, *secretKeyField, *venueSlugField;

- (IBAction)showMap:(id)sender;

@end

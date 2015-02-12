//
//  MIAssetImage.h
//  MappedIn
//
//  Created by Daniel Lichty on 11/18/2013.
//
//

#import <Foundation/Foundation.h>

typedef NS_ENUM(NSUInteger, MIAssetImageType)
{
  MIAssetImageLogo,
  MIAssetImagePicture,
  MIAssetImageNotification
};

@interface MIAssetImageDisplayInfo : NSObject

@property (nonatomic, strong) NSString *url;
@property (nonatomic, strong) UIColor *backgroundColor;
@property (nonatomic) MIAssetImageType type;

+ (MIAssetImageDisplayInfo *)imageDisplayInfoWithURL:(NSString *)url backgroundColor:(UIColor *)color type:(MIAssetImageType)type;

@end

@interface MIAssetImage : NSObject

- (id)initWithDictionary:(NSDictionary *)dict type:(MIAssetImageType)type;
- (id)initWithDictionary:(NSDictionary *)dict type:(MIAssetImageType)type backgroundColor:(UIColor *)color;

@property (nonatomic, readonly) MIAssetImageDisplayInfo *imageInfo;
@property (nonatomic, readonly) MIAssetImageDisplayInfo *thumbnailInfo;

@end

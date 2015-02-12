//
//  MIGeometry.h
//  MappedIn
//
//  Created by Dan Lichty on 2013-09-20.
//
//

CGRect scaleAndCenterRectInsideRect(CGRect rect, CGRect containerRect);

CGRect boundingBoxForRectWithIncline(CGRect rect, CGFloat incline);

CGSize virtualSizeForContentSizedToContainerWithContentMode(CGSize originalContentSize, CGSize containerSize, UIViewContentMode contentMode);

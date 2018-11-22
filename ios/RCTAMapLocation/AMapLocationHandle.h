//
//  AMapLocationHandle.h
//  RCTAMapLocation
//
//  Created by Dreamfan on 2018/11/16.
//
#import <Foundation/Foundation.h>
#import <AMapFoundationKit/AMapFoundationKit.h>
#import <AMapLocationKit/AMapLocationKit.h>
typedef void(^LocationBlock) (NSDictionary *locationInfo,NSString *err);
@interface AMapLocationHandle : NSObject

+ (AMapLocationHandle *) getInstance;
+ (void)initAMapLocation:(NSString *) appKey;
- (void)getPosition:(LocationBlock) locationBlock;

@end

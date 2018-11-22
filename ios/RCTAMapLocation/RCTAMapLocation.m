//
//  RCTAMapLocation.m
//  RCTAMapLocation
//
//  Created by Dreamfan on 2018/11/16.
//

#import "RCTAMapLocation.h"
#import "AMapLocationHandle.h"

@implementation RCTAMapLocation


RCT_EXPORT_MODULE();
RCT_EXPORT_METHOD(getCurrentPosition:(RCTResponseSenderBlock)success onError:(RCTResponseSenderBlock)error){
    [[AMapLocationHandle getInstance] getPosition:^(NSDictionary *locationInfo, NSString *err) {
                if(err){
                    error(@[err]);
                }else{
                    success(@[locationInfo]);
                }
    }];
};

@end

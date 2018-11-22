//
//  AMapLocationHandle.m
//  RCTAMapLocation
//
//  Created by Dreamfan on 2018/11/16.
//
#import "AMapLocationHandle.h"
#import "AMapUtils.h"

static AMapLocationHandle *locationHandle;
@interface AMapLocationHandle()
@property (nonatomic, strong) AMapLocationManager *locationManager;
@end

@implementation AMapLocationHandle

+ (AMapLocationHandle *) getInstance{
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        locationHandle = [[self alloc]init];
    });
    return locationHandle;
}

+ (void)initAMapLocation:(NSString *) appKey{
    [AMapServices sharedServices].apiKey = appKey;
}

- (void)getPosition:(LocationBlock) locationBlock{
    self.locationManager = [[AMapLocationManager alloc] init];
    [self.locationManager setDesiredAccuracy:kCLLocationAccuracyBest];
    self.locationManager.locationTimeout = 10;
    self.locationManager.reGeocodeTimeout = 10;

    [self.locationManager requestLocationWithReGeocode:YES completionBlock:^(CLLocation *location, AMapLocationReGeocode *regeocode, NSError *error) {
        if(error){
            NSLog(@"locError:{%ld - %@};", (long)error.code, error.localizedDescription);
            
            if (error.code == AMapLocationErrorLocateFailed)
            {
                locationBlock(nil,[NSString stringWithFormat:(@"%@%@"),[AMapUtils nilToEmptyString:error.domain],@"定位失败,请重试"] );
            }
        }
        else{
            locationBlock(@{@"address": [self handleRegeocode:regeocode],
                            @"coodrs": [self handleLatitudeAndLongitude:location]
                            }, nil);
        }
    }];
}

- (NSDictionary *)handleLatitudeAndLongitude:(CLLocation *)location{
    if (location) {
        CLLocationCoordinate2D coordinate = location.coordinate;
        NSString *latitude = [NSString stringWithFormat:@"%lf",coordinate.latitude];
        NSString *longitude = [NSString stringWithFormat:@"%lf",coordinate.longitude];
        NSString *altitude = [NSString stringWithFormat:@"%lf",location.altitude];
        return @{
                 @"latitude": latitude,
                 @"longitude": longitude,
                 @"altitude":altitude
                 };
    }
    return @{};
}

- (NSDictionary *)handleRegeocode:(AMapLocationReGeocode *)regeocode{
    if (regeocode) {
        return @{
                 @"country": [AMapUtils nilToEmptyString:regeocode.country],
                 @"province": [AMapUtils nilToEmptyString:regeocode.province],
                 @"city": [AMapUtils nilToEmptyString:regeocode.city],
                 @"district": [AMapUtils nilToEmptyString:regeocode.district],
                 @"street": [AMapUtils nilToEmptyString:regeocode.street],
                 @"address":[NSString stringWithFormat:@"%@%@%@%@",[AMapUtils nilToEmptyString:regeocode.province],[AMapUtils nilToEmptyString:regeocode.city],[AMapUtils nilToEmptyString:regeocode.district],[AMapUtils nilToEmptyString:regeocode.street]],
                 @"cityCode": [AMapUtils nilToEmptyString:regeocode.citycode],
                 @"adCode": [AMapUtils nilToEmptyString:regeocode.adcode],
                 @"streetNum": [AMapUtils nilToEmptyString:regeocode.number]
                 };
    }
    return @{};
}

@end

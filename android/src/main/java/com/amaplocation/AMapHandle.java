package com.amaplocation;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

/**
 * AMapHandle
 *
 * @author Dreamfan
 * @date 2018/11/21
 */
public class AMapHandle implements AMapLocationListener {
    private static final String TAG = "AMapHandle";
    private static AMapLocationClient mLocationClient = null;
    private static AMapLocationClientOption mLocationOption;
    private Callback success, error;

    public void initModule(Context context) {
        // 初始化定位
        mLocationClient = new AMapLocationClient(context);
        // 初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        // 设置定位监听
        mLocationClient.setLocationListener(this);
        //设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        // 设置为高精度定位模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置单次定位
        mLocationOption.setOnceLocation(true);
        // 设置获取地址信息
        mLocationOption.setNeedAddress(true);
        // 给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    public void startLocation(Callback success, Callback error) {
        try {
            this.success = success;
            this.error = error;
            mLocationClient.startLocation();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            amapLocationToObject(aMapLocation);
        }
    }

    private void amapLocationToObject(AMapLocation amapLocation) {

        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                WritableMap map = Arguments.createMap();
                WritableMap locatinoMap = Arguments.createMap();
                locatinoMap.putString("latitude", String.valueOf(amapLocation.getLatitude()));
                locatinoMap.putString("longitude", String.valueOf(amapLocation.getLongitude()));
                locatinoMap.putString("altitude", String.valueOf(amapLocation.getAltitude()));
                map.putMap("coodrs", locatinoMap);
                WritableMap addressMap = Arguments.createMap();
                addressMap.putString("conutry", amapLocation.getCountry());
                addressMap.putString("province", amapLocation.getProvince());
                addressMap.putString("city", amapLocation.getCity());
                addressMap.putString("district", amapLocation.getDistrict());
                addressMap.putString("street", amapLocation.getStreet());
                addressMap.putString("address", amapLocation.getAddress());
                addressMap.putString("cityCode", amapLocation.getCityCode());
                addressMap.putString("adCode", amapLocation.getAdCode());
                addressMap.putString("streetNum", amapLocation.getStreetNum());
                map.putMap("address", addressMap);

                success.invoke(map);

            } else {
                WritableMap map = Arguments.createMap();
                map.putString("errInfo", amapLocation.getErrorInfo());
                map.putDouble("errCode", amapLocation.getErrorCode());

                error.invoke(map);
            }

        }
    }
}

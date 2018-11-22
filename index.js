/**
 *
 * @flow
 * @format
 * Created by Dreamfan  on 2018/7/19.
 *
 */

import {Platform, NativeAppEventEmitter, PermissionsAndroid} from 'react-native';
import {Utils} from 'react-native-mint-services';

const AMapLocation = require('react-native').NativeModules.AMapLocation;

class RNAMapLocation {
  constructor() {
  };

  /**
   * 获取当前定位信息
   * @param onSuccess
   * @param onError
   */
  getCurrentPosition(onSuccess: () => void, onError: () => void) {
    onError = onError || function() {
    };
    if (Platform.OS === 'ios') {
      AMapLocation.getCurrentPosition(onSuccess, onError);
    } else {
      if (Platform.Version > 21) {
        let promise = PermissionsAndroid.check(PermissionsAndroid.PERMISSIONS.CAMERA);
        promise.then((data) => {
          if (data) {
            AMapLocation.getCurrentPosition(onSuccess, onError);
          } else {
            this._requestCameraPermission(onSuccess, onError);
          }
        });
      } else {
        AMapLocation.getCurrentPosition(onSuccess, onError);
      }
    }
  }

  async _requestCameraPermission(onSuccess, onError) {
    try {
      const granted = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.ACCESS_COARSE_LOCATION,
      );
      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        AMapLocation.getCurrentPosition(onSuccess, onError);
      } else {
        Utils.toast('关闭定位权限将影响部分功能使用');
      }
    } catch (err) {
    }
  }
};

module.exports = new RNAMapLocation();




### ReactNative 高德地图

## Installation

Using npm:

```shell
npm install --save react-native-mint-amap
```

or using yarn:

```shell
yarn add react-native-mint-amap
```
## Android

### Step1  Update Gradle Settings
    // file: android/settings.gradle
    ...
    
    include ':react-native-mint-amap'
    project(':react-native-mint-amap').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-mint-amap')

### Step2 Update app Gradle Build
    // file: android/app/build.gradle
    ...
    
    dependencies {
        ...
        implementation project(':react-native-mint-amap')
    }

### Step4 Register React Package 
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
        new MainReactPackage(),
        new AMaplocationPackage()
      ); // <-- Register package here
    }

### Step5 Init module  
    // file: MainActivity.class   
    AMapLocationModule.getInstance().initModule(this);

### Step6 - Add service, api key and permissions
    <!--用于进行网络定位-->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"></uses-permission>
    <!--用于访问GPS定位-->
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
    <!--获取运营商信息，用于支持提供运营商信息相关的接口-->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
    <!--用于访问wifi网络信息，wifi信息会用于进行网络定位-->
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
    <!--这个权限用于获取wifi的获取权限，wifi信息会用来进行网络定位-->
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
    <!--用于访问网络，网络定位需要上网-->
    <uses-permission android:name="android.permission.INTERNET"></uses-permission>
    <!--用于读取手机当前的状态-->
    <uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
    <!--写入扩展存储，向扩展卡写入数据，用于写入缓存定位数据-->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
    <uses-permission android:name="android.permission.INTERNET" />
    ...
     <application
          android:allowBackup="true"
          android:label="@string/app_name"
          android:icon="@mipmap/ic_launcher"
          android:theme="@style/AppTheme">
          <service android:name="com.amap.api.location.APSService"></service>
          <!--添加appKey-->
          <meta-data
             android:name="com.amap.api.v2.apikey"
             android:value="Your api key here"/>
    </application>
    ...


## iOS

Add the following line to your build targets in your `Podfile`

`pod 'RCTAMapLocation', :path => '../node_modules/react-native-mint-amap'`

Then run `pod install`


### Usage

    import RNAMapLocation from 'react-native-mint-amap';
        
    getCurrentPosition(onSucess,onError) 获取当前定位地址
    
        onSucess 定位成功
        onError 定位失败
        
    onSucess返回参数
    ---coodrs      经纬度海拔高度
            ---latitude     纬度
            ---longitude    精度
            ---altitude     海拔高度
    ---address     详细地理位置信息
    
            ---conutry      国家
            ---province     省份
            ---city         城市
            ---district     区
            ---street       街道
            ---address      详细地址  XX省XX市XX区XX街道XX号
            ---cityCode     城市编码
            ---adCode       区域编码
            ---streetNum    街道编号
    
    onErro(errMsg)错误信息
    
### 完整示例

    import RNAMapLocation from 'react-native-mint-amap';
    
    
    RNAMapLocation.getCurrentPosition(
      data => {
        alert(JSON.stringify(data));
      },
      error => {
        alert(JSON.stringify(error));
      },
    );



### ReactNative 地图定位Fly包

简介：android和iOS通用定位功能包，该包仅提供定位功能。


## Android安装指南

**Step1 NPM install**

    pakage add:
    "fly-react-native-amaplocation": "file:../rn-plugins/fly-react-native-amaplocation"
    then "npm install"

## Step2  Update Gradle Settings
    // file: android/settings.gradle
    ...
    
    include ':reactamaplocation'
    project(':reactamaplocation').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-amap-location')

### Step3 Update app Gradle Build
    // file: android/app/build.gradle
    ...
    
    dependencies {
        ...
        compile project(':reactamaplocation')
    }

### Step4 Register React Package 
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
        new MainReactPackage(),
        new AMapLocationReactPackage()); // <-- Register package here
    }

### Step 5 - Add service, api key and permissions
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


## iOS安装指南
1.  fly-react-native-amaplocation包导入node_modules
1.  将RCT***.xcodeproj引入主工程中
2.  并将该.a文件引入工程
3.  添加定位权限。建议在plist文件中同时添加NSLocationWhenInUseUsageDescription、NSLocationAlwaysUsageDescription和NSLocationAlwaysAndWhenInUseUsageDescription权限申请
4. 在主工程添加Framework的搜索路径: "$(SRCROOT)/../node_modules/fly-react-native-amaplocation/iOS/RCTAMapLocation"
5. 添加依赖包libstdc++.6.0.9.tbd、libc++.tbd、libz.tbd、Security.framework、CoreLocation.framework、CoreTelephony.framework、SystemConfiguration.framework、JavaScriptcore.framework
6. AMapFoundationKit.framework、AMapLocationKit.framework也要添加
6. 在rn.plist 配“AMapKey”= 你的高德key


### 使用及说明

    import {initAmaplocation,getCurrentPosition}from 'fly-react-native-amaplocation'
    
    initAmaplocation 初始化定位
    
    getCurrentPosition(onSucess,onErro) 获取当前定位地址
    
        onSucess 定位成功
        onErro 定位失败
        
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
    
    onErro(errMsg)错误信息字符串
    
### 完整示例

    import {initAmaplocation,getCurrentPosition}from 'fly-react-native-amaplocation'
    
    
     componentDidMount() {
        initAmaplocation();
        getCurrentPosition((params) => {
            console.log(params.address.adress);
        }, (error) => {
            console.log(error);
        })
    }

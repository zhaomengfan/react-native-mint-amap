-keep class * implements com.facebook.react.ReactPackage { *; }
-keepclassmembers class com.amaplocation.AMaplocationModule {
    @com.facebook.react.bridge.ReactMethod *;
}
#amap
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

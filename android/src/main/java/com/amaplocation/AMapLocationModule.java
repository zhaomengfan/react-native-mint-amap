package com.amaplocation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.telecom.Call;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.PermissionListener;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.views.text.ReactFontManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Dreamfan
 */
public class AMapLocationModule extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "AMapLocation";
    private static AMapHandle instance = null;

    public AMapLocationModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public static synchronized AMapHandle getInstance(){
        if(null==instance){
            instance = new AMapHandle();
            return instance;
        }
        return instance;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    /**
     * 获取当前定位信息
     * @param success
     * @param error
     */
    @ReactMethod
    public void getCurrentPosition(Callback success, Callback error) {
        instance.startLocation(success,error);
    }
}



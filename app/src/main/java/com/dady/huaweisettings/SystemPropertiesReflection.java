// HuaweiSettings - android_packages_apps_HuaweiSettings
// Simple Java app that provides preferences specific to the Huawei P9 Lite line of devices (AOSP)
// Copyright (C) 2017  Daniel 'dady8889' Múčka

package com.dady.huaweisettings;

import android.util.Log;

import java.lang.reflect.Method;

// Class that enables use of SystemProperties class, which is hidden within AOSP Sources

final class SystemPropertiesReflection {

    private static final String TAG = "HuaweiSettings_Reflect";

    static String GetSystemString(String name, String def)
    {
        try {
            Class<?> systemProperties = Class.forName("android.os.SystemProperties");
            Method method = systemProperties.getDeclaredMethod("get", String.class, String.class);
            String propertyValue = (String)method.invoke(systemProperties, name, def);
            Log.d(TAG, "GetSystemString: Prop [" + name + "] Value [" + propertyValue + "]");
            return propertyValue;
        } catch (Exception ex) {
            Log.e(TAG, "GetSystemString: " + ex.getMessage());
        }
        return def;
    }

    static void SetSystemString(String name, String val)
    {
        try {
            Class<?> systemProperties = Class.forName("android.os.SystemProperties");
            Method method = systemProperties.getDeclaredMethod("set", String.class, String.class);
            method.invoke(systemProperties, name, val);
            Log.d(TAG, "SetSystemString: Prop [" + name + "] Value [" + val + "]");
        } catch (Exception ex) {
            Log.e(TAG, "SetSystemString: " + ex.getMessage());
        }
    }
}

package com.mobiles.devices.fuction;

import android.content.Context;
import android.os.Debug;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.mobiles.devices.utils.Shell_Util;


public class check_adb {
    /**
     * 手机是否DebuggerConnected
     * @return  boolean
     */
    public static boolean isDebuggerConnected() {
        try {
            return Debug.isDebuggerConnected();
        } catch (Exception unused) {
            return false;
        }
    }

    /**
     * 手机是否adb_enabled
     * @return  boolean
     */
    public static boolean adb_enabled(Context ctx) {
        try {
            return Settings.Secure.getInt(ctx.getContentResolver(), "adb_enabled", 0) > 0;
        } catch (Exception unused) {
            return false;
        }
    }

    /**
     * 手机是否adb_enabled
     * @return  boolean
     */
    private static String init_svc_adbd() {

        String a2 = Shell_Util.shell("getprop init.svc.adbd");
        if (TextUtils.isEmpty(a2) || a2.length() <= 0 || a2.charAt(a2.length() - 1) != '\n') {
            return TextUtils.isEmpty(a2) ? "$unknown" : a2;
        }
        return a2.substring(0, a2.length() - 1);
    }
    public static String chenk_all(Context ctx) {
        String TAG = "adb环境检测：";
        Log.e(TAG, "DebuggerConnected：" + isDebuggerConnected());
        Log.e(TAG, "adb_enabled：" + adb_enabled(ctx));
        Log.e(TAG, "init_svc_adbd：" + init_svc_adbd());
        return "DebuggerConnected：" + isDebuggerConnected() +"\n"+ "adb_enabled：" + adb_enabled(ctx) +"\n" + "init_svc_adbd：" + init_svc_adbd();
    }
}

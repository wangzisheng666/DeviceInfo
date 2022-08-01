package com.mobiles.devices.fuction;

import android.os.Build;
import android.util.Log;

public class device_info {
    /**
     * 获取当前手机系统版本号Build.VERSION.RELEASE
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }
    /**
     * 获取手机型号Build.MODEL
     * @return  手机型号
     */
    public static String getSystemModel() {

        return android.os.Build.MODEL;
    }
    /**
     * 获取手机厂商Build.BRAND
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;

    }
    /**
     * 获取产品号Build.PRODUCT
     * @return  手机产品号
     */
    public static  String getDeviceProduct(){
        return android.os.Build.PRODUCT;
    }
    /**
     * 制造商Build.MANUFACTURE
     */
    public static String getManufacturer(){
        return  android.os.Build.MANUFACTURER;
    }
    /**
     * 主板Build.BOARD
     *
     */
    public static String getBoard(){
        return Build.BOARD;
    }
    /**
     * 设备标识Build.FINGERPRINT
     */
    public static String getFINGERPRINT(){
        return Build.FINGERPRINT;
    }
    /**
     * 版本号Build.ID
     */
    public static String getBuild_id(){
        return Build.ID;
    }
    /**
     * 用户Build.USER
     */
    public static String getBuildUser(){
        return Build.USER;
    }

    /**
     *
     */
    public static void showSystemParameter() {
        String TAG = "系统参数：";
        Log.e(TAG, "Android系统版本号：" + getSystemVersion());
        Log.e(TAG, "手机型号：" + getSystemModel());
        Log.e(TAG, "手机厂商：" + getDeviceBrand());
        Log.e(TAG, "手机产品号：" + getDeviceProduct());
        Log.e(TAG, "制造商：" + getManufacturer());
        Log.e(TAG, "主板：" + getBoard());
        Log.e(TAG, "Build_id版本号: "+getBuild_id());
        Log.e(TAG, "设备标识：" + getFINGERPRINT());
        Log.e(TAG, "用户：" + getBuildUser());

    }



}

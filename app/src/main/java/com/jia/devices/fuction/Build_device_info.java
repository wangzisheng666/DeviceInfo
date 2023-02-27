package com.jia.devices.fuction;

import android.os.Build;
import android.util.Log;

public class Build_device_info {
    /**
     * 获取当前手机系统版本号Build.VERSION.RELEASE
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号Build.MODEL
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商Build.BRAND
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取产品号Build.PRODUCT
     *
     * @return 手机产品号
     */
    public static String getDeviceProduct() {
        return android.os.Build.PRODUCT;
    }

    /**
     * 制造商Build.MANUFACTURE
     */
    public static String getManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 主板Build.BOARD
     */
    public static String getBoard() {
        return Build.BOARD;
    }

    /**
     * 设备标识Build.FINGERPRINT
     */
    public static String getFINGERPRINT() {
        return Build.FINGERPRINT;
    }

    /**
     * 版本号Build.ID
     */
    public static String getBuild_id() {
        return Build.ID;
    }

    /**
     * 用户Build.USER
     */
    public static String getBuildUser() {
        return Build.USER;
    }

    /**
     * 标签Build.TAGS
     */
    public static String getBuildTags() {
        return Build.TAGS;
    }

    /**
     * cpu架构 Build.CPU_ABI
     */
    public static String getCPU_ABI() {
        return Build.CPU_ABI;
    }

    /**
     * cpu2架构 Build.CPU_ABI2
     */
    public static String getCPU_ABI2() {
        return Build.CPU_ABI2;
    }

    /**
     * android sdk版本Build.VERSION.SDK
     */
    public static String getBuildVERSIONSDK() {
        return Build.VERSION.SDK;
    }

    /**
     * Android SDK版本号Build.VERSION.SDK_INT
     */
    public static int getBuildVERSIONSDK_INT() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 驱动Build.DEVICE
     */
    public static String getBuilddevice() {
        return Build.DEVICE;
    }

    /**
     * DISPLAY  Build.DISPLAY
     */
    public static String getDisplay() {
        return Build.DISPLAY;
    }

    /**
     * HARDWARE Build.HARDWARE
     */
    public static String getHARDWARE() {
        return Build.HARDWARE;
    }

    /**
     * host Build.HOST
     */
    public static String gethost() {
        return Build.HOST;
    }

    /**
     * 编译类型TYPE Build.TYPE
     */
    public static String getBuildType() {
        return Build.TYPE;
    }

    /**
     * 编译时间TIME Build.TIME
     */
    public static long getTime() {
        return Build.TIME;
    }

    /**
     * RADIO Build.RADIO
     */
    public static String getRadio() {
        return Build.RADIO;
    }

    /**
     * 序列号（SERIAL）Build.SERIAL
     */
    public static String getSerial() {
        return Build.SERIAL;
    }

    /**
     * bootloader
     */
    public static String getbootloader() {
        return Build.BOOTLOADER;
    }

    public static String CODENAME() {
        return Build.VERSION.CODENAME;
    }

    public static String SECURITY_PATCH() {
        return Build.VERSION.SECURITY_PATCH;
    }

    public static String INCREMENTAL() {
        return Build.VERSION.INCREMENTAL;
    }

    public static String getRadioVersion() {
        return Build.getRadioVersion();
    }

    public static String RELEASE() {
        return Build.VERSION.RELEASE;
    }

    public static String BASE_OS() {
        return Build.VERSION.BASE_OS;
    }

    public static int PREVIEW_SDK_INT() {
        return Build.VERSION.PREVIEW_SDK_INT;
    }

    /**
     * 整合数据
     */
    public static void showSystemParameter() {
        String TAG = "系统参数：";
        Log.e(TAG, "Android系统版本号：" + getSystemVersion());
        Log.e(TAG, "手机型号：" + getSystemModel());
        Log.e(TAG, "手机厂商：" + getDeviceBrand());
        Log.e(TAG, "手机产品号：" + getDeviceProduct());
        Log.e(TAG, "制造商：" + getManufacturer());
        Log.e(TAG, "主板：" + getBoard());
        Log.e(TAG, "Build_id版本号: " + getBuild_id());
        Log.e(TAG, "设备标识：" + getFINGERPRINT());
        Log.e(TAG, "用户：" + getBuildUser());
        Log.e(TAG, "标签Build.TAGS：" + getBuildTags());
        Log.e(TAG, "cpu架构：" + getCPU_ABI());
        Log.e(TAG, "cpu2架构：" + getCPU_ABI2());
        Log.e(TAG, "android sdk版本：" + getBuildVERSIONSDK());
        Log.e(TAG, "Android SDK版本号：" + getBuildVERSIONSDK_INT());
        Log.e(TAG, "驱动Build.DEVICE：" + getBuilddevice());
        Log.e(TAG, "Build.DISPLAY：" + getDisplay());
        Log.e(TAG, "Build.HARDWARE：" + getHARDWARE());
        Log.e(TAG, "Build.HOST：" + gethost());
        Log.e(TAG, "Build.TYPE：" + getBuildType());
        Log.e(TAG, "Build.TIME：" + getTime());
        Log.e(TAG, "Build.RADIO：" + getRadio());
        Log.e(TAG, "序列号（SERIAL）：" + getSerial());
        Log.e(TAG, "bootloader：" + getbootloader());
        Log.e(TAG, "CODENAME：" + CODENAME());
        Log.e(TAG, "SECURITY_PATCH：" + SECURITY_PATCH());
        Log.e(TAG, "INCREMENTAL：" + INCREMENTAL());
        Log.e(TAG, "getRadioVersion：" + getRadioVersion());
        Log.e(TAG, "RELEASE：" + RELEASE());
        Log.e(TAG, "BASE_OS：" + BASE_OS());
        Log.e(TAG, "PREVIEW_SDK_INT：" + PREVIEW_SDK_INT());


    }
}

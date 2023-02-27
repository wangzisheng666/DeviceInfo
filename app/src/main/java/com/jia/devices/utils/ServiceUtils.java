package com.jia.devices.utils;

import android.app.IDevices;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

public class ServiceUtils {
    private static IDevices iMikRom = null;
    public static IDevices getiMikRom() {
        if (iMikRom == null) {
            try {
                Class localClass = Class.forName("android.os.ServiceManager");
                Method getService = localClass.getMethod("getService", new Class[] {String.class});
                if(getService != null) {
                    Object objResult = getService.invoke(localClass, new Object[]{"devices"});
                    if (objResult != null) {
                        IBinder binder = (IBinder) objResult;
                        iMikRom = IDevices.Stub.asInterface(binder);
                    }
                }
            } catch (Exception e) {
                Log.d("MikManager",e.getMessage());
                e.printStackTrace();
            }
        }
        return iMikRom;
    }
}

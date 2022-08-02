/*
 * Copyright (C) 2022 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mobiles.devices.fuction;

import static com.mobiles.devices.core.webview.AgentWebFragment.TAG;
import static com.umeng.commonsdk.statistics.AnalyticsConstants.LOG_TAG;

import android.os.FileUtils;
import android.util.Log;

import java.io.File;



public class check_root {


    private static boolean checkDeviceKey() {
        String buildTags = android.os.Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }
        return false;
    }

    /**
     * 检测是否存在可执行文件su 、busybox
     */
    private static boolean checkSuFile() {
        final String filePaths[] = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/","/data/local","/system/bin/failsafe","/data/local/bin","/data/local/xbin","/data","/cache"};
        for (int i = 0; i < filePaths.length; i++) {
            File f = new File(filePaths[i] + "su");
            if (f != null && f.exists()) {
                return true;
            }
        }
        for (int i = 0; i < filePaths.length; i++) {
            File f = new File(filePaths[i] + "busybox");
            if (f != null && f.exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测环境变量PATH上是否存在su、magisk
     */
    private static boolean checkSuPath() {
        String[] paths = System.getenv("PATH").split(":");
        for (String path : paths) {
            if (new File(path, "su").exists()) {
                return true;
            }
            if (new File(path, "magisk").exists()) {
                return true;
            }
        }
        return false;
    }



    public static void showSystemParameter() {
        String TAG = "CHEKROOT：";
        Log.e(TAG, "ROOT_test-keys：" + checkDeviceKey());
        Log.e(TAG, "checkSuFile：" + checkSuFile());
        Log.e(TAG, "checkSuPath：" + checkSuPath());

    }


}

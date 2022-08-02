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

import java.io.File;

public class check_root {


    private boolean checkRootFile() {
        int v4_2;
        try {
            if(new File("/system/app/Superuser.apk").exists()) {
                return true;
            }
            String[] v3 = {"/data/local/", "/data/local/bin/", "/data/local/xbin/", "/sbin/", "/su/bin/", "/system/bin/", "/system/bin/.ext/", "/system/bin/failsafe/", "/system/sd/xbin/", "/system/usr/we-need-root/", "/system/xbin/", "/system/sbin/", "/vendor/bin/", "/cache", "/data", "/dev"};
            int v4;
            for(v4 = 0; v4 < 16; ++v4) {
                if(new File(v3[v4], "su").exists()) {
                    return true;
                }
            }
            int v4_1;
            for(v4_1 = 0; v4_1 < 16; ++v4_1) {
                if(new File(v3[v4_1], "busybox").exists()) {
                    return true;
                }
            }
            v4_2 = 0;
            while(true) {

                if(v4_2 >= 16) {
                    return false;
                }
                boolean v5 = new File(v3[v4_2], "magisk").exists();
                if(v5) {
                    return true;
                }
                break;
            }

        }
        catch(Throwable v0) {
            return false;
        }
        return false;
    }


}

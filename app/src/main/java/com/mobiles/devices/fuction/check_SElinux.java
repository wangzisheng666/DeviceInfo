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

import android.text.TextUtils;
import android.util.Log;

import com.mobiles.devices.utils.Shell_Util;

public class check_SElinux {
    /**
     * 先用getenforce来检测selinux
     * 后续需要检测 是否允许了一些ioctl或者检测其他selinux规则的状态
     */

    public static String SElinux() {

        String a2 = Shell_Util.shell("getenforce");
        if (TextUtils.equals(a2,"Enforcing")){
            Log.i(TAG, "SElinux: 正常"+a2);
            return "SElinux 正常";
        }
        else
        {
            Log.i(TAG, "SElinux: 异常"+a2);
            return "SElinux 规则已被修改";
        }

    }
}


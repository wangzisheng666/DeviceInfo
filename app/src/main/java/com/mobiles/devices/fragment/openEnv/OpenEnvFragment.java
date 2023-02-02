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

package com.mobiles.devices.fragment.openEnv;

import static com.mobiles.devices.utils.Utils.execRootCmd;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mobiles.devices.R;
import com.mobiles.devices.adapter.entity.buildInfo;
import com.mobiles.devices.adapter.entity.deviceIfo;
import com.mobiles.devices.core.BaseFragment;
import com.mobiles.devices.databinding.FragmentOpenEnvBinding;
import com.mobiles.devices.fragment.other.AboutFragment;
import com.mobiles.devices.fragment.other.SettingsFragment;
import com.mobiles.devices.fragment.settings.CornerListView;
import com.mobiles.devices.utils.CommandExecution;
import com.mobiles.devices.utils.ServiceUtils;
import com.mobiles.devices.utils.ShellUtils;
import com.mobiles.devices.utils.Utils;
import com.mobiles.devices.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.shadow.ShadowTextView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import com.xuexiang.xui.widget.toast.XToast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * @author xuexiang
 * @since 2019-10-30 00:18
 */
@Page(anim = CoreAnim.none)
public class OpenEnvFragment extends BaseFragment<FragmentOpenEnvBinding> implements SuperTextView.OnSuperTextViewClickListener {
    String pid = null;
    public boolean isOpen =false;
    TextView textView;

    @Override
    protected void initArgs() {
        super.initArgs();
        XToast.Config.get()
                //位置设置为居中
                .setGravity(Gravity.CENTER);
    }
    @NonNull
    @Override
    protected FragmentOpenEnvBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentOpenEnvBinding.inflate(inflater, container, false);
    }

    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

        SetingOne();
        SetingTwo();


        textView=findViewById(R.id.page1_button_text);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    //通知父控件不要干扰
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                if(event.getAction()==MotionEvent.ACTION_MOVE){
                    //通知父控件不要干扰
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        findViewById(R.id.page1_button_bianji).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getString();




            }
        });

    }

    @Override
    protected void initListeners() {
      //  binding.open1.setOnSuperTextViewClickListener(this);


       /* binding.openDebugable.setSwitchCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                XToastUtils.toast("GPU测评开启");
            }else { XToastUtils.toast("GPU测评关闭");
                 }
        });*/

      /*  binding.openFrida.setOnSuperTextViewClickListener(superTextView -> superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false)).setSwitchCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                if(!isOpen){
                    String[] str1 = new String[]{"myfs1 &"};
                    CommandExecution.execCommand(str1,true);
                    String result =  execRootCmd("ps -e |grep myfs1");
                    pid = StringUtils.substringBetween(result, "root", " 1 ");
                    Log.i("TAG",pid+"result1.result = " + result);
                    XToastUtils.success("frida开启成功");
                    isOpen = true;

                }else {
                    binding.openFridaBig.setSwitchIsChecked(false);
                    String[] str1 = new String[]{"kill  "+ pid };
                    CommandExecution.execCommand(str1,true);

                    String[] str2 = new String[]{"myfs1 &"};
                    CommandExecution.execCommand(str2,true);
                    String result =  execRootCmd("ps -e |grep myfs1");
                    pid = StringUtils.substringBetween(result, "root", " 1 ");
                    XToastUtils.success("frida开启成功");

                }

       *//*         ShellUtils.CommandResult result = ShellUtils.execCommand("myfs1",true);  //主要是这条语句，字符串中是命令
                Log.i(TAG,"result1.result = " + result.result);
                Log.i(TAG,"result1.successMsg = " + result.successMsg);		// 有用的信息在这里，可以打印出来看看
                Log.i(TAG,"result1.errorMsg = " + result.errorMsg);*//*
               *//* Utils.shellExec("myfs1");
                String bb =Utils.shellExec("ps -e |grep myfs1");
                exe_cmd("myfs1");
               String aa = exe_cmd("ps -e |grep myfs1");
                String[] str = new String[]{"ps -e |grep myfs1"};
               if(bb.isEmpty()){
                   XToastUtils.error("frida开启失败");
                   binding.openFrida.setSwitchIsChecked(false);
               }else if(exe_cmd("ps -e |grep myfs1").contains("myfs1")){
                   XToastUtils.success("frida开启");
               }*//*
            }else {
                String[] str1 = new String[]{"kill  "+ pid };
                CommandExecution.execCommand(str1,true);
                XToastUtils.error("frida关闭");
                isOpen = false;
                //删除敏感文件防止检测
                execRootCmd("rm -rf /data/local/tmp/re.frida.server");
            }
        });
*/
       /* binding.openFridaBig.setOnSuperTextViewClickListener(superTextView -> superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false)).setSwitchCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                if(!isOpen){
                    String[] str1 = new String[]{"myfs2 &"};
                    CommandExecution.execCommand(str1,true);
                    String result =  execRootCmd("ps -e |grep myfs2");
                    pid = StringUtils.substringBetween(result, "root", " 1 ");
                    Log.i("TAG",pid+"result1.result = " + result);
                    XToastUtils.success("frida隐藏版开启");
                    isOpen = true;
                }else {
                    binding.openFrida.setSwitchIsChecked(false);
                    String[] str1 = new String[]{"kill  "+ pid };
                    CommandExecution.execCommand(str1,true);

                    String[] str2 = new String[]{"myfs2 &"};
                    CommandExecution.execCommand(str2,true);
                    String result =  execRootCmd("ps -e |grep myfs2");
                    pid = StringUtils.substringBetween(result, "root", " 1 ");
                    XToastUtils.success("frida隐藏版开启");
                   // XToastUtils.error("frida隐藏版开启未成功，请先关闭Frida");

                }

            }else {
                String[] str1 = new String[]{"kill  "+ pid };
                CommandExecution.execCommand(str1,true);
                XToastUtils.error("frida隐藏版关闭");
                isOpen = false;
            }
        });

       // binding.openDebugable.setSwitchIsChecked(isOpen_rodebug());
        binding.openDebugable.setOnSuperTextViewClickListener(superTextView -> superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false)).setSwitchCheckedChangeListener((buttonView, isChecked)  -> {
            if(isChecked){
               // execRootCmd("injectprop  ro.debuggable 1");
              //  execRootCmd("stop;start;");
                CommandExecution.execCommand("injectprop ro.debuggable 1",true);
                CommandExecution.execCommand("setprop ctl.restart zygote_secondary",true);
                *//*String path=copyAssetGetFilePath("wan.sh");
                CommandExecution.execCommand("chmod 777 "+path,true);
                CommandExecution.execCommand("nohup "+path+" &",true);*//*
                //exe_cmd_str(str);
                XToastUtils.success("ro.debug开启");
            }else {

               // CommandExecution.execCommand("injectprop ro.debuggable 0",true);
                //CommandExecution.execCommand("setprop ctl.restart zygote_secondary",true);
                String[] str1 = new String[]{"injectprop ro.debuggable 0",
                       // "setprop ctl.restart zygote_secondary",
                        "setprop ctl.restart zygote",
                      //  "setorop ctl.restart netd"

                };
                CommandExecution.execCommand(str1,true);
                XToastUtils.error("ro.debug关闭");

            }
        });



       // binding.openRoYc.setSwitchIsChecked(isOpen_ro());
        binding.openRoYc.setOnSuperTextViewClickListener(superTextView -> superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false)).setSwitchCheckedChangeListener((buttonView, isChecked)  -> {
            if(isChecked){

                String[] str1 = new String[]{"injectprop ro.boot.vbmeta.device_state locked",
                        "injectprop ro.boot.verifiedbootstate green",
                        "injectprop ro.boot.flash.locked 1",
                        "injectprop ro.boot.veritymode enforcing",
                        "injectprop ro.boot.warranty_bit 0",
                        "injectprop ro.warranty_bit 0",
                        "injectprop ro.debuggable 0",
                        "injectprop ro.secure 1",
                        "injectprop ro.adb.secure 1",
                        "injectprop ro.build.type user",
                        "injectprop ro.build.tags release-keys",
                        "injectprop ro.vendor.boot.warranty_bit 0",
                        "injectprop ro.vendor.warranty_bit 0",
                        "injectprop vendor.boot.vbmeta.device_state locked",
                        "injectprop vendor.boot.verifiedbootstate green",
                        "injectprop ro.secureboot.lockstate locked"
                };
                CommandExecution.execCommand(str1,true);
                XToastUtils.success("ro.debug开启");
            }else {

                XToastUtils.error("ro.debug关闭");
            }
        });

*/
    }

    @SingleClick
    @Override
    public void onClick(SuperTextView view) {
        int id = view.getId();
       /* if (id == R.id.page1_button_bianji) {

            //openNewPage(SettingsFragment.class);
        }*/ /*else if (id == R.id.open_debugable) {
            openNewPage(AboutFragment.class);
        }*/
    }
    public static final int UPDATE_TEXT=1;
    private static String string;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==UPDATE_TEXT){
               // tvContent.setText(string);
                textView.setText(string);

               JSONObject  jsonObject = new JSONObject(JSON.parseObject(string));

                JSONObject js =jsonObject.getJSONObject("buildInfo");
                String str = js.toString();

                Map mapsRe = (Map)JSON.parse(str);


                Map<String,String> map_tj=new HashMap<>();
                map_tj.put("board", "ro.product.board");
                Log.i("buildInfo_BOARD",Build.BOARD);
                map_tj.put("bootloader", "ro.bootloader");
                Log.i("buildInfo_BOOTLOADER",Build.BOOTLOADER);
                map_tj.put("brand", "ro.product.brand");
                Log.i("buildInfo_BRAND",Build.BRAND);
                map_tj.put("device", "ro.product.device");
                Log.i("buildInfo_BRAND",Build.DEVICE);
                map_tj.put("display", "ro.build.display.id");
                Log.i("buildInfo_DISPLAY",Build.DISPLAY);
                map_tj.put("increment", "ro.build.version.incremental");
                Log.i("buildInfo_VERSION.INCREMENTAL",Build.VERSION.INCREMENTAL);
                map_tj.put("fingerPrint", "ro.build.fingerprint");
                /*ro.bootimage.build.fingerprint]: [google/coral/coral:13/TP1A.221005.002/9012097:user/release-keys]
[ro.build.fingerprint]: [google/coral/coral:13/TP1A.221005.002/9012097:user/release-keys]
[ro.build.version.preview_sdk_fingerprint]: [REL]
[ro.odm.build.fingerprint]: [google/coral/coral:13/TP1A.221005.002/9012097:user/release-keys]
[ro.product.build.fingerprint]: [google/coral/coral:13/TP1A.221005.002/9012097:user/release-keys]
[ro.system.build.fingerprint]: [google/coral/coral:13/TP1A.221005.002/9012097:user/release-keys]
[ro.system_ext.build.fingerprint]: [google/coral/coral:13/TP1A.221005.002/9012097:user/release-keys]
[ro.vendor.build.fingerprint]: [google/coral/coral:13/TP1A.221005.002/9012097:user/release-keys]
[ro.vendor_dlkm.build.fingerprint]: [google/coral/coral:13/TP1A.221005.002/9012097:user/release-keys]
*/
                Log.i("buildInfo_FINGERPRINT",Build.FINGERPRINT);
                map_tj.put("hardWare", "ro.hardware");
                Log.i("buildInfo_HARDWARE",Build.HARDWARE);
                map_tj.put("host", "ro.build.host");
                Log.i("buildInfo_HOST",Build.HOST);
                map_tj.put("id", "ro.build.id");
                Log.i("buildInfo_ID",Build.ID);
                map_tj.put("manufacture", "ro.product.manufacturer");
                Log.i("buildInfo_MANUFACTURER",Build.MANUFACTURER);
                map_tj.put("serial", "no.such.thing");
                Log.i("buildInfo_SERIAL",Build.SERIAL);
                map_tj.put("product", "ro.product.name");
                Log.i("buildInfo_PRODUCT",Build.PRODUCT);
                map_tj.put("tags", "ro.build.tags");
                Log.i("buildInfo_TAGS",Build.TAGS);
                map_tj.put("time", "o.build.date.utc");
                Log.i("buildInfo_",String.valueOf(Build.TIME));
                map_tj.put("type", "ro.build.type");
                Log.i("buildInfo_TYPE",Build.TYPE);
                map_tj.put("user", "ro.build.user");
                Log.i("buildInfo_",Build.USER);
                map_tj.put("sdk", "ro.build.version.sdk");
                Log.i("buildInfo_VERSION.SDK",Build.VERSION.SDK);
                map_tj.put("sdkInit", "ro.build.version.sdk");
                Log.i("buildInfo_",Build.VERSION.SDK);
                map_tj.put("model", "ro.product.model");
                Log.i("buildInfo_MODEL",Build.MODEL);

                Map<String,String> map_new=new HashMap<>();

                for (Object map_re : mapsRe.entrySet()){

                    Log.i("map_re集合",((Map.Entry)map_re).getKey()+"     " + ((Map.Entry)map_re).getValue());

                    for (Object map__tj : map_tj.entrySet()){

                        Log.i("map__tj集合",((Map.Entry)map__tj).getKey()+"     " + ((Map.Entry)map__tj).getValue());

                        if( ((Map.Entry)map_re).getKey().toString().equals(((Map.Entry)map__tj).getKey()) ){
                            map_new.put(((Map.Entry)map__tj).getValue().toString(),((Map.Entry)map_re).getValue().toString());
                            Log.i("map_new集合",((Map.Entry)map__tj).getValue().toString()+"     " + ((Map.Entry)map_re).getValue().toString());
                            try {
                                ServiceUtils.getiMikRom().shellExec("injectprop " + ((Map.Entry)map__tj).getValue().toString()+" " + ((Map.Entry)map_re).getValue().toString());
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }

                try {
                    ServiceUtils.getiMikRom().shellExec("setprop ctl.restart zygote_secondary");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            super.handleMessage(msg);
        }
    };


   private void getString(){
       XHttp.get("/v2/api/fakeinfo?country=JP")
               .syncRequest(false) //异步请求
               .onMainThread(true) //回到主线程
               .execute(new SimpleCallBack<String>() {
                   @Override
                   public void onSuccess(String response) throws Throwable {

                       String Decryption_base= new String(Base64.decode(response.getBytes(), Base64.DEFAULT));

                       String bb ="{\"ua\":\"Mozilla/5.0 (Linux; Android 10; Reno) AppleWebKit/ 537.36 (KHTML, like Gecko) Chrome/90.0.4430.91 Mobile Safari/537.36\",\"javaUa\":\"Dalvik/2.1.0 (Linux; U; Android 10; Reno Build/CFMG.202143.065)\",\"imei\":\"860005483227623\",\"imsi\":\"860005485645681\",\"mac\":\"B1:DE:6B:8F:89:05\",\"bssid\":\"37:DB:63:D2:B4:A8\",\"ssid\":\"W35LlyN\",\"phone\":\"+819066031821\",\"phoneStatus\":\"1\",\"phoneType\":1,\"width\":\"720\",\"height\":\"1560\",\"densityDpi\":340,\"xdpi\":\"343.628\",\"ydpi\":\"343.628\",\"carrier\":\"NTT\",\"mcc\":\"440\",\"mnc\":\"10\",\"carrierCode\":\"44010\",\"countryCode\":\"JP\",\"latitude\":\"33.488185\",\"longitude\":\"134.396803\",\"simSerial\":\"89810495833779614901\",\"simStatus\":\"5\",\"androidId\":\"cf9715e520d96c2b\",\"androidSerial\":\"9187E2181E952E\",\"ip\":\"\",\"diskSize\":\"13648998\",\"networkType\":\"0\",\"networkTypeName\":\"MOBILE\",\"networkSubType\":13,\"networkSubTypeName\":\"LTE\",\"blueTooth\":\"94:56:F5:A6:53:56\",\"email\":\"OS3tf@gmail.com\",\"Sensors\":{\"ACCELEROMETER\":{\"NAME\":\"AK09918-pseudo-gyro\",\"VENDOR\":\"MTK\"},\"GYROSCOPE\":{\"NAME\":\"lsm6dsm\",\"VENDOR\":\"lsm6dsm_acc\"}},\"buildInfo\":{\"board\":\"coral\",\"bootloader\":\"c2f2-0.4-8048765\",\"brand\":\"google\",\"cpu_abi\":\"arm64-v8a\",\"cpu_abi2\":\"arm64-v8a\",\"cpu_abilist\":\"arm64-v8a,armeabi-v7a,armeabi\",\"device\":\"coral\",\"display\":\"TD1A.221105.001\",\"radioVersion\":\"YXMS0-00060-2014770638\",\"increment\":\"1673581186\",\"fingerPrint\":\"Oppo/PCAM00/PCAM00:10/74ELYT-660.6153.24/1899793:user/release-keys\",\"hardWare\":\"coral\",\"host\":\"cxf-System\",\"id\":\"TD1A.221105.001\",\"manufacture\":\"Google\",\"serial\":\"9187E2181E952E\",\"product\":\"coral\",\"tags\":\"release-keys\",\"time\":1595651339149,\"type\":\"user\",\"user\":\"cxf\",\"sdk\":\"33\",\"sdkInit\":\"33\",\"model\":\"Pixel 4 XL\",\"osName\":\"13\",\"osArch\":\"armv7l\",\"osVersion\":\"13\",\"androidVersion\":\"13\",\"SECURITY_PATCH\":\"2022-11-05\"}}";

                       String aa = "{\n" +
                               "  \"ua\": \"Mozilla/5.0 (Linux; Android 11; Zenfone 7) AppleWebKit/ 537.36 (KHTML, like Gecko) Chrome/90.0.4430.66 Mobile Safari/537.36\",\n" +
                               "  \"javaUa\": \"Dalvik/2.1.0 (Linux; U; Android 11; Zenfone 7 Build/5PAS.201871.032)\",\n" +
                               "  \"imei\": \"866872080788958\",\n" +
                               "  \"imsi\": \"866872081834502\",\n" +
                               "  \"mac\": \"4D:B0:24:19:2F:4D\",\n" +
                               "  \"bssid\": \"D9:35:F3:84:9D:6D\",\n" +
                               "  \"ssid\": \"wmnFFLzjZ\",\n" +
                               "  \"phone\": \"+81805530610\",\n" +
                               "  \"phoneStatus\": \"1\",\n" +
                               "  \"phoneType\": 1,\n" +
                               "  \"width\": \"1080\",\n" +
                               "  \"height\": \"2340\",\n" +
                               "  \"densityDpi\": 560,\n" +
                               "  \"xdpi\": \"515.442\",\n" +
                               "  \"ydpi\": \"515.442\",\n" +
                               "  \"carrier\": \"KDDI\",\n" +
                               "  \"mcc\": \"440\",\n" +
                               "  \"mnc\": \"73\",\n" +
                               "  \"carrierCode\": \"44073\",\n" +
                               "  \"countryCode\": \"JP\",\n" +
                               "  \"latitude\": \"30.320331\",\n" +
                               "  \"longitude\": \"126.315645\",\n" +
                               "  \"simSerial\": \"89810288185159523082\",\n" +
                               "  \"simStatus\": \"5\",\n" +
                               "  \"androidId\": \"cd52db4611f5181b\",\n" +
                               "  \"androidSerial\": \"53F989CC\",\n" +
                               "  \"ip\": \"\",\n" +
                               "  \"diskSize\": \"3047200\",\n" +
                               "  \"networkType\": \"0\",\n" +
                               "  \"networkTypeName\": \"MOBILE\",\n" +
                               "  \"networkSubType\": 14,\n" +
                               "  \"networkSubTypeName\": \"WCDMA\",\n" +
                               "  \"blueTooth\": \"E2:17:B0:35:E2:1E\",\n" +
                               "  \"email\": \"kwBJS4@gmail.com\",\n" +
                               "  \"Sensors\": {\n" +
                               "    \"ACCELEROMETER\": {\n" +
                               "      \"NAME\": \"icm4x6xx\",\n" +
                               "      \"VENDOR\": \"MTK\"\n" +
                               "    },\n" +
                               "    \"GYROSCOPE\": {\n" +
                               "      \"NAME\": \"st-lis3dh\",\n" +
                               "      \"VENDOR\": \"ST\"\n" +
                               "    }\n" +
                               "  },\n" +
                               "  \"buildInfo\": {\n" +
                               "   \"board\": \"X662, X662B, X689F\",\n" +
                               "    \"bootloader\": \"06HWF-399.1920.85\",\n" +
                               "    \"brand\": \"Infinix\",\n" +
                               "    \"cpu_abi\": \"arm64-v8a\",\n" +
                               "    \"cpu_abi2\": \"arm64-v8a\",\n" +
                               "    \"cpu_abilist\": \"arm64-v8a,armeabi-v7a,armeabi\",\n" +
                               "    \"device\": \"X662\",\n" +
                               "    \"display\": \"W7BM.202016.098\",\n" +
                               "    \"radioVersion\": \"1K49Q-00040-2018577763\",\n" +
                               "    \"increment\": \"1121674\",\n" +
                               "    \"fingerPrint\": \"Infinix/X662/X662:11/06HWF-399.1920.85/1121674:user/release-keys\",\n" +
                               "    \"hardWare\": \"Infinix\",\n" +
                               "    \"host\": \"FA22C-98377\",\n" +
                               "    \"id\": \"W7BM.202016.098\",\n" +
                               "    \"manufacture\": \"Infinix\",\n" +
                               "    \"serial\": \"23168E09\",\n" +
                               "    \"product\": \"Hot 11\",\n" +
                               "    \"tags\": \"release-keys\",\n" +
                               "    \"time\": 1617337885293,\n" +
                               "    \"type\": \"user\",\n" +
                               "    \"user\": \"builder\",\n" +
                               "    \"sdk\": \"30\",\n" +
                               "    \"sdkInit\": \"33\",\n" +
                               "    \"model\": \"Zenfone 7\",\n" +
                               "    \"osName\": \"11\",\n" +
                               "    \"osArch\": \"armv7l\",\n" +
                               "    \"osVersion\": \"11\",\n" +
                               "    \"androidVersion\": \"11\",\n" +
                               "    \"SECURITY_PATCH\": \"2020-12-28\"\n" +
                               "  }\n" +
                               "}";

                       Log.i("解密数据", Decryption_base);
                       ServiceUtils.getiMikRom().writeFile("/data/system/IDevice_conf",Decryption_base);
                       string= Decryption_base;
                       Message message=new Message();
                       message.what=UPDATE_TEXT;
                       handler.sendMessage(message);



               /*        String jsonStr = Decryption_base;
                       JSONObject jsonObject;
                       List<buildInfo> buildInfoList = new ArrayList<>();
                       try{
                           jsonObject = new JSONObject(JSON.parseObject(jsonStr));

                           JSONObject js =jsonObject.getJSONObject("buildInfo");
                           String a = js.getString("board");


                          *//* deviceIfo tagInfo = JSONObject.toJavaObject(jsonObject,deviceIfo.class);
                           buildInfoList = JSONObject.parseArray(tagInfo.getBuildInfos().toString(), buildInfo.class);

                           deviceIfo deviceIfo =  JSON.parseObject(jsonStr, deviceIfo.class);
                           deviceIfo.buildInfos = deviceIfo.getBuildInfos();
                           Log.i("11111",buildInfoList.toString());*//*

                           //List<buildInfo> aa = tagInfo.getBuildInfos();
                           //t 方法1
                           // Log.i("11111",a);
                       } catch (JSONException e) {
                           throw new RuntimeException(e.getMessage(), e);
                       }*/
                   }
                   @Override
                   public void onError(ApiException e) {
                       Log.i("11",e.getDisplayMessage());
                   }
               });

   }


    private void SetingOne()
    {
        CornerListView cornerListView = findViewById(R.id.setting_list);
        ArrayList<ListItem> mList = setListDataOne();
        // ��ȡMainListAdapter����
        MainListViewAdapter adapter = new MainListViewAdapter(getActivity().getApplicationContext(),mList);

        // ��MainListAdapter���󴫵ݸ�ListView��ͼ
        cornerListView.setAdapter(adapter);
    }

    private void SetingTwo()
    {
        CornerListView cornerListView2 = findViewById(R.id.setting_list2);
        ArrayList<ListItem> mList = setListDataTwo();
        MainListViewAdapter adapter = new MainListViewAdapter(getActivity().getApplicationContext(),mList);
        cornerListView2.setAdapter(adapter);
    }

    /**
     * �����б�����
     */
    private ArrayList<ListItem>  setListDataOne(){

        ArrayList<ListItem> mList = new ArrayList<ListItem>();
        Resources res = this.getResources();
        ListItem item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.fr_lm));
        item.setTitle("设备名称");
        item.setTitle1("xiao");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.fr_mg));
        item.setTitle("设备型号");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.fr_xj));
        item.setTitle("品牌");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.fr_xg));
        item.setTitle("Android ID");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.fr_tz));
        item.setTitle("设备指纹");
        mList.add(item);
        return mList;
    }

    private ArrayList<ListItem>  setListDataTwo(){

        ArrayList<ListItem> mList = new ArrayList<ListItem>();
        Resources res = this.getResources();
        ListItem item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.fr_bl));
        item.setTitle("SDK");
        mList.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.fr_cz));
        item.setTitle("版本号");
        mList.add(item);
        return mList;
    }
}

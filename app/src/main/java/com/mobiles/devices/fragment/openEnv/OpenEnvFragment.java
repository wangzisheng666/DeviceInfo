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
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
import com.mobiles.devices.fragment.environmental.AppInfo;
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

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author xuexiang
 * @since 2019-10-30 00:18
 */
@Page(anim = CoreAnim.none)
public class OpenEnvFragment extends BaseFragment<FragmentOpenEnvBinding> implements SuperTextView.OnSuperTextViewClickListener {
    String pid = null;
    public boolean isOpen =false;
    TextView textView_app ;
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
    Spinner mSpinnerFitOffset;
    private ArrayAdapter<String> groupScene_adapter;


    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        Spinner groupScene;



        groupScene = findViewById(R.id.spinner_system_fit_offset);
        textView_app = findViewById(R.id.app);

        List<String> list_app =  new ArrayList<>();
// 建立数据源

// 建立Adapter并且绑定数据源
        groupScene_adapter =new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,  getAllAppInfo(getActivity(), true)
        );
        //设置下拉列表的风格,simple_spinner_dropdown_item是android系统自带的样式，等会自定义修改
        //将可选内容与ArrayAdapter连接起来，simple_spinner_item是android系统自带样式groupScene_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//绑定 Adapter到控件
        groupScene.setAdapter(groupScene_adapter);
        //添加事件Spinner事件监听
        groupScene.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String f = groupScene.getSelectedItem().toString();
               // Toast.makeText(getContext(), "点击:" + pos + "id " + id + "选中值== " + f , Toast.LENGTH_SHORT).show();

                list_app.add(f);
                list_app.contains("选择应用");

                remove(list_app, "选择应用");
                textView_app.setText(StringUtils.join(list_app,","));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
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
    /**
     * 获取手机已安装应用列表
     * @param ctx
     * @param isFilterSystem 是否过滤系统应用
     * @return
     */
    public static ArrayList<String> getAllAppInfo(Context ctx, boolean isFilterSystem) {
        ArrayList<String> appBeanList = new ArrayList<>();
       // AppInfo bean = null;
        appBeanList.add("选择应用");
        PackageManager packageManager = ctx.getPackageManager();
        List<PackageInfo> list = packageManager.getInstalledPackages(0);
        for (PackageInfo p : list) {
           // bean = new AppInfo();
           // bean.setIcon(p.applicationInfo.loadIcon(packageManager));
          //  bean.setLabel(packageManager.getApplicationLabel(p.applicationInfo).toString());
          //  bean.setPackage_name(p.applicationInfo.packageName);
            int flags = p.applicationInfo.flags;
            // 判断是否是属于系统的apk
            if ((flags & ApplicationInfo.FLAG_SYSTEM) != 0&&isFilterSystem) {
//                bean.setSystem(true);
            } else {
                String aa = packageManager.getApplicationLabel(p.applicationInfo).toString();
                if(!aa.contains(".")){
                    appBeanList.add(aa);
                }
            }
        }
        return appBeanList;
    }

    public static void remove(List<String> list, String target){
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String item = iter.next();
            if (item.equals(target)) {
                iter.remove();
            }
        }
    }



}

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

import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mobiles.devices.R;
import com.mobiles.devices.core.BaseFragment;
import com.mobiles.devices.databinding.FragmentOpenEnvBinding;
import com.mobiles.devices.fragment.other.AboutFragment;
import com.mobiles.devices.fragment.other.SettingsFragment;
import com.mobiles.devices.utils.CommandExecution;
import com.mobiles.devices.utils.ShellUtils;
import com.mobiles.devices.utils.Utils;
import com.mobiles.devices.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import com.xuexiang.xui.widget.toast.XToast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;


/**
 * @author xuexiang
 * @since 2019-10-30 00:18
 */
@Page(anim = CoreAnim.none)
public class OpenEnvFragment extends BaseFragment<FragmentOpenEnvBinding> implements SuperTextView.OnSuperTextViewClickListener {
    String pid = null;
    public boolean isOpen =false;

    @Override
    protected void initArgs() {
        super.initArgs();
        XToast.Config.get()
                //?????????????????????
                .setGravity(Gravity.CENTER);
    }
    @NonNull
    @Override
    protected FragmentOpenEnvBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentOpenEnvBinding.inflate(inflater, container, false);
    }

    /**
     * @return ????????? null????????????????????????
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * ???????????????
     */
    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        binding.openDebugable.setOnSuperTextViewClickListener(this);
        binding.open1.setOnSuperTextViewClickListener(this);


       /* binding.openDebugable.setSwitchCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                XToastUtils.toast("GPU????????????");
            }else { XToastUtils.toast("GPU????????????");
                 }
        });*/

        binding.openFrida.setOnSuperTextViewClickListener(superTextView -> superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false)).setSwitchCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                if(!isOpen){
                    String[] str1 = new String[]{"myfs1 &"};
                    CommandExecution.execCommand(str1,true);
                    String result =  execRootCmd("ps -e |grep myfs1");
                    pid = StringUtils.substringBetween(result, "root", " 1 ");
                    Log.i("TAG",pid+"result1.result = " + result);
                    XToastUtils.success("frida????????????");
                    isOpen = true;

                }else {
                    binding.openFridaBig.setSwitchIsChecked(false);
                    String[] str1 = new String[]{"kill  "+ pid };
                    CommandExecution.execCommand(str1,true);

                    String[] str2 = new String[]{"myfs1 &"};
                    CommandExecution.execCommand(str2,true);
                    String result =  execRootCmd("ps -e |grep myfs1");
                    pid = StringUtils.substringBetween(result, "root", " 1 ");
                    XToastUtils.success("frida????????????");

                }

       /*         ShellUtils.CommandResult result = ShellUtils.execCommand("myfs1",true);  //?????????????????????????????????????????????
                Log.i(TAG,"result1.result = " + result.result);
                Log.i(TAG,"result1.successMsg = " + result.successMsg);		// ???????????????????????????????????????????????????
                Log.i(TAG,"result1.errorMsg = " + result.errorMsg);*/
               /* Utils.shellExec("myfs1");
                String bb =Utils.shellExec("ps -e |grep myfs1");
                exe_cmd("myfs1");
               String aa = exe_cmd("ps -e |grep myfs1");
                String[] str = new String[]{"ps -e |grep myfs1"};
               if(bb.isEmpty()){
                   XToastUtils.error("frida????????????");
                   binding.openFrida.setSwitchIsChecked(false);
               }else if(exe_cmd("ps -e |grep myfs1").contains("myfs1")){
                   XToastUtils.success("frida??????");
               }*/
            }else {
                String[] str1 = new String[]{"kill  "+ pid };
                CommandExecution.execCommand(str1,true);
                XToastUtils.error("frida??????");
                isOpen = false;
                //??????????????????????????????
                execRootCmd("rm -rf /data/local/tmp/re.frida.server");
            }
        });

        binding.openFridaBig.setOnSuperTextViewClickListener(superTextView -> superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false)).setSwitchCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                if(!isOpen){
                    String[] str1 = new String[]{"myfs2 &"};
                    CommandExecution.execCommand(str1,true);
                    String result =  execRootCmd("ps -e |grep myfs2");
                    pid = StringUtils.substringBetween(result, "root", " 1 ");
                    Log.i("TAG",pid+"result1.result = " + result);
                    XToastUtils.success("frida???????????????");
                    isOpen = true;
                }else {
                    binding.openFrida.setSwitchIsChecked(false);
                    String[] str1 = new String[]{"kill  "+ pid };
                    CommandExecution.execCommand(str1,true);

                    String[] str2 = new String[]{"myfs2 &"};
                    CommandExecution.execCommand(str2,true);
                    String result =  execRootCmd("ps -e |grep myfs2");
                    pid = StringUtils.substringBetween(result, "root", " 1 ");
                    XToastUtils.success("frida???????????????");
                   // XToastUtils.error("frida???????????????????????????????????????Frida");

                }

            }else {
                String[] str1 = new String[]{"kill  "+ pid };
                CommandExecution.execCommand(str1,true);
                XToastUtils.error("frida???????????????");
                isOpen = false;
            }
        });

        binding.openDebugable.setSwitchIsChecked(isOpen_rodebug());
        binding.openDebugable.setOnSuperTextViewClickListener(superTextView -> superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false)).setSwitchCheckedChangeListener((buttonView, isChecked)  -> {
            if(isChecked){
               // execRootCmd("injectprop  ro.debuggable 1");
              //  execRootCmd("stop;start;");
                CommandExecution.execCommand("injectprop ro.debuggable 1",true);
                CommandExecution.execCommand("setprop ctl.restart zygote_secondary",true);
                /*String path=copyAssetGetFilePath("wan.sh");
                CommandExecution.execCommand("chmod 777 "+path,true);
                CommandExecution.execCommand("nohup "+path+" &",true);*/
                //exe_cmd_str(str);
                XToastUtils.success("ro.debug??????");
            }else {

               // CommandExecution.execCommand("injectprop ro.debuggable 0",true);
                //CommandExecution.execCommand("setprop ctl.restart zygote_secondary",true);
                String[] str1 = new String[]{"injectprop ro.debuggable 0",
                       // "setprop ctl.restart zygote_secondary",
                        "setprop ctl.restart zygote",
                      //  "setorop ctl.restart netd"

                };
                CommandExecution.execCommand(str1,true);
                XToastUtils.error("ro.debug??????");

            }
        });



        binding.openRoYc.setSwitchIsChecked(isOpen_ro());
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
                XToastUtils.success("ro.debug??????");
            }else {

                XToastUtils.error("ro.debug??????");
            }
        });


    }

    @SingleClick
    @Override
    public void onClick(SuperTextView view) {
        int id = view.getId();
        if (id == R.id.open_1) {
            openNewPage(SettingsFragment.class);
        } else if (id == R.id.open_debugable) {
            openNewPage(AboutFragment.class);
        }
    }

    private String exe_cmd(String cmd){
        try {
           return Utils.run(cmd,"/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String exe_cmd_str(String[] cmd){
        try {
            return Utils.run1(cmd,"/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    private String copyAssetGetFilePath(String fileName) {
        try {
            File cacheDir = getContext().getCacheDir();
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            File outFile = new File(cacheDir, fileName);
            if (!outFile.exists()) {
                boolean res = outFile.createNewFile();
                if (!res) {
                    return null;
                }
            } else {
                if (outFile.length() > 10) {//????????????????????????
                    return outFile.getPath();
                }
            }
            InputStream is = getContext().getAssets().open(fileName);
            FileOutputStream fos = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int byteCount;
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
            is.close();
            fos.close();
            return outFile.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private boolean isOpen_ro(){
        String[] strIf = new String[]{"getprop ro.boot.flash.locked"};
        CommandExecution.CommandResult result= CommandExecution.execCommand(strIf,true);
        String aa =  result.successMsg;
        if(aa.equals("0")){
            return false;
        }else return aa.equals("1");
    }

    private boolean isOpen_rodebug(){
        String[] strIf = new String[]{" getprop ro.debuggable "};
        CommandExecution.CommandResult result= CommandExecution.execCommand(strIf,true);
        String aa =  result.successMsg;
        if(aa.equals("0")){
            return false;
        }else return aa.equals("1");
    }
}

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

package com.jia.devices.fragment.openEnv;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.jia.devices.R;
import com.jia.devices.core.BaseFragment;
import com.jia.devices.databinding.FragmentOpenEnvBinding;
import com.jia.devices.fragment.other.AboutFragment;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import com.xuexiang.xui.widget.toast.XToast;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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

    }

    @SingleClick
    @Override
    public void onClick(SuperTextView view) {
        int id = view.getId();
        if (id == R.id.shan) {

            //openNewPage(SettingsFragment.class);
        } /*else if (id == R.id.open_debugable) {
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

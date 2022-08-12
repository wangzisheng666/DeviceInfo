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

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.mobiles.devices.R;
import com.mobiles.devices.core.BaseFragment;
import com.mobiles.devices.databinding.FragmentOpenEnvBinding;
import com.mobiles.devices.fragment.other.AboutFragment;
import com.mobiles.devices.fragment.other.SettingsFragment;
import com.mobiles.devices.utils.Utils;
import com.mobiles.devices.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.io.IOException;


/**
 * @author xuexiang
 * @since 2019-10-30 00:18
 */
@Page(anim = CoreAnim.none)
public class OpenEnvFragment extends BaseFragment<FragmentOpenEnvBinding> implements SuperTextView.OnSuperTextViewClickListener {

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

    }

    @Override
    protected void initListeners() {
        binding.openDebugable.setOnSuperTextViewClickListener(this);
        binding.open1.setOnSuperTextViewClickListener(this);


       /* binding.openDebugable.setSwitchCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                XToastUtils.toast("GPU测评开启");
            }else { XToastUtils.toast("GPU测评关闭");
                 }
        });*/

        binding.openDebugable.setOnSuperTextViewClickListener(superTextView -> superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false)).setSwitchCheckedChangeListener((buttonView, isChecked)  -> {
            if(isChecked){
                XToastUtils.success("ro.debug开启");
            }else {
                XToastUtils.error("ro.debug关闭");
            }
        });

        binding.openFrida.setOnSuperTextViewClickListener(superTextView -> superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false)).setSwitchCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                XToastUtils.success("frida加强版开启");
            }else {
                XToastUtils.error("frida加强版关闭");
            }
        });

        binding.openFridaBig.setOnSuperTextViewClickListener(superTextView -> superTextView.setSwitchIsChecked(!superTextView.getSwitchIsChecked(), false)).setSwitchCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                XToastUtils.success("frida开启");
            }else {
                XToastUtils.error("frida关闭");
            }
        });

        try {
            Utils.run("myfs1","");
            Utils.run("myfs1","");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
}

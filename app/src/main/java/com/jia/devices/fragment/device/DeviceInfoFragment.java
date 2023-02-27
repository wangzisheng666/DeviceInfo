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

package com.jia.devices.fragment.device;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.jia.devices.adapter.base.delegate.SimpleDelegateAdapter;
import com.jia.devices.adapter.entity.NewInfo;
import com.jia.devices.core.BaseFragment;
import com.jia.devices.databinding.FragmentDeviceInfoBinding;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;

/**
 * 设备信息
 *
 * @author xuexiang
 * @since 2019-10-30 00:15
 */
@Page(anim = CoreAnim.none)
public class DeviceInfoFragment extends BaseFragment<FragmentDeviceInfoBinding> {

    private SimpleDelegateAdapter<NewInfo> mNewsAdapter;

    @NonNull
    @Override
    protected FragmentDeviceInfoBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentDeviceInfoBinding.inflate(inflater, container, false);
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
        /*//下拉刷新
        binding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mNewsAdapter.refresh(DemoDataProvider.getDemoNewInfos());
                refreshLayout.finishRefresh();
            }, 1000);
        });
        //上拉加载
        binding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mNewsAdapter.loadMore(DemoDataProvider.getDemoNewInfos());
                refreshLayout.finishLoadMore();
            }, 1000);
        });
        binding.refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果*/
    }
}

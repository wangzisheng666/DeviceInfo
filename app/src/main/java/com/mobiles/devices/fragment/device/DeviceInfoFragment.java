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

package com.mobiles.devices.fragment.device;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.mobiles.devices.R;
import com.mobiles.devices.adapter.base.delegate.SimpleDelegateAdapter;
import com.mobiles.devices.adapter.base.delegate.SingleDelegateAdapter;
import com.mobiles.devices.adapter.entity.NewInfo;
import com.mobiles.devices.core.BaseFragment;
import com.mobiles.devices.adapter.base.broccoli.BroccoliSimpleDelegateAdapter;
import com.mobiles.devices.databinding.FragmentDeviceInfoBinding;
import com.mobiles.devices.utils.DemoDataProvider;
import com.mobiles.devices.utils.Utils;
import com.mobiles.devices.utils.XToastUtils;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleImageBanner;
import com.xuexiang.xui.widget.imageview.ImageLoader;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import me.samlss.broccoli.Broccoli;

/**
 * ????????????
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
        /*//????????????
        binding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            // TODO: 2020-02-25 ?????????????????????????????????
            refreshLayout.getLayout().postDelayed(() -> {
                mNewsAdapter.refresh(DemoDataProvider.getDemoNewInfos());
                refreshLayout.finishRefresh();
            }, 1000);
        });
        //????????????
        binding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            // TODO: 2020-02-25 ?????????????????????????????????
            refreshLayout.getLayout().postDelayed(() -> {
                mNewsAdapter.loadMore(DemoDataProvider.getDemoNewInfos());
                refreshLayout.finishLoadMore();
            }, 1000);
        });
        binding.refreshLayout.autoRefresh();//????????????????????????????????????????????????*/
    }
}

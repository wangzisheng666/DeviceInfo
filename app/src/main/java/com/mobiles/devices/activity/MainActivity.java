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

package com.mobiles.devices.activity;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mobiles.devices.R;
import com.mobiles.devices.core.BaseFragment;
import com.mobiles.devices.databinding.ActivityMainBinding;
import com.mobiles.devices.fragment.device.DeviceInfoFragment;
import com.mobiles.devices.fragment.environmental.EnvironmentalTestingFragment;
import com.mobiles.devices.fragment.openEnv.OpenEnvFragment;
import com.mobiles.devices.core.BaseActivity;
import com.mobiles.devices.fragment.other.AboutFragment;
import com.mobiles.devices.utils.ServiceUtils;
import com.mobiles.devices.utils.XToastUtils;
import com.mobiles.devices.widget.GuideTipsDialog;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xutil.XUtil;
import com.xuexiang.xutil.common.ClickUtils;
import com.xuexiang.xutil.common.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 程序主页面,只是一个简单的Tab例子
 *
 * @author xuexiang
 * @since 2019-07-07 23:53
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener, ClickUtils.OnClick2ExitListener, Toolbar.OnMenuItemClickListener {

    private String[] mTitles;

    @Override
    protected ActivityMainBinding viewBindingInflate(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       String aa = Build.ID;
        Log.i("33333",aa);
// getprop ro.build.id
      /*  try {
           // ServiceUtils.getiMikRom().shellExec("injectprop  ro.build.id 2039");
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
        initViews();

        initData();

        initListeners();
    /*    try {
            ServiceUtils.getiMikRom().writeFile("/data/system/IDevice_conf","{\n" +
                    "    \"language\":[\n" +
                    "        {\"id\":1, \"tools\":\"XCode111111\", \"name\":\"Swift\"},\n" +
                    "        {\"id\":2, \"tools\":\"Eclipse\", \"name\":\"Java\"},\n" +
                    "        {\"id\":3, \"tools\":\"Visual Studio\", \"name\":\"C#\"}\n" +
                    "    ],\n" +
                    "    \"manufacturer\":\"manufacturer\",\n" +
                    "    \"ro.build.id\":\"testcxf33333\",\n" +
                    "    \"ro.product.name\":\"test_cxf333\"\n" +
                    "}");

           // ServiceUtils.getiMikRom().shellExec("setprop ctl.restart zygote_secondary");
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/


    }


    @Override
    protected boolean isSupportSlideBack() {
        return false;
    }

    private void initViews() {
        WidgetUtils.clearActivityBackground(this);

        mTitles = ResUtils.getStringArray(R.array.home_titles);
        binding.includeMain.toolbar.setTitle(mTitles[0]);
        binding.includeMain.toolbar.inflateMenu(R.menu.menu_main);
        binding.includeMain.toolbar.setOnMenuItemClickListener(this);


        //主页内容填充
        BaseFragment[] fragments = new BaseFragment[]{
                new OpenEnvFragment(),
                new EnvironmentalTestingFragment(),
                new DeviceInfoFragment()

        };
        FragmentAdapter<BaseFragment> adapter = new FragmentAdapter<>(getSupportFragmentManager(), fragments);
        binding.includeMain.viewPager.setOffscreenPageLimit(mTitles.length - 1);
        binding.includeMain.viewPager.setAdapter(adapter);
    }

    private void initData() {
       // GuideTipsDialog.showTips(this);
        //XUpdateInit.checkUpdate(this, false);
    }



    protected void initListeners() {
      /*  ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.includeMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();*/

        //主页事件监听
        binding.includeMain.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MenuItem item = binding.includeMain.bottomNavigation.getMenu().getItem(position);
                binding.includeMain.toolbar.setTitle(item.getTitle());
                item.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.includeMain.bottomNavigation.setOnNavigationItemSelectedListener(this);
    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_privacy) {
            String bb ="{\"ua\":\"Mozilla/5.0 (Linux; Android 10; Reno) AppleWebKit/ 537.36 (KHTML, like Gecko) Chrome/90.0.4430.91 Mobile Safari/537.36\",\"javaUa\":\"Dalvik/2.1.0 (Linux; U; Android 10; Reno Build/CFMG.202143.065)\",\"imei\":\"860005483227623\",\"imsi\":\"860005485645681\",\"mac\":\"B1:DE:6B:8F:89:05\",\"bssid\":\"37:DB:63:D2:B4:A8\",\"ssid\":\"W35LlyN\",\"phone\":\"+819066031821\",\"phoneStatus\":\"1\",\"phoneType\":1,\"width\":\"720\",\"height\":\"1560\",\"densityDpi\":340,\"xdpi\":\"343.628\",\"ydpi\":\"343.628\",\"carrier\":\"NTT\",\"mcc\":\"440\",\"mnc\":\"10\",\"carrierCode\":\"44010\",\"countryCode\":\"JP\",\"latitude\":\"33.488185\",\"longitude\":\"134.396803\",\"simSerial\":\"89810495833779614901\",\"simStatus\":\"5\",\"androidId\":\"cf9715e520d96c2b\",\"androidSerial\":\"9187E2181E952E\",\"ip\":\"\",\"diskSize\":\"13648998\",\"networkType\":\"0\",\"networkTypeName\":\"MOBILE\",\"networkSubType\":13,\"networkSubTypeName\":\"LTE\",\"blueTooth\":\"94:56:F5:A6:53:56\",\"email\":\"OS3tf@gmail.com\",\"Sensors\":{\"ACCELEROMETER\":{\"NAME\":\"AK09918-pseudo-gyro\",\"VENDOR\":\"MTK\"},\"GYROSCOPE\":{\"NAME\":\"lsm6dsm\",\"VENDOR\":\"lsm6dsm_acc\"}},\"buildInfo\":{\"board\":\"coral\",\"bootloader\":\"c2f2-0.4-8048765\",\"brand\":\"google\",\"cpu_abi\":\"arm64-v8a\",\"cpu_abi2\":\"arm64-v8a\",\"cpu_abilist\":\"arm64-v8a,armeabi-v7a,armeabi\",\"device\":\"coral\",\"display\":\"TD1A.221105.001\",\"radioVersion\":\"YXMS0-00060-2014770638\",\"increment\":\"1673581186\",\"fingerPrint\":\"Oppo/PCAM00/PCAM00:10/74ELYT-660.6153.24/1899793:user/release-keys\",\"hardWare\":\"coral\",\"host\":\"cxf-System\",\"id\":\"TD1A.221105.001\",\"manufacture\":\"Google\",\"serial\":\"9187E2181E952E\",\"product\":\"coral\",\"tags\":\"release-keys\",\"time\":1595651339149,\"type\":\"user\",\"user\":\"cxf\",\"sdk\":\"33\",\"sdkInit\":\"33\",\"model\":\"Pixel 4 XL\",\"osName\":\"13\",\"osArch\":\"armv7l\",\"osVersion\":\"13\",\"androidVersion\":\"13\",\"SECURITY_PATCH\":\"2022-11-05\"}}";
            try {
                Map<String,String> map_new=new HashMap<>();
                Map mapsRe = (Map) JSON.parse(bb);
                for (Object map_re : mapsRe.entrySet()){

                    Log.i("map_re集合",((Map.Entry)map_re).getKey()+"     " + ((Map.Entry)map_re).getValue());

                    for (Object map__tj : EnvironmentalTestingFragment.map_tj.entrySet()){

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

                ServiceUtils.getiMikRom().writeFile("/data/system/IDevice_conf",bb);
                ServiceUtils.getiMikRom().shellExec("setprop ctl.restart zygote_secondary");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            // GuideTipsDialog.showTipsForce(this);
        } else if (id == R.id.action_about) {

            try {
                ServiceUtils.getiMikRom().shellExec("pm clear  com.ytheekshana.deviceinfo");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            //openNewPage(AboutFragment.class);
        }
        return false;
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        int id = v.getId();
      /*  if (id == R.id.nav_header) {

        }*/
    }


    /**
     * 底部导航栏点击事件
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = CollectionUtils.arrayIndexOf(mTitles, menuItem.getTitle());
        if (index != -1) {
            binding.includeMain.toolbar.setTitle(menuItem.getTitle());
            binding.includeMain.viewPager.setCurrentItem(index, false);

            return true;
        }
        return false;
    }



    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ClickUtils.exitBy2Click(2000, this);
        }
        return true;
    }

    @Override
    public void onRetry() {
        XToastUtils.toast("再按一次退出程序");
    }

    @Override
    public void onExit() {
        XUtil.exitApp();
    }




}

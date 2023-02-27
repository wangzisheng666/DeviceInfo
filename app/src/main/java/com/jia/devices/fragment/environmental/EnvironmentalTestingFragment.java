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

package com.jia.devices.fragment.environmental;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jia.devices.R;
import com.jia.devices.core.BaseFragment;
import com.jia.devices.databinding.FragmentEnvironmentalTestingBinding;
import com.jia.devices.fragment.settings.CornerListView;
import com.jia.devices.fuction.Build_device_info;
import com.jia.devices.fuction.check_SElinux;
import com.jia.devices.fuction.check_root;
import com.jia.devices.utils.ServiceUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 环境检测
 * @author prince
 * @since 2019-10-30 00:19
 */
@Page(anim = CoreAnim.none)
public class EnvironmentalTestingFragment extends BaseFragment<FragmentEnvironmentalTestingBinding> implements SuperTextView.OnSuperTextViewClickListener {

    @NonNull
    @Override
    protected FragmentEnvironmentalTestingBinding viewBindingInflate(LayoutInflater inflater, ViewGroup container) {
        return FragmentEnvironmentalTestingBinding.inflate(inflater, container, false);
    }

    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    TextView textView;

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

             /*   try {
                    ServiceUtils.getiMikRom().shellExec("setprop ctl.restart zygote_secondary");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }*/

            }
        });


        Build_device_info.showSystemParameter();
        check_root.showSystemParameter();
        check_SElinux.SElinux();
        //check_adb.chenk_all();





    }

    @Override
    protected void initListeners() {
       // binding.envRoot.setOnSuperTextViewClickListener(this);
    }



    @Override
    public void onClick(SuperTextView superTextView) {
        int id = superTextView.getId();
      /*  if (id == R.id.env_root) {
            XToastUtils.toast("root");
            openNewPage(CheckResultFragment.class,"test","test");
        } else if (id == R.id.env_adb) {
            XToastUtils.toast("adb");
        }else if (id == R.id.env_frida) {
            XToastUtils.toast("frida");
        }*/
    }




    public static final int UPDATE_TEXT=1;
    private static String string;
    public static Map<String,String> map_tj=new HashMap<>();
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==UPDATE_TEXT){
                // tvContent.setText(string);
                textView.setText(string);

                JSONObject jsonObject = new JSONObject(JSON.parseObject(string));

                JSONObject js =jsonObject.getJSONObject("buildInfo");
                String str = js.toString();

                Map mapsRe = (Map)JSON.parse(str);



                map_tj.put("board", "ro.product.board");
                Log.i("buildInfo_BOARD", Build.BOARD);
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
                Log.i("buildInfo_",String.valueOf(Build.VERSION.PREVIEW_SDK_INT));
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


            }
            super.handleMessage(msg);
        }
    };


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
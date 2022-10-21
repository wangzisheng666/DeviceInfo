package com.mobiles.devices.utils;

import android.util.Log;

import com.topjohnwu.superuser.Shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Shell_Util {
    public static String  shell(String cmd){
        Shell.Result result;
        result = Shell.cmd(cmd).exec();
        List<String> out = result.getOut();  // stdout
        int code = result.getCode();         // return code of the last command
        boolean ok = result.isSuccess();     // return code == 0?
        return out.toString();

    }
    /**
     * 执行 adb 命令
     *
     * @param cmd 命令
     * @return
     */
    public static StringBuffer shellExec(String cmd) {
        Runtime mRuntime = Runtime.getRuntime(); //执行命令的方法
        try {
            //Process中封装了返回的结果和执行错误的结果
            Process mProcess = mRuntime.exec(cmd); //加入参数
            //使用BufferReader缓冲各个字符，实现高效读取
            //InputStreamReader将执行命令后得到的字节流数据转化为字符流
            //mProcess.getInputStream()获取命令执行后的的字节流结果
            BufferedReader mReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()));
            //实例化一个字符缓冲区
            StringBuffer mRespBuff = new StringBuffer();
            //实例化并初始化一个大小为1024的字符缓冲区，char类型
            char[] buff = new char[1024];
            int ch = 0;
            //read()方法读取内容到buff缓冲区中，大小为buff的大小，返回一个整型值，即内容的长度
            //如果长度不为null
            while ((ch = mReader.read(buff)) != -1) {
                //就将缓冲区buff的内容填进字符缓冲区
                mRespBuff.append(buff, 0, ch);
            }
            //结束缓冲
            mReader.close();
            Log.i("shell", "shellExec: " + mRespBuff);
            //弹出结果
//            Log.i("shell", "执行命令: " + cmd + "执行成功");
            return mRespBuff;

        } catch (IOException e) {
            // 异常处理
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}

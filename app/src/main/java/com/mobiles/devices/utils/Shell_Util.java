package com.mobiles.devices.utils;

import com.topjohnwu.superuser.Shell;

import java.util.List;

public class Shell_Util {
    public static String  shell(String cmd){
        Shell.Result result;
        result = Shell.cmd(cmd).exec();
        List<String> out = result.getOut();  // stdout
        result = Shell.cmd(cmd).exec();
        int code = result.getCode();         // return code of the last command
        boolean ok = result.isSuccess();     // return code == 0?
        return out.toString();

    }
}

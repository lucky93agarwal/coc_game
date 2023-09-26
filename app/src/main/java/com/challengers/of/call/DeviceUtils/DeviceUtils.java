package com.challengers.of.call.DeviceUtils;

import android.content.Context;

import java.io.File;

public class DeviceUtils {

    public Boolean isDeviceRooted(Context context){
        boolean isRooted = isrooted1() || isrooted2();
        return isRooted;
    }

    private boolean isrooted1() {

        File file = new File("/system/app/Superuser.apk");
        if (file.exists()) {
            return true;
        }
        return false;
    }

    // try executing commands
    private boolean isrooted2() {
        return canExecuteCommand("/system/app/com.truedevelopersstudio.automatictap.autoclicker.apk");
    }


    private static boolean canExecuteCommand(String command) {
        boolean executedSuccesfully;
        try {
            Runtime.getRuntime().exec(command);
            executedSuccesfully = true;
        } catch (Exception e) {
            executedSuccesfully = false;
        }

        return executedSuccesfully;
    }
}

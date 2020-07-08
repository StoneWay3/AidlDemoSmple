package com.stone.aidldemo;

import android.app.ActivityManager;
import android.content.Context;

import java.util.Iterator;
import java.util.List;

/**
 * create by StoneWay on 2020/7/8
 */
public class MyUtils {


    public static boolean isServiceRunning(Context ctx, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        List servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
        Iterator l = servicesList.iterator();

        while (l.hasNext()) {
            ActivityManager.RunningServiceInfo si = (ActivityManager.RunningServiceInfo) l.next();
            if (className.equals(si.service.getClassName())) {
                isRunning = true;
            }
        }

        return isRunning;
    }
}

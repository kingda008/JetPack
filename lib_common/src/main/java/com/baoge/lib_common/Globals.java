package com.baoge.lib_common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 获取上下文
 */
public class Globals {
    private static Application sApplication;

    /**
     * 反射获取当前Application
     *
     * @return
     */
    @SuppressLint("PrivateApi")
    public static Application getsApplication() {
        if (sApplication == null) {
            //去反射得到
            try {
                Class<?> aClass = Class.forName("android.app.ActivityThread");
                //获取里面的currentApplication
                Method currentApplication = aClass.getDeclaredMethod("currentApplication");
                sApplication = (Application) currentApplication.invoke(null,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sApplication;
    }


    /**
     * 获取当前Activity
     *
     * @return
     */
    public static Activity getActivity() {
        Class activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }


}

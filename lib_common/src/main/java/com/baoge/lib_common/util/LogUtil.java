package com.baoge.lib_common.util;

import android.text.TextUtils;
import android.util.Log;

public class LogUtil {
    private static String TAG = "BaoGe";

    public static void i(String content) {
        if (!TextUtils.isEmpty(content)) {
            Log.i(TAG, content);
        }
    }

    public static void e(String content) {
        if (!TextUtils.isEmpty(content)) {
            Log.e(TAG, content);
        }
    }
}

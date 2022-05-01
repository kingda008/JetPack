package com.baoge.ft_login.app_dir;

import com.baoge.lib_common.base.BaseApplication;
import com.baoge.lib_common.util.LogUtil;

public class LiveDataApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i(LiveDataApplication.class.getSimpleName());
    }
}

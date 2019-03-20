package com.example.baselibrary.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;

public class BaseModuleApplication extends Application {

    private static BaseModuleApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Utils.init(this);
        AppContext.initARouter();
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static BaseModuleApplication getInstance() {
        return mInstance;
    }
}

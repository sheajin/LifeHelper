package com.xy.lifehelper.base.app;

import android.app.Application;

import com.xy.lifehelper.di.component.ApplicationComponent;
import com.xy.lifehelper.di.component.DaggerApplicationComponent;
import com.xy.lifehelper.di.module.ApplicationModule;


/**
 * Created by jxy on 2018/6/7.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initApplicationComponent();
        myApplication = this;
        //Bugly
//        CrashReport.initCrashReport(getApplicationContext(), Constant.BUGLY_ID, false);
    }

    public static synchronized MyApplication getInstance() {
        return myApplication;
    }

    /**
     * 初始化ApplicationComponent
     */
    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
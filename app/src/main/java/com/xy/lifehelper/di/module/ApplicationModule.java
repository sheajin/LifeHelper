package com.xy.lifehelper.di.module;

import android.content.Context;


import com.xy.lifehelper.base.app.MyApplication;

import dagger.Module;
import dagger.Provides;


/**
 * Created by lw on 2017/1/19.
 */
@Module
public class ApplicationModule {
    private MyApplication mApplication;

    public ApplicationModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }
}

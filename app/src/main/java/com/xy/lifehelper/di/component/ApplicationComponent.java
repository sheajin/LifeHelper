package com.xy.lifehelper.di.component;

import android.content.Context;


import com.xy.lifehelper.di.module.ApplicationModule;

import dagger.Component;


/**
 * Created by lw on 2017/1/19.
 */
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getApplication();
}
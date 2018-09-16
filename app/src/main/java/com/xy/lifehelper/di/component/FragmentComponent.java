package com.xy.lifehelper.di.component;

import android.app.Activity;


import com.xy.lifehelper.di.module.FragmentModule;

import dagger.Component;

/**
 * Created by jxy on 2018/7/17.
 */

@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();
//
//    void inject(HomePageFragment fragment);
//
//    void inject(KnowledgeFragment fragment);
//
//    void inject(KnowledgeListFragment fragment);
//
//    void inject(ProjectFragment fragment);
//
//    void inject(ProjectListFragment fragment);
//
//    void inject(LiveListFragment fragment);

}

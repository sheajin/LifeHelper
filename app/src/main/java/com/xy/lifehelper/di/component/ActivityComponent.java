package com.xy.lifehelper.di.component;

import android.app.Activity;


import com.xy.lifehelper.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by jxy on 2018/7/17.
 */

@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
//
//    void inject(LoginActivity activity);
//
//    void inject(RegisterActivity activity);
//
//    void inject(ArticleDetailsActivity activity);
//
//    void inject(HotActivity activity);
//
//    void inject(SearchActivity activity);
//
//    void inject(SearchResultActivity activity);
//
//    void inject(MyCollectActivity activity);
//
//    void inject(VideoActivity activity);
//
//    void inject(LiveActivity activity);

}

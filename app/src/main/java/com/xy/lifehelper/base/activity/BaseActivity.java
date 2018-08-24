package com.xy.lifehelper.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xy.lifehelper.base.app.MyApplication;
import com.xy.lifehelper.base.presenter.AbsPresenter;
import com.xy.lifehelper.base.view.AbstractView;
import com.xy.lifehelper.di.component.ActivityComponent;
import com.xy.lifehelper.di.component.DaggerActivityComponent;
import com.xy.lifehelper.di.module.ActivityModule;
import com.xy.lifehelper.model.constant.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by jxy on 2018/1/8.
 */

public abstract class BaseActivity<T extends AbsPresenter> extends SupportActivity implements AbstractView {
    protected MyApplication context;
    protected BaseActivity activity;
    protected ActivityComponent mActivityComponent;
    private int netMobile;

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        context = MyApplication.getInstance();
        activity = this;
        initActivityComponent();
        initBind();
        initInject();
        onViewCreated();
        initToolbar();
        initUI();
        initData();
    }

    /**
     * 初始化ActivityComponent
     */
    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    protected void onViewCreated() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * init ToolBar
     */
    protected void initToolbar() {
    }

    /**
     * 获取当前Activity的UI布局
     */
    protected abstract int getLayoutId();

    public void initBind() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onMessageEvent(MessageEvent event) {
    }

    /**
     * dagger初始化
     */
    protected void initInject() {

    }

    /**
     * 界面初始化
     */
    protected abstract void initUI();

    /**
     * 数据初始化
     */
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    /**
     * 网络变化之后的类型
     */


    /**
     * 判断有无网络
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        return false;
    }

    /**
     * 设置可见
     */
    @Override
    public void setVisible(View... views) {
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置隐藏
     */
    @Override
    public void setInVisible(View... views) {
        for (View v : views) {
            v.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置不可见
     */
    @Override
    public void setGone(View... views) {
        for (View v : views) {
            v.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showNormal() {

    }

    @Override
    public void reload() {

    }

}

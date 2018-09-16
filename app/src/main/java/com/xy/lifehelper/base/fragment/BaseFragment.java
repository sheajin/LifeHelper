package com.xy.lifehelper.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xy.lifehelper.base.app.MyApplication;
import com.xy.lifehelper.base.presenter.AbsPresenter;
import com.xy.lifehelper.base.view.AbstractView;
import com.xy.lifehelper.di.component.DaggerFragmentComponent;
import com.xy.lifehelper.di.component.FragmentComponent;
import com.xy.lifehelper.di.module.FragmentModule;
import com.xy.lifehelper.model.constant.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by jxy on 2018/1/13.
 */

public abstract class BaseFragment<T extends AbsPresenter> extends SupportFragment implements AbstractView {
    public View rootView;
    protected Activity activity;
    protected MyApplication context;
    protected FragmentComponent mFragmentComponent;
    private int netMobile;

    @Inject
    protected T mPresenter;

    public BaseFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResID(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        activity = getActivity();
        context = MyApplication.getInstance();
        initFragmentComponent();
        initInjector();
        onViewCreated();
        initBind(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initUI();
        initData();
    }

    /**
     * 获取当前界面的UI布局
     */
    public abstract int getLayoutResID();

    /**
     * dagger初始化
     */
    protected void initInjector() {
    }

    /**
     * 界面初始化
     */
    protected abstract void initUI();

    /**
     * 数据初始化
     */
    protected abstract void initData();

    public void initBind(View view) {
        ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    protected void onViewCreated() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 初始化FragmentComponent
     */
    private void initFragmentComponent() {
        mFragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(MyApplication.getInstance().getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
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
    public void showLoading() {

    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void reload() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

    }

}

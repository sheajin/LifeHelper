package com.xy.lifehelper.ui;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

import com.xy.lifehelper.R;
import com.xy.lifehelper.base.activity.BaseActivity;
import com.xy.lifehelper.util.StatusBarUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

//    @BindView(R.id.drawerLayout)
//    DrawerLayout mDrawerLayout;
//    @BindView(R.id.pager)
//    ViewPager mpager;
    //

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI() {
        StatusBarUtil.setFullScreeen(this);
    }

    @Override
    protected void initData() {

    }

}

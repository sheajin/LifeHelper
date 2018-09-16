package com.xy.lifehelper.ad;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.xy.lifehelper.R;
import com.xy.lifehelper.base.activity.BaseActivity;
import com.xy.service.Book;
import com.xy.service.BookAidlInterface;

import butterknife.OnClick;

/**
 * Created by jxy on 2018/8/23.
 */

/**
 *
 * 由于是两个项目，需要先启动服务端项目(Service项目)，在启动客户端项目。
 */

public class EmptyActivity extends BaseActivity {

    private boolean isBind = false;
    public BookAidlInterface bookAidlInterface = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_empty;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            isBind = true;
            Log.e("TAG", "onServiceConnected = " + isBind);
            bookAidlInterface = BookAidlInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
        }
    };

    @OnClick({R.id.btn_bind, R.id.btn_unbind})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_bind:
                connectService();
                if (isBind) {
                    try {
                        bookAidlInterface.addBook(new Book("bookName"));
                        Log.e("TAG", "EmptyActivity size =  " + bookAidlInterface.getBookSize() + ",bookList =" + bookAidlInterface.getBookList().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    connectService();
                }
                break;
            case R.id.btn_unbind:
                if (isBind)
                    unbindService(connection);
                break;
        }
    }

    private void connectService() {
        Intent intent = new Intent();
        intent.setAction("com.xy.service.MyService.action");
        intent.setPackage("com.xy.service");
        bindService(intent, connection, BIND_AUTO_CREATE);
//        intent.setComponent(new ComponentName("com.xy.service", "com.xy.service.MyService"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind)
            unbindService(connection);
    }
}

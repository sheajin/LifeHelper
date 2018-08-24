package com.xy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jxy on 2018/8/23.
 */

public class MyService extends Service {

    private List<Book> bookList = new ArrayList<>();

    private final BookAidlInterface.Stub bookAidlInterface = new BookAidlInterface.Stub() {
        @Override
        public void addBook(Book book) throws RemoteException {
            if (bookList == null) {
                bookList = new ArrayList<>();
            }
            bookList.add(book);
            Log.e("TAG", "MyService addBook  ");
        }

        @Override
        public int getBookSize() throws RemoteException {
            return bookList.size();
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return bookList;
        }


    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return bookAidlInterface;
    }
}

// BookAidlInterface.aidl
package com.xy.service;

import com.xy.service.Book;

interface BookAidlInterface {

    void addBook(in Book book);

    int getBookSize();

    List<Book> getBookList();
}

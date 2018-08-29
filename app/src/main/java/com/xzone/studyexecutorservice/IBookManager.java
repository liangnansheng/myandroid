package com.xzone.studyexecutorservice;

import android.os.IBinder;
import android.os.IInterface;

import java.util.List;

/**
 * Created by xl on 2018/6/30.
 */

public interface IBookManager extends IInterface {
    static final String DESCRIPTOR = "com.xzone.studyexecutor.IBookManager";
    static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION + 0;
    static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION + 1;

    public List<Book> getBookList();

    public void addBook(Book book);

}

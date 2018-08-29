package com.xzone.studyexecutorservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.List;

/**
 * Created by xl on 2018/6/30.
 */

public class BookManagerImpl extends Binder implements IBookManager {
    public BookManagerImpl() {
        attachInterface(this, DESCRIPTOR);
    }

    @Override
    public List<Book> getBookList() {

        return null;
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    public static IBookManager asInterface(IBinder obj) {
        if (obj == null) {
            return null;
        }
        IInterface iInterface = obj.queryLocalInterface(DESCRIPTOR);
        if (iInterface != null && iInterface instanceof IBookManager) {
            return (IBookManager) iInterface;
        }
        return new Proxy(obj);

    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;
            case TRANSACTION_addBook:
                data.enforceInterface(DESCRIPTOR);
                Book arg0;
                if (0 != data.readInt()) {
                    arg0 = Book.CREATOR.createFromParcel(data);
                } else {
                    arg0 = null;
                }
                this.addBook(arg0);
                reply.writeNoException();
                return true;
            case TRANSACTION_getBookList:
                data.enforceInterface(DESCRIPTOR);
                List<Book> bookList = getBookList();
                reply.writeNoException();
                reply.writeTypedList(bookList);
                return true;

        }
        return super.onTransact(code, data, reply, flags);
    }

    private static class Proxy implements IBookManager {
        private IBinder mRemote;

        Proxy(IBinder binder) {
            mRemote = binder;
        }

        @Override
        public List<Book> getBookList() {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            List<Book> list = null;
            data.writeInterfaceToken(DESCRIPTOR);
            try {
                mRemote.transact(TRANSACTION_getBookList, data, reply, 0);
                reply.readException();
                list = reply.createTypedArrayList(Book.CREATOR);
            } catch (RemoteException e) {
                e.printStackTrace();
            } finally {
                reply.recycle();
                data.recycle();
            }
            return list;
        }

        @Override
        public void addBook(Book book) {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                if (book != null) {
                    data.writeInt(1);
                    book.writeToParcel(data, 0);
                } else {
                    data.writeInt(0);
                }
                mRemote.transact(TRANSACTION_addBook, data, reply, 0);
                reply.readException();
            } catch (RemoteException e) {
                e.printStackTrace();
            } finally {
                reply.recycle();
                data.recycle();
            }
        }

        @Override
        public IBinder asBinder() {
            return mRemote;
        }
    }
}

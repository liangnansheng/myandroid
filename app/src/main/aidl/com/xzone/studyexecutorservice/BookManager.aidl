// BookManager.aidl
package com.xzone.studyexecutorservice;

// Declare any non-default types here with import statements
import com.xzone.studyexecutorservice.Book;
import com.xzone.studyexecutorservice.IOnNewBookArrivedListener;

interface BookManager {


           void  addBook(in Book book);
           List<Book> getBooks();
           void registListener(IOnNewBookArrivedListener listener);
                      void unRegistListener(IOnNewBookArrivedListener listener);

}

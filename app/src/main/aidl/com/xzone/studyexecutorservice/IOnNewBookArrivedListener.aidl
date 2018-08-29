// IOnNewBookArrivedListener.aidl
package com.xzone.studyexecutorservice;

// Declare any non-default types here with import statements
import com.xzone.studyexecutorservice.Book;

interface IOnNewBookArrivedListener {
   void onNewBookArrived(in Book book);
}

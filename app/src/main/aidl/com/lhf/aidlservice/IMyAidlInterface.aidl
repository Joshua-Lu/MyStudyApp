// IMyAidlInterface.aidl
package com.lhf.aidlservice;

// Declare any non-default types here with import statements
import com.lhf.aidlservice.User;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
    String getData();
    User getUser();
}

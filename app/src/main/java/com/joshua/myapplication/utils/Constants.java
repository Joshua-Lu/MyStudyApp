package com.joshua.myapplication.utils;

import android.net.Uri;

/**
 * Created by Joshua on 2020/11/6 0:16
 */
public class Constants {
    // 数据库相关
    public static final String DB_NAME = "joshua.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "person";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_AGE = "age";
    // provider相关
    public static final String AUTHORITIES_PERSON = "com.joshua.myapplication.provider.PersonProvider";
    public static Uri personUri = Uri.parse("content://" + AUTHORITIES_PERSON + "/person");
}

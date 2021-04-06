package com.joshua.myapplication.utils;

import android.net.Uri;

/**
 * Created by Joshua on 2020/11/6 0:16
 */
public class Constants {
    /**
     * 数据库相关
     */
    public static final String DB_NAME = "joshua.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "person";
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_AGE = "age";

    /**
     * provider相关
     */
    public static final String AUTHORITIES_PERSON = "com.joshua.myapplication.provider.PersonProvider";
    public static Uri personUri = Uri.parse("content://" + AUTHORITIES_PERSON + "/person");

    /**
     * 图片地址
     */
    public static String imageUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp2.so.qhmsg.com%2Ft01deb223c38d031ce2.jpg&refer=http%3A%2F%2Fp2.so.qhmsg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620311281&t=e26a96cf289789ae04e8bea0e13c51f1";
    public static String gifUrl = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F201210%2F09%2F20121009215819_tK3Pc.thumb.700_0.gif&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620311546&t=871cf7c9477802c88f4d8c773ff3ecf9";
}

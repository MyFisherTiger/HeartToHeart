package com.me.hearttoheart.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/4/2.
 */
public class CacheUtil {

    //得到缓存的值
    public static boolean getState(Context context, String key) {
        SharedPreferences sp=context.getSharedPreferences("HTH",Context.MODE_PRIVATE);//HeartToHeart
        return sp.getBoolean(key,false);
    }

    public static void setState(Context context, String key, Boolean value) {
        SharedPreferences sp=context.getSharedPreferences("HTH",Context.MODE_PRIVATE);//HeartToHeart
        sp.edit().putBoolean(key,value).commit();
    }
}

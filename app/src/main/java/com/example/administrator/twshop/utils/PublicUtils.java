package com.example.administrator.twshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bj on 2017/3/30.
 */

public class PublicUtils {


    /*引导页需要的工具类*/

    private static final String spFileName = "app";

    public static void putBoolean(Context context, String strKey,
                                  Boolean strData) {
        SharedPreferences activityPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putBoolean(strKey, strData);
        editor.commit();
    }

    public static Boolean getBoolean(Context context, String strKey) {
        SharedPreferences setPreferences = context.getSharedPreferences(
                spFileName, Context.MODE_PRIVATE);
        Boolean result = setPreferences.getBoolean(strKey, false);
        return result;
    }
}

package com.example.administrator.twshop.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastUtils {
    /**
     * Toast提示
     *
     * @param message
     */
    private static Toast mToast;

    public static void showToast(Context context, String message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
       // mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }
}

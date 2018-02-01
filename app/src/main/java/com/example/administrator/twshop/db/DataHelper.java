package com.example.administrator.twshop.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2018/1/11 0011.
 */
public class DataHelper {
    private SQLiteDatabase db;
    private static RecordSQLiteOpenHelper openHelpr;
    Context mContext;
    private static DataHelper dataHelper;

    private DataHelper() {
    }

    public static DataHelper getDataHelper() {
        if (dataHelper == null) {
            synchronized (DataHelper.class) {
                if (dataHelper == null) {
                    dataHelper = new DataHelper();
                }
            }
        }
        return dataHelper;
    }

    public void initDBHelpr(Context mContext) {
        this.mContext = mContext;
        DataHelper.openHelpr = new RecordSQLiteOpenHelper(mContext);
    }
}

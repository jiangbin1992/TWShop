package com.example.administrator.twshop.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.administrator.twshop.MainActivity;
import com.example.administrator.twshop.R;
import com.example.administrator.twshop.base.AppManager;
import com.example.administrator.twshop.contants.Constants;
import com.example.administrator.twshop.utils.PublicUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class SplashingActivity extends Activity {

    private static final String IS_FIRST = "appisFirst";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 判断是否是第一次开启应用
        boolean isFirstOpen = PublicUtils.getBoolean(this, Constants.FIRST_OPEN);
        // 如果是第一次启动，则先进入功能引导页
        if (!isFirstOpen) {
            Intent intent = new Intent(SplashingActivity.this, WelcomeGuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_splash);
        AppManager.getAppManager().addActivity(this);
        initView();
    }

    protected void initView() {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {

            public void run() {

                enterHome();
            }

        };

        timer.schedule(task, 1000);

    }

    private void enterHome() {
        Intent intent = new Intent(SplashingActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}

package com.example.administrator.twshop.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.alibaba.sdk.android.feedback.impl.FeedbackAPI;
import com.example.administrator.twshop.R;
import com.example.administrator.twshop.base.BaseActivity;
import com.example.administrator.twshop.utils.CacheUtils;
import com.example.administrator.twshop.utils.ToastUtils;

/**
 * Created by Administrator on 2018/1/10 0010.
 * 设置页面
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout design_iv_back;
    private RelativeLayout design_rl_login;
    private RelativeLayout design_rl_feedback;
    private RelativeLayout design_rl_call;
    private TextView design_tv_out;
    private RelativeLayout design_rl_clean;
    private TextView setting_cache_size;
    private String number;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        design_iv_back = (RelativeLayout) findViewById(R.id.design_iv_back);
        design_rl_login = (RelativeLayout) findViewById(R.id.design_rl_login);
        design_rl_feedback = (RelativeLayout) findViewById(R.id.design_rl_feedback);
        design_rl_call = (RelativeLayout) findViewById(R.id.design_rl_call);
        design_rl_clean = (RelativeLayout) findViewById(R.id.design_rl_clean);
        design_tv_out = (TextView) findViewById(R.id.design_tv_out);
        setting_cache_size = (TextView) findViewById(R.id.setting_cache_size);

        design_iv_back.setOnClickListener(this);
        design_rl_login.setOnClickListener(this);
        design_rl_feedback.setOnClickListener(this);
        design_rl_call.setOnClickListener(this);
        design_tv_out.setOnClickListener(this);
        design_rl_clean.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.design_iv_back:
                finish();
                break;
            case R.id.design_rl_clean:
                ToastUtils.showToast(SettingActivity.this, "清除缓存成功");
                CacheUtils.getInstance().clearImageAllCache(SettingActivity.this);
                setting_cache_size.setText("0.00" + "M");
                break;
            case R.id.design_rl_call:
                //拨打电话
                number = "0371-60300778";
                onCall();
                break;
            case R.id.design_rl_feedback:
                //反馈
                FeedbackAPI.openFeedbackActivity();
                break;
            case R.id.design_rl_login:
                Intent intent = new Intent(SettingActivity.this, FindloginPasswordActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    //处理android6.0拨打电话新特新的权限
    public void onCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(SettingActivity.this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    SettingActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
        } else {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            // startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + number)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    onCall();
                } else {

                    ToastUtils.showToast(SettingActivity.this, "请检查拨打电话的权限");
                }
                break;

            default:
                break;
        }
    }
}

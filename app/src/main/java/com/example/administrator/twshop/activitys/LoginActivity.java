package com.example.administrator.twshop.activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.twshop.Path;
import com.example.administrator.twshop.R;
import com.example.administrator.twshop.base.BaseActivity;
import com.example.administrator.twshop.bean.Login_Data;
import com.example.administrator.twshop.contants.Result;
import com.example.administrator.twshop.utils.ClassPathResource;
import com.example.administrator.twshop.utils.Json;
import com.example.administrator.twshop.utils.SPUtils;
import com.example.administrator.twshop.utils.Share;
import com.example.administrator.twshop.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

/**
 * Created by Administrator on 2018/1/12 0012  下午 2:24
 * 登录
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout back;
    private TextView title_head;
    private TextView register;
    private TextView findpassword;
    private Intent intent;
    private EditText user_name;
    private EditText user_password;
    private Button login;
    private String tel;
    private String password;
    private Context context;
    private Login_Data login_data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = LoginActivity.this;
        initView();
        initEvent();
    }


    private void initView() {
        back = (RelativeLayout) findViewById(R.id.back);
        title_head = (TextView) findViewById(R.id.title_head);
        register = (TextView) findViewById(R.id.register);
        findpassword = (TextView) findViewById(R.id.findpassword);
        user_name = (EditText) findViewById(R.id.user_name);
        user_password = (EditText) findViewById(R.id.user_password);
        login = (Button) findViewById(R.id.login);
    }

    private void initEvent() {
        back.setOnClickListener(this);
        register.setOnClickListener(this);
        findpassword.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            //注册
            case R.id.register:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            //找回密码
            case R.id.findpassword:
                intent = new Intent(LoginActivity.this, FindloginPasswordActivity.class);
                startActivity(intent);
                break;
            //登录
            case R.id.login:
                tel = user_name.getText().toString().trim();
                password = user_password.getText().toString().trim();
                if (TextUtils.isEmpty(tel)) {
                    ToastUtils.showToast(context, getResources().getString(R.string.toast_login));
                    return;
                }
                if (!ClassPathResource.isMobileNO2(tel)) {
                    ToastUtils.showToast(context, getString(R.string.toast_login_phone));
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtils.showToast(context, getString(R.string.toast_login_pwd));
                    return;
                }
                //网络请求
                doLogin();

                break;
            default:
                break;
        }


    }

    /**
     * 登录请求网络
     *
     * @param
     * @param
     */
    private void doLogin() {
        String url = Path.LOGIN_PATH;
        Share.d("网络请求" + url);
        OkGo.<String>post(url)
                .tag(this)
                .retryCount(3)
                .cacheKey("login")
                .cacheMode(CacheMode.DEFAULT)
                .params("username", tel)
                .params("password", password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        Share.d("新网络请求" + response.body());
                        Result result = Json.parse_message(response.body());
                        if (result.isState() && result != null) {
                            //Gson解析
                            Gson gson = new Gson();
                            login_data = gson.fromJson(response.body(), new TypeToken<Login_Data>() {
                            }.getType());
                            if (login_data.getRet().equals("ok")) {
                                handler.sendEmptyMessage(1);
                            } else if (login_data.getRet().equals("err")) {
                                handler.sendEmptyMessage(2);
                            }
                        } else {
                            ToastUtils.showToast(LoginActivity.this, result.getMessage());
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Share.d("错误：" + response.body());
                        handler.sendEmptyMessage(0);
                    }
                });

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    ToastUtils.showToast(LoginActivity.this, getResources().getString(R.string.err_alert));
                    break;
                case 1:
                    if (login_data.getData().toString().length() > 0 && login_data.getData() != null) {
                        Share.d("token" + login_data.getData().getUser_token());
                        SPUtils.put(LoginActivity.this, "token", login_data.getData().getUser_token());
                        //  String image_one = Path.NAME + login_data.getData().getPortrait();
                        //  SPUtils.put(LoginActivity.this, "image_one", image_one);
                        //  String status = String.valueOf(login_data.getData().getStatus());
                        //  String type = login_data.getData().getType();

                        //  SPUtils.put(LoginActivity.this, "status", status);
                        //  SPUtils.put(LoginActivity.this, "type", type);

                        Share.d("取token" + SPUtils.get(LoginActivity.this, "token", ""));
                        //   Share.d("status" + status);
                        //ToastUtil.show(LoginActivity.this, login_data.getMsg());
                        ToastUtils.showToast(LoginActivity.this, "登录成功");
                        finish();
                    }
                    break;
                case 2:

                    //   ToastUtils.showToast(LoginActivity.this, login_data.getMsg());

                    break;
            }
        }
    };
}

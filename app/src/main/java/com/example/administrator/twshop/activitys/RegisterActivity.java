package com.example.administrator.twshop.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.twshop.Path;
import com.example.administrator.twshop.R;
import com.example.administrator.twshop.base.BaseActivity;
import com.example.administrator.twshop.bean.Register_Data;
import com.example.administrator.twshop.contants.Result;
import com.example.administrator.twshop.utils.ClassPathResource;
import com.example.administrator.twshop.utils.Json;
import com.example.administrator.twshop.utils.Share;
import com.example.administrator.twshop.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

/**
 * Created by Administrator on 2018/1/12 0012  下午 1:33
 * 注册
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout back;
    private TextView title_head;
    private EditText register_tel;
    private EditText register_password;
    private EditText register_code;
    private Button register_getcode;
    private Button register;
    private String register_tels;
    private String register_passwords;
    private String register_codes;
    private TimeCount time;
    private RadioGroup radioGroup;
    private RadioButton caigou;
    private RadioButton gongying;
    private String groupid;
    private EditText register_company;
    private String register_companys;
    private Register_Data register_data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
        initValue();
    }


    private void initView() {
//        back = (LinearLayout) findViewById(R.id.back);
//        title_head = (TextView) findViewById(R.id.title_head);
        register_tel = (EditText) findViewById(R.id.register_tel);
        register_password = (EditText) findViewById(R.id.register_password);
        register_code = (EditText) findViewById(R.id.register_code);
        register_getcode = (Button) findViewById(R.id.register_getcode);
        register = (Button) findViewById(R.id.register);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        caigou = (RadioButton) findViewById(R.id.caigou);
        gongying = (RadioButton) findViewById(R.id.gongying);
        register_company = (EditText) findViewById(R.id.register_company);
    }

    private void initValue() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (caigou.getId() == i) {
                    caigou.setButtonDrawable(getResources().getDrawable(R.mipmap.buttoncheck));
                    gongying.setButtonDrawable(getResources().getDrawable(R.mipmap.button));
                    caigou.setTextColor(getResources().getColor(R.color.black));
                    gongying.setTextColor(getResources().getColor(R.color.white));
                    groupid = "5";
                }
                if (gongying.getId() == i) {
                    gongying.setButtonDrawable(getResources().getDrawable(R.mipmap.buttoncheck));
                    caigou.setButtonDrawable(getResources().getDrawable(R.mipmap.button));
                    gongying.setTextColor(getResources().getColor(R.color.black));
                    caigou.setTextColor(getResources().getColor(R.color.white));
                    groupid = "6";
                }
            }
        });
    }

    private void initEvent() {
        back.setOnClickListener(this);
        title_head.setText(getResources().getString(R.string.register_now));
        register_getcode.setOnClickListener(this);
        register.setOnClickListener(this);
        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.register_getcode:
                register_tels = register_tel.getText().toString().trim();

                if (ClassPathResource.isEmptyOrNull(register_tels)) {
                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.register_number));
                    return;
                }
                if (!ClassPathResource.isMobileNO2(register_tels)) {

                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.toast_login_phone));
                    return;
                }
                time.start();//倒计时开始
                // doGetCode();
                break;
            case R.id.register:
                register_tels = register_tel.getText().toString().trim();
                register_passwords = register_password.getText().toString().trim();
                register_codes = register_code.getText().toString().trim();
                register_companys = register_company.getText().toString().trim();

                if (ClassPathResource.isEmptyOrNull(register_tels)) {
                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.register_number));
                    return;
                }
                if (!ClassPathResource.isMobileNO2(register_tels)) {

                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.toast_login_phone));
                    return;
                }
                if (ClassPathResource.isEmptyOrNull(register_passwords)) {
                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.register_passwords));
                    return;
                }
//                if (ClassPathResource.isEmptyOrNull(register_codes)) {
//                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.register_message));
//                    return;
//
//                }
                if (ClassPathResource.isEmptyOrNull(groupid)) {
                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.register_groupid));
                    return;

                }
                if (ClassPathResource.isEmptyOrNull(register_companys)) {
                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.register_gongsi));
                    return;

                }

                doRegister();
                break;
            default:
                break;
        }


    }

    private void doRegister() {
        String url = Path.REGISTER_PATH;
        OkGo.<String>post(url)
                .tag(this)
                .retryCount(3)
                .cacheKey("register")
                .cacheMode(CacheMode.DEFAULT)
                .params("username", register_tels)
                .params("password", register_passwords)
                .params("groupid", groupid)
                .params("company", register_companys)
                //.params("code", register_codes)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        Share.d("打印数据" + response.body());
                        Result result = Json.parse_message(response.body());
                        if (result.isState() && result != null) {
                            //Gson解析
                            Gson gson = new Gson();
                            register_data = gson.fromJson(response.body(), new TypeToken<Register_Data>() {
                            }.getType());
                            if (register_data.getRet().equals("ok")) {
                                handler.sendEmptyMessage(2);
                            } else {
                                ToastUtils.showToast(RegisterActivity.this, register_data.getMsg());
                            }

                        } else {
                            ToastUtils.showToast(RegisterActivity.this, result.getMessage());
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        handler.sendEmptyMessage(0);
                    }
                });

    }

    /**
     * 获取短信验证码
     *
     * @param
     * @param
     */
    private void doGetCode() {
        String url = Path.GETMSG_PATH;
        OkGo.<String>post(url)
                .tag(this)
                .retryCount(3)
                .cacheKey("register_code")
                .cacheMode(CacheMode.DEFAULT)
                .params("phone", register_tels)
                .params("type", "registered")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
//                        Result result = Json.parse_message(s);
//                        if (result.isState() && result != null) {
//                            //Gson解析
//                            Gson gson = new Gson();
//                            getCode_data = gson.fromJson(s, new TypeToken<GetCode_Data>() {
//                            }.getType());
//                            if (getCode_data.getRet().equals("ok")) {
//                                handler.sendEmptyMessage(1);
//                            } else {
//                                ToastUtils.showToast(RegisterActivity.this, getCode_data.getMsg());
//                            }
//
//
//                        } else {
//                            ToastUtils.showToast(RegisterActivity.this, result.getMessage());
//                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
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
                    ToastUtils.showToast(RegisterActivity.this, getResources().getString(R.string.err_alert));
                    break;
                case 1:
                    //获取短信成功
                    //ToastUtils.showToast(RegisterActivity.this, getCode_data.getMsg());
                    break;
                case 2:
                    //注册成功
                    ToastUtils.showToast(RegisterActivity.this, register_data.getMsg());
                    finish();
                    break;

            }
        }
    };

    /**
     * 倒计时
     *
     * @author Administrator
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            register_getcode.setText("重新获取");
            register_getcode.setEnabled(true);
            register_getcode.setOnClickListener(RegisterActivity.this);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            register_getcode.setEnabled(false);
            register_getcode.setText(millisUntilFinished / 1000 + "秒");
        }


    }
}

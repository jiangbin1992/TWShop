package com.example.administrator.twshop.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.twshop.Path;
import com.example.administrator.twshop.R;
import com.example.administrator.twshop.base.BaseActivity;
import com.example.administrator.twshop.utils.ClassPathResource;
import com.example.administrator.twshop.utils.SPUtils;
import com.example.administrator.twshop.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

/**
 * Created by Administrator on 2018/1/12 0012  下午 2:28
 * 找回登录密码
 */

public class FindloginPasswordActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout back;
    private TextView title_head;
    private EditText newtel;
    private EditText new_password;
    private EditText newcode;
    private Button newgetcode;
    private Button find_password;
    private String tels;
    private String password;
    private String code;
    private TimeCount time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findloginpassword);
        initView();
        initEvent();
    }


    private void initView() {
//        back = (LinearLayout) findViewById(R.id.back);
//        title_head = (TextView) findViewById(R.id.title_head);
        newtel = (EditText) findViewById(R.id.newtel);
        new_password = (EditText) findViewById(R.id.new_password);
        newcode = (EditText) findViewById(R.id.newcode);
        newgetcode = (Button) findViewById(R.id.newgetcode);
        find_password = (Button) findViewById(R.id.find_password);
    }

    private void initEvent() {
        back.setOnClickListener(this);
        title_head.setText(getResources().getString(R.string.forgot_passwordhead));
        newgetcode.setOnClickListener(this);
        find_password.setOnClickListener(this);
        time = new TimeCount(60000, 1000);// 构造CountDownTimer对象
        initValue();
    }

    private void initValue() {
        tels = newtel.getText().toString().trim();
        password = new_password.getText().toString().trim();
        code = newcode.getText().toString().trim();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.newgetcode:
                tels = newtel.getText().toString().trim();
                if (ClassPathResource.isEmptyOrNull(tels)) {
                    ToastUtils.showToast(FindloginPasswordActivity.this, getResources().getString(R.string.register_number));
                    return;
                }
                if (!ClassPathResource.isMobileNO2(tels)) {

                    ToastUtils.showToast(FindloginPasswordActivity.this, getResources().getString(R.string.toast_login_phone));
                    return;
                }
                time.start();//倒计时开始
                doGetCode();
                break;
            case R.id.find_password:
                tels = newtel.getText().toString().trim();
                password = new_password.getText().toString().trim();
                code = newcode.getText().toString().trim();
                if (ClassPathResource.isEmptyOrNull(tels)) {
                    ToastUtils.showToast(FindloginPasswordActivity.this, getResources().getString(R.string.register_number));
                    return;
                }
                if (!ClassPathResource.isMobileNO2(tels)) {

                    ToastUtils.showToast(FindloginPasswordActivity.this, getResources().getString(R.string.toast_login_phone));
                    return;
                }

                if (ClassPathResource.isEmptyOrNull(password)) {
                    ToastUtils.showToast(FindloginPasswordActivity.this, getResources().getString(R.string.toast_login_pwd));
                    return;
                }
                if (ClassPathResource.isEmptyOrNull(code)) {
                    ToastUtils.showToast(FindloginPasswordActivity.this, getResources().getString(R.string.register_message));
                    return;
                }
                doFindPassword();
        }


    }

    private void doGetCode() {
        String url = Path.GETMSG_PATH;
        OkGo.<String>post(url)
                .tag(this)
                .retryCount(3)
                .cacheMode(CacheMode.DEFAULT)
                .cacheKey("findpassword_code")
                .params("phone", tels)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
//                        Result result = Json.parse_message(response);
//                        if (result.isState() && result != null) {
//                            //Gson解析
//                            Gson gson = new Gson();
//                            getCode_data = gson.fromJson(s, new TypeToken<GetCode_Data>() {
//                            }.getType());
//                            if (getCode_data.getRet().equals("ok")) {
//                                handler.sendEmptyMessage(1);
//                            } else {
//                                ToastUtils.showToast(FindloginPasswordActivity.this, getCode_data.getMsg());
//                            }
//
//
//                        } else {
//                            ToastUtils.showToast(FindloginPasswordActivity.this, result.getMessage());
//                        }

                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        handler.sendEmptyMessage(0);
                    }
                })
        ;

    }

    private void doFindPassword() {
        String url = Path.FINDPASSWORD_PATH;
        OkGo.<String>post(url)
                .tag(this)
                .retryCount(3)
                .cacheMode(CacheMode.DEFAULT)
                .cacheKey("findpassword")
                .params("phone", tels)
                .params("code", code)
                .params("password", password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
//                        Result result = Json.parse_message(response);
//                        if (result.isState() && result != null) {
//                            //Gson解析
//                            Gson gson = new Gson();
//                            findPassword_data = gson.fromJson(s, new TypeToken<FindPassword_Data>() {
//                            }.getType());
//                            if (findPassword_data.getRet().equals("ok")) {
//                                handler.sendEmptyMessage(2);
//
//                            } else if (findPassword_data.getRet().equals("ok")) {
//                                handler.sendEmptyMessage(3);
//                            }
//
//
//                        } else {
//                            ToastUtils.showToast(FindloginPasswordActivity.this, result.getMessage());
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
                    ToastUtils.showToast(FindloginPasswordActivity.this, getResources().getString(R.string.err_alert));
                    break;
                case 1:
                    // ToastUtils.showToast(FindloginPasswordActivity.this, getCode_data.getMsg());
                    break;
                case 2:
                    SPUtils.remove(FindloginPasswordActivity.this, "token");
                    // ToastUtils.showToast(FindloginPasswordActivity.this, findPassword_data.getMsg());
                    finish();
                    break;
                case 3:
                    // ToastUtils.showToast(FindloginPasswordActivity.this, findPassword_data.getMsg());
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
            newgetcode.setText("重新获取");
            newgetcode.setEnabled(true);
            newgetcode.setOnClickListener(FindloginPasswordActivity.this);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            newgetcode.setEnabled(false);
            newgetcode.setText(millisUntilFinished / 1000 + "秒");
        }


    }
}

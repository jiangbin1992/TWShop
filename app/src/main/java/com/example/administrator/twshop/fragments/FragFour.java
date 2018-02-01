package com.example.administrator.twshop.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.twshop.R;
import com.example.administrator.twshop.activitys.DecorateActivity;
import com.example.administrator.twshop.activitys.GoodsDetailActivity;
import com.example.administrator.twshop.activitys.LoginActivity;
import com.example.administrator.twshop.activitys.PersonalMessageActivity;
import com.example.administrator.twshop.activitys.SettingActivity;
import com.example.administrator.twshop.utils.SPUtils;
import com.example.administrator.twshop.utils.ToastUtils;

/**
 * Created by Administrator on 2018/1/10 0010.
 * 我的页面
 */

public class FragFour extends Fragment implements View.OnClickListener {
    private RelativeLayout mine_iv_desgin;    //设置按钮
    private RelativeLayout mine_iv_message;    //消息按钮
    private RelativeLayout mine_iv_go;
    private ImageView mine_iv_head;    //头像
    private TextView mine_tv_name;    //用户名
    private ImageView mine_iv_code;    //二维码
    private ImageView mine_iv_grade;    //会员级别
    private LinearLayout mine_iv_setting;
    private LinearLayout mine_iv_my_older;
    private LinearLayout mine_iv_address;
    private LinearLayout mine_iv_collection;
    private LinearLayout mine_iv_call;
    private LinearLayout mine_iv_zhaungxiu;
    private Intent intent;
    private String number;
    private LinearLayout mine_ll_login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_four, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }


    private void initView() {
        mine_iv_desgin = (RelativeLayout) getActivity().findViewById(R.id.mine_iv_desgin);
        mine_iv_message = (RelativeLayout) getActivity().findViewById(R.id.mine_iv_message);
        mine_iv_head = (ImageView) getActivity().findViewById(R.id.mine_iv_head);
        mine_tv_name = (TextView) getActivity().findViewById(R.id.mine_tv_name);
        mine_iv_code = (ImageView) getActivity().findViewById(R.id.mine_iv_code);
        mine_iv_grade = (ImageView) getActivity().findViewById(R.id.mine_iv_grade);
        mine_iv_go = (RelativeLayout) getActivity().findViewById(R.id.mine_iv_go);

        mine_iv_setting = (LinearLayout) getActivity().findViewById(R.id.mine_iv_setting);
        mine_iv_my_older = (LinearLayout) getActivity().findViewById(R.id.mine_iv_my_older);
        mine_iv_address = (LinearLayout) getActivity().findViewById(R.id.mine_iv_address);
        mine_iv_collection = (LinearLayout) getActivity().findViewById(R.id.mine_iv_collection);
        mine_iv_call = (LinearLayout) getActivity().findViewById(R.id.mine_iv_call);
        mine_iv_zhaungxiu = (LinearLayout) getActivity().findViewById(R.id.mine_iv_zhaungxiu);
        mine_ll_login = (LinearLayout) getActivity().findViewById(R.id.mine_ll_login);

        mine_iv_desgin.setOnClickListener(this);
        mine_iv_setting.setOnClickListener(this);
        mine_iv_my_older.setOnClickListener(this);
        mine_iv_address.setOnClickListener(this);
        mine_iv_collection.setOnClickListener(this);
        mine_iv_call.setOnClickListener(this);
        mine_iv_zhaungxiu.setOnClickListener(this);
        mine_ll_login.setOnClickListener(this);
        mine_iv_head.setOnClickListener(this);
    }

    private void initData() {
        String token = (String) SPUtils.get(getActivity(), "token", "");
        if (token.length() > 0 && token != null) {
            mine_tv_name.setText("欢迎您");
        } else {
            mine_tv_name.setText("点击登录");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_iv_desgin:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);

                break;
            case R.id.mine_iv_setting:
                intent = new Intent(getActivity(), PersonalMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_iv_address:
                intent = new Intent(getActivity(), GoodsDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_iv_collection:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_iv_call:
                //拨打电话
                number = "0371-60300778";
                onCall();
                break;
            case R.id.mine_iv_zhaungxiu:
                intent = new Intent(getActivity(), DecorateActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_ll_login:
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_iv_head:

                break;
            default:
                break;
        }

    }

//    private void showNormalDialog() {
//        /* @setIcon 设置对话框图标
//         * @setTitle 设置对话框标题
//         * @setMessage 设置对话框消息提示
//         * setXXX方法返回Dialog对象，因此可以链式设置属性
//         */
//        final AlertDialog.Builder normalDialog =
//                new AlertDialog.Builder(getActivity());
//
//        normalDialog.setTitle("上传头像提示");
//        normalDialog.setMessage("确定更改头像");
//        normalDialog.setPositiveButton("确定",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        intent = new Intent(getActivity(), PersonalMessageActivity.class);
//                        intent.putExtra("from", "fragleft");
//                        startActivity(intent);
//                    }
//                });
//        normalDialog.setNegativeButton("关闭",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //...To-do
//                    }
//                });
//        // 显示
//        normalDialog.show();
//    }
    //处理android6.0拨打电话新特新的权限
    public void onCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    getActivity(),
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

                    ToastUtils.showToast(getActivity(), "请检查拨打电话的权限");
                }
                break;

            default:
                break;
        }
    }
}

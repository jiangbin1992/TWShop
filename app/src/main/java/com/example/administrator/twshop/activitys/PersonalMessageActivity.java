package com.example.administrator.twshop.activitys;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.twshop.MainActivity;
import com.example.administrator.twshop.R;
import com.example.administrator.twshop.base.BaseActivity;
import com.example.administrator.twshop.bean.PersonalMessage_Data;
import com.example.administrator.twshop.utils.SPUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.squareup.picasso.MemoryPolicy.NO_CACHE;
import static com.squareup.picasso.MemoryPolicy.NO_STORE;

/**
 * Created by jiangbin on 2018/1/13.
 * 个人信息中心
 */

public class PersonalMessageActivity extends TakePhotoActivity implements View.OnClickListener {

    private RelativeLayout back;
    private TextView personal_name;
    private TextView personal_tel;
    private RelativeLayout persoanl_changepassword;
    private RelativeLayout personal_bankcard;
    private CircleImageView mine_iv_head;
    private Intent intent;
    private PersonalMessage_Data personalMessage_data;
    private LinearLayout choose_image;
    private TextView choose_camera;
    private TextView choose_libaray;
    private TextView choose_cancel;


    private Context mContext;
    private TextView title;
    private ImageView image;

    //TakePhoto
    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalmessage);
        mContext = PersonalMessageActivity.this;
        initView();
        initEvent();
        //申请相关权限
        initPermission();
        initData();  //设置压缩、裁剪参数


    }


    private void initView() {
        back = (RelativeLayout) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        personal_name = (TextView) findViewById(R.id.personal_name);
        personal_tel = (TextView) findViewById(R.id.personal_tel);
        persoanl_changepassword = (RelativeLayout) findViewById(R.id.persoanl_changepassword);
        personal_bankcard = (RelativeLayout) findViewById(R.id.personal_bankcard);
        mine_iv_head = (CircleImageView) findViewById(R.id.mine_iv_head);

        choose_image = (LinearLayout) findViewById(R.id.choose_image);
        choose_camera = (TextView) findViewById(R.id.choose_camera);
        choose_libaray = (TextView) findViewById(R.id.choose_libaray);
        choose_cancel = (TextView) findViewById(R.id.choose_cancel);


        title.setText(getResources().getText(R.string.mine_tv_personal));


    }

    @Override
    protected void onResume() {
        super.onResume();
        //进入页面网络请求
        //getPersonalMessage();


    }

    private void initEvent() {
        back.setOnClickListener(this);
        persoanl_changepassword.setOnClickListener(this);
        personal_bankcard.setOnClickListener(this);
        mine_iv_head.setOnClickListener(this);
        choose_camera.setOnClickListener(this);
        choose_libaray.setOnClickListener(this);
        choose_cancel.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.persoanl_changepassword:
                intent = new Intent(PersonalMessageActivity.this, FindloginPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_iv_head:
                bottomIn();
                break;
            case R.id.choose_cancel:
                bottomOut();
                break;
            case R.id.choose_camera:

                imageUri = getImageCropUri();
                //拍照并裁剪
                takePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
                //仅仅拍照不裁剪
                //takePhoto.onPickFromCapture(imageUri);

                bottomOut();

                break;

            case R.id.choose_libaray:
                imageUri = getImageCropUri();
                //从相册中选取图片并裁剪
                takePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
                //从相册中选取不裁剪
                //takePhoto.onPickFromGallery();
                bottomOut();
                break;
            default:
                break;
        }


    }

    private void bottomOut() {
        //收缩底部弹窗
        choose_image.setVisibility(View.GONE);
        Animation out = AnimationUtils.loadAnimation(PersonalMessageActivity.this, R.anim.scale_out_2);
        choose_image.startAnimation(out);
    }

    private void bottomIn() {
        //弹出底部弹窗
        choose_image.setVisibility(View.VISIBLE);
        Animation in = AnimationUtils.loadAnimation(PersonalMessageActivity.this, R.anim.scale_in_2);
        choose_image.startAnimation(in);
    }


    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        String iconPath = result.getImage().getOriginalPath();
        //Toast显示图片路径
        Toast.makeText(this, "imagePath:" + iconPath, Toast.LENGTH_SHORT).show();
        //Google Glide库 用于加载图片资源
        Glide.with(this).load(iconPath).into(mine_iv_head);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Toast.makeText(PersonalMessageActivity.this, "Error:" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    private void initPermission() {
        // 申请权限。
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .send();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 只需要调用这一句，其它的交给AndPermission吧，最后一个参数是PermissionListener。
        AndPermission.onRequestPermissionsResult(requestCode, permissions, grantResults, listener);
    }

    //权限申请回调接口
    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            if (requestCode == 100) {
                // TODO 相应代码。
                //do nothing
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。

            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(PersonalMessageActivity.this, deniedPermissions)) {

                // 用自定义的提示语
                AndPermission.defaultSettingDialog(PersonalMessageActivity.this, 103)
                        .setTitle("权限申请失败")
                        .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
                        .setPositiveButton("好，去设置")
                        .show();
            }
        }
    };

    private void initData() {
        ////获取TakePhoto实例
        takePhoto = getTakePhoto();
        //设置裁剪参数
        cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
        //设置压缩参数
        compressConfig = new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig, true);  //设置为需要压缩
    }

    //获得照片的输出保存Uri
    private Uri getImageCropUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        return Uri.fromFile(file);
    }
}

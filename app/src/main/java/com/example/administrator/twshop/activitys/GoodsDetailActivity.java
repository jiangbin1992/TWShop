package com.example.administrator.twshop.activitys;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.twshop.R;
import com.example.administrator.twshop.base.BaseActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import static com.umeng.socialize.bean.SHARE_MEDIA.QQ;
import static com.umeng.socialize.bean.SHARE_MEDIA.QZONE;

/**
 * Created by jiangbin on 2018/1/17.
 */

public class GoodsDetailActivity extends BaseActivity implements View.OnClickListener {

    private TextView detail_share;
    private ShareAction mShareAction;
    private UMWeb web;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsdetail);
        initView();
        initValue();
        initShare();
    }


    private void initView() {
        detail_share = (TextView) findViewById(R.id.detail_share);
    }

    private void initValue() {
        detail_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_share:
                ShareBoardConfig config = new ShareBoardConfig();
                config.setShareboardBackgroundColor(Color.WHITE);//分享面板背景颜色
                mShareAction.open(config);
                break;
            default:
                break;
        }

    }

    private void initShare() {
        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(GoodsDetailActivity.this).setDisplayList(
                QQ, QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE
        )
                // .addButton("umeng_sharebutton_copy", "umeng_sharebutton_copy", "umeng_socialize_copy", "umeng_socialize_copy")
                //  .addButton("umeng_sharebutton_copyurl", "umeng_sharebutton_copyurl", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        switch (share_media) {

                            case QQ:
                                web = new UMWeb("http://www.doqu.com/");
                                web.setTitle("商品");
                                web.setDescription("很棒的Android开发工程师");
                                //web.setThumb(new UMImage(GoodsDetailActivity.this, String.valueOf(getResources().getDrawable(R.mipmap.shopcar))));
                                new ShareAction(GoodsDetailActivity.this).withMedia(web)
                                        .setPlatform(share_media)
                                        .setCallback(umShareListener)
                                        .share();
                                break;
                            case QZONE:
                                web = new UMWeb("http://www.doqu.com/");
                                web.setTitle("商品");
                                web.setDescription("很棒的Android开发工程师");
                                //web.setThumb(new UMImage(GoodsDetailActivity.this, String.valueOf(getResources().getDrawable(R.mipmap.shopcar))));
                                new ShareAction(GoodsDetailActivity.this).withMedia(web)
                                        .setPlatform(share_media)
                                        .setCallback(umShareListener)
                                        .share();
                                break;
                            case WEIXIN:
                                web = new UMWeb("http://www.doqu.com/");
                                web.setTitle("商品");
                                web.setDescription("很棒的Android开发工程师");
                                //web.setThumb(new UMImage(GoodsDetailActivity.this, String.valueOf(getResources().getDrawable(R.mipmap.shopcar))));
                                new ShareAction(GoodsDetailActivity.this).withMedia(web)
                                        .setPlatform(share_media)
                                        .setCallback(umShareListener)
                                        .share();
                                break;
                            case WEIXIN_CIRCLE:
                                web = new UMWeb("http://www.doqu.com/");
                                web.setTitle("商品");
                                web.setDescription("很棒的Android开发工程师");
                                //web.setThumb(new UMImage(GoodsDetailActivity.this, String.valueOf(getResources().getDrawable(R.mipmap.shopcar))));
                                new ShareAction(GoodsDetailActivity.this).withMedia(web)
                                        .setPlatform(share_media)
                                        .setCallback(umShareListener)
                                        .share();
                                break;
                        }

                    }

                });
    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(GoodsDetailActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(GoodsDetailActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(GoodsDetailActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

}

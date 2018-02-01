package com.example.administrator.twshop;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.twshop.base.AppManager;
import com.example.administrator.twshop.fragments.FragFirst;
import com.example.administrator.twshop.fragments.FragFour;
import com.example.administrator.twshop.fragments.FragSecond;
import com.example.administrator.twshop.fragments.FragSeconds;
import com.example.administrator.twshop.fragments.FragThird;

import java.util.Calendar;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    /**
     * 管理fragment的类
     */
    private FragmentManager fm;
    /**
     * 首页
     */
    private LinearLayout layout1;
    private ImageView img1;
    private TextView text1;
    /**
     * 分类
     */
    private LinearLayout layout2;
    private ImageView img2;
    private TextView text2;
    /**
     * 购物车
     */
    private LinearLayout layout3;
    private ImageView img3;
    private TextView text3;

    /**
     * 我的
     */
    private LinearLayout layout4;
    private ImageView img4;
    private TextView text4;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        //沉浸式导航栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = MainActivity.this.getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(getResources().getColor(R.color.backaround_gray));

            ViewGroup mContentView = (ViewGroup) MainActivity.this.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
                ViewCompat.setFitsSystemWindows(mChildView, true);
            }

        }
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        initView();
    }

    private void initView() {
        layout1 = (LinearLayout) findViewById(R.id.tab_1);
        layout2 = (LinearLayout) findViewById(R.id.tab_2);
        layout3 = (LinearLayout) findViewById(R.id.tab_3);
        layout4 = (LinearLayout) findViewById(R.id.tab_4);

        img1 = (ImageView) findViewById(R.id.rb_img_1);
        text1 = (TextView) findViewById(R.id.rb_tv_1);
        img2 = (ImageView) findViewById(R.id.rb_img_2);
        text2 = (TextView) findViewById(R.id.rb_tv_2);
        img3 = (ImageView) findViewById(R.id.rb_img_3);
        text3 = (TextView) findViewById(R.id.rb_tv_3);
        img4 = (ImageView) findViewById(R.id.rb_img_4);
        text4 = (TextView) findViewById(R.id.rb_tv_4);


        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);

        setChoiceItem(0);
    }

    FragFirst fg1;
    FragSeconds fg2;
    FragThird fg3;
    FragFour fg4;


    private void setChoiceItem(int index) {
        FragmentTransaction transaction = fm.beginTransaction();

        switch (index) {
            case 0: // 首页
                clearChoice();
                hideFragments(transaction);
                img1.setImageResource(R.mipmap.souye);
                text1.setTextColor(getResources().getColor(R.color.text_color));
                if (fg1 == null) {
                    fg1 = new FragFirst();
                    transaction.add(R.id.main_fragment, fg1);
                } else {
                    transaction.show(fg1);
                }

                break;
            case 1: // 分类
                clearChoice();
                hideFragments(transaction);
                img2.setImageResource(R.mipmap.classification);
                text2.setTextColor(getResources().getColor(R.color.text_color));
                if (fg2 == null) {
                    fg2 = new FragSeconds();
                    transaction.add(R.id.main_fragment, fg2);
                } else {
                    transaction.show(fg2);
                }

                break;
            case 2: //购物车
                clearChoice();
                hideFragments(transaction);
                img3.setImageResource(R.mipmap.shop);
                text3.setTextColor(getResources().getColor(R.color.text_color));
                if (fg3 == null) {
                    fg3 = new FragThird();
                    transaction.add(R.id.main_fragment, fg3);
                } else {
                    transaction.show(fg3);
                }

                break;
            case 3: //我的
                clearChoice();
                hideFragments(transaction);
                img4.setImageResource(R.mipmap.wode);
                text4.setTextColor(getResources().getColor(R.color.text_color));
                if (fg4 == null) {
                    fg4 = new FragFour();
                    transaction.add(R.id.main_fragment, fg4);
                } else {
                    transaction.show(fg4);
                }

                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏所有的Fragment,避免fragment混乱
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (fg1 != null) {
            transaction.hide(fg1);
        }
        if (fg2 != null) {
            transaction.hide(fg2);
        }
        if (fg3 != null) {
            transaction.hide(fg3);
        }
        if (fg4 != null) {
            transaction.hide(fg4);
        }
    }

    // 定义一个重置所有选项的方法
    public void clearChoice() {
        img1.setImageResource(R.mipmap.souye1);
        text1.setTextColor(getResources().getColor(R.color.text_bg));
        img2.setImageResource(R.mipmap.classification1);
        text2.setTextColor(getResources().getColor(R.color.text_bg));
        img3.setImageResource(R.mipmap.shop1);
        text3.setTextColor(getResources().getColor(R.color.text_bg));
        img4.setImageResource(R.mipmap.wode1);
        text4.setTextColor(getResources().getColor(R.color.text_bg));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_1:
                setChoiceItem(0);
                break;
            case R.id.tab_2:
                setChoiceItem(1);
                break;
            case R.id.tab_3:
                setChoiceItem(2);
                break;
            case R.id.tab_4:
                setChoiceItem(3);
                break;
            default:
                break;
        }
    }

    private long lasttime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long current = Calendar.getInstance().getTimeInMillis();

        if ((current - lasttime) / 1000 >= 2) {
            Toast mToast = Toast.makeText(MainActivity.this, "再按一次返回键退出", Toast.LENGTH_SHORT);
            mToast.show();
            lasttime = current;
            return true;
        } else {
            AppManager.getAppManager().AppExit();
        }
        return super.onKeyDown(keyCode, event);
    }
}

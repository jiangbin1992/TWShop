package com.example.administrator.twshop.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.administrator.twshop.base.BaseFragment;
import com.example.administrator.twshop.bean.Classify_Title;
import com.example.administrator.twshop.fragments.PageFragment;
import com.example.administrator.twshop.utils.ToastUtils;

import java.util.List;


/**
 * Created by Administrator on 2018/1/29 0011.
 */

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {


    private List<Classify_Title> list;
    private List<BaseFragment> fragments;
    private final FragmentManager mFm;

    public MyFragmentPagerAdapter(FragmentManager fm,  List<BaseFragment> fragments, List<Classify_Title> list) {
        super(fm);
        mFm = fm;

        this.list = list;
        this.fragments = fragments;
    }


    @Override
    public BaseFragment getItem(int position) {
        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list == null ? "" : list.get(position).getTitle();
    }

    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;

    }

}

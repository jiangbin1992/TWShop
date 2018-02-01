package com.example.administrator.twshop.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.twshop.R;
import com.example.administrator.twshop.adapter.MyFragmentPagerAdapter;
import com.example.administrator.twshop.base.BaseFragment;
import com.example.administrator.twshop.bean.Classify_Title;
import com.example.administrator.twshop.utils.Share;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class FragSeconds extends Fragment implements View.OnClickListener {
    private MyFragmentPagerAdapter adapter;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_seconds, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private List<BaseFragment> mFragments;
    private String[] titles = new String[]{"推荐", "热点", "娱乐", "科技", "体育", "军事", "图片"};

    private void initView() {
        List<Classify_Title> list = new ArrayList<Classify_Title>();
        list.add(new Classify_Title("0", "推荐"));
        list.add(new Classify_Title("1", "热点"));
        list.add(new Classify_Title("2", "娱乐"));
        list.add(new Classify_Title("3", "科技"));
        list.add(new Classify_Title("4", "体育"));
        list.add(new Classify_Title("5", "军事"));
        list.add(new Classify_Title("6", "图片"));


        mFragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            PageFragment fragment = PageFragment.newInstance(list.get(i).getTitle());
            mFragments.add(fragment);
        }
        Share.d("size"+mFragments.size());
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        adapter = new MyFragmentPagerAdapter(getChildFragmentManager(),  mFragments, list);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(list.size());



        //TabLayout
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //显示当前那个标签页
        //viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);//给Tabs设置适配器
    }

    private void initData() {
    }

    @Override
    public void onClick(View view) {

    }
}

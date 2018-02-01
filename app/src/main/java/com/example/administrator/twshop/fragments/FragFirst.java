package com.example.administrator.twshop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.twshop.R;
import com.example.administrator.twshop.activitys.SettingActivity;
import com.example.administrator.twshop.activitys.SearchActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class FragFirst extends Fragment implements View.OnClickListener {

    private LinearLayout home_ll_search;
    private RelativeLayout home_rl_xiaoxi;
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initRefresh();
    }

    private void initRefresh() {
        final RefreshLayout refreshLayout = (RefreshLayout) getActivity().findViewById(R.id.refresh);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        refreshLayout.finishRefresh();

                    }
                }, 2000);

            }
        });

    }


    private void initView() {
        home_ll_search = (LinearLayout) getActivity().findViewById(R.id.home_ll_search);
        home_rl_xiaoxi = (RelativeLayout) getActivity().findViewById(R.id.home_rl_xiaoxi);
        home_ll_search.setOnClickListener(this);
        home_rl_xiaoxi.setOnClickListener(this);
    }

    private void initData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_ll_search:
                intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.home_rl_xiaoxi:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }

    }
}

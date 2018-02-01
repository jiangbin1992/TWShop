package com.example.administrator.twshop.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.twshop.R;
import com.example.administrator.twshop.base.BaseFragment;
import com.example.administrator.twshop.utils.ConstanceValue;
import com.example.administrator.twshop.utils.ToastUtils;

/**
 * Created by Administrator on 2018/1/29 0029  下午 2:12
 */

public class PageFragment extends BaseFragment {

    private TextView tv;


    private View view;
    private String mTitleCode;
    private SwipeRefreshLayout swipeRefreshLayout;


    public static PageFragment newInstance(String title) {
        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstanceValue.DATA, title);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    protected View loadViewLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_page, container, false);

        return view;
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();


        swipeRefreshLayout.setRefreshing(true);

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);//休眠3秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**
                 * 要执行的操作
                 */
                handler.sendEmptyMessage(0);
            }
        }.start();

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:

                    swipeRefreshLayout.setRefreshing(false);
                    mTitleCode = getArguments().getString(ConstanceValue.DATA);
                    ToastUtils.showToast(getActivity(), mTitleCode);
                    tv.setText(mTitleCode);
                    break;
                case 1:
                    swipeRefreshLayout.setRefreshing(false);
                    tv.setText("2");
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void bindViews(View view) {

        tv = (TextView) view.findViewById(R.id.tv);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);


    }


    @Override
    protected void processLogic() {
        mTitleCode = getArguments().getString(ConstanceValue.DATA);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(3000);//休眠3秒

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        /**
                         * 要执行的操作
                         */
                        handler.sendEmptyMessage(1);

                    }
                }.start();

            }
        });

    }

    @Override
    protected void setListener() {

    }


}

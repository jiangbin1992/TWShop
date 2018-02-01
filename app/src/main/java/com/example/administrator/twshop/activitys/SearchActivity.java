package com.example.administrator.twshop.activitys;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.twshop.R;
import com.example.administrator.twshop.adapter.SearchAdapter;
import com.example.administrator.twshop.base.BaseActivity;
import com.example.administrator.twshop.bean.DataBean;
import com.example.administrator.twshop.db.RecordSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/11 0011.
 * 搜索
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_search;
    private LinearLayout tv_cancel;
    private ListView listView;
    private TextView tv_clear;
    private LinearLayout search_ll;
    private String form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        // 第一次进入查询所有的历史记录
        queryData("");
    }

    private void initView() {
        et_search = (EditText) findViewById(R.id.search_et);
        tv_cancel = (LinearLayout) findViewById(R.id.search_cancel);
        tv_clear = (TextView) findViewById(R.id.tv_clear);
        listView = (ListView) findViewById(R.id.search_lv);
        search_ll = (LinearLayout) findViewById(R.id.search_ll);

        tv_cancel.setOnClickListener(this);
        tv_clear.setOnClickListener(this);


        Intent intent = getIntent();
        form = intent.getStringExtra("form");


        et_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent
                        .ACTION_DOWN) {// 修改回车键功能
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    getCurrentFocus().getWindowToken(), InputMethodManager
                                            .HIDE_NOT_ALWAYS);
                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    boolean hasData = hasData(et_search.getText().toString().trim());
                    if (!hasData) {
                        insertData(et_search.getText().toString().trim());
                        queryData("");
                    }
                    // TODO 根据输入的内容模糊查询商品，并跳转到另一个界面，由你自己去实现
//                    Intent intent = new Intent(SearchActivity.this, ShowActivity.class);
//                    intent.putExtra("title", et_search.getText().toString());
//                    ToastUtils.showToast(SearchActivity.this,"进入国内精选搜索");
//                    startActivity(intent);

                    Intent intent = new Intent(SearchActivity.this, ShowActivity.class);
                    intent.putExtra("title", et_search.getText().toString());
                    startActivity(intent);

                }
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                et_search.setText(list.get(position).getText());

                Intent intent = new Intent(SearchActivity.this, ShowActivity.class);
                intent.putExtra("title", et_search.getText().toString());
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_cancel:
                this.finish();
                break;
            case R.id.tv_clear:
                deleteData();
                queryData("");
                break;
        }
    }

    private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);
    private SQLiteDatabase db;

    /**
     * 插入数据
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /**
     * 模糊查询数据
     */
    List<DataBean> list = new ArrayList<>();
    SearchAdapter adapter;

    private void queryData(String tempName) {
        list = new ArrayList<>();
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by" +
                        " id desc ", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String text = cursor.getString(1);
                list.add(new DataBean(id, text));
            }
        } else {
            search_ll.setVisibility(View.GONE);
        }
        Log.i("Tag", "SearchList" + list.size());
        if (list != null && list.size() > 0) {
            adapter = new SearchAdapter(list, SearchActivity.this, helper, db);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            search_ll.setVisibility(View.VISIBLE);
        } else {
            search_ll.setVisibility(View.GONE);
        }
    }

    /**
     * 检查数据库中是否已经有该条记录
     */
    private boolean hasData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /**
     * 清空数据
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


}


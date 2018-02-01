package com.example.administrator.twshop.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.twshop.R;
import com.example.administrator.twshop.bean.DataBean;
import com.example.administrator.twshop.db.RecordSQLiteOpenHelper;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11 0011.
 */
public class SearchAdapter extends BaseAdapter {
    List<DataBean> list;
    Context mContext;
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;

    public SearchAdapter(List<DataBean> list, Context mContext, RecordSQLiteOpenHelper helper,
                         SQLiteDatabase db) {
        this.list = list;
        this.mContext = mContext;
        this.helper = helper;
        this.db = db;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_search, null);
            holder.text = (TextView) convertView.findViewById(R.id.item_search_tv);
            holder.img = (ImageView) convertView.findViewById(R.id.item_search_close);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(list.get(position).getText());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "删除", Toast.LENGTH_SHORT).show();
                deleteRecord(list.get(position).getId());
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView text;
        ImageView img;
    }

    /**
     * 删除数据
     */

    private void deleteRecord(int id) {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records where id=?", new Object[]{id});
        db.close();
    }
}

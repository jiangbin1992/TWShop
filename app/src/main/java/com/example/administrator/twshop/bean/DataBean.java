package com.example.administrator.twshop.bean;

/**
 * Created by Administrator on 2018/1/11 0011.
 */
public class DataBean {
    private int id;
    private String text;

    public DataBean(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

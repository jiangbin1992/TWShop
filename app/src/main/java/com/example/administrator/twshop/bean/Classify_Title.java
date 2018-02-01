package com.example.administrator.twshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/30 0030  上午 10:13
 */

public class Classify_Title implements Serializable {
    private String id;
    private String title;

    public Classify_Title(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Classify_Title{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

package com.example.administrator.twshop.contants;

/**
 * 返回消息类
 * Created by ${jiangbin} on 2016/8/15.
 */
public class Result {

    private boolean state;
    private String message;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

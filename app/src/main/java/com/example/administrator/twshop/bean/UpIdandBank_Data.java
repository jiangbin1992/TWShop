package com.example.administrator.twshop.bean;

/**
 * Created by bj on 2017/3/24.
 */

public class UpIdandBank_Data {

    /**
     * ret : ok
     * msg : ok
     * data : Upload/user_90/idcard_reverse.jpg
     * totalpage : 1
     */

    private String ret;
    private String msg;
    private String data;
    private int totalpage;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }
}

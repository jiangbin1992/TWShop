package com.example.administrator.twshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/22.
 */

public class Login_Data implements Serializable {

    /**
     * ret : ok
     * msg : 登录成功
     * data : {"user_token":"97e54de65a0d0f330dd1accd24d9dce2","company":"中国公司"}
     * code : 200
     */

    private String ret;
    private String msg;
    private DataBean data;
    private int code;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * user_token : 97e54de65a0d0f330dd1accd24d9dce2
         * company : 中国公司
         */

        private String user_token;
        private String company;

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }
    }
}

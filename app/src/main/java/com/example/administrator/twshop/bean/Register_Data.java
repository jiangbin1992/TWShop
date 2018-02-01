package com.example.administrator.twshop.bean;

/**
 * Created by bj on 2017/3/3.
 */

public class Register_Data {

    /**
     * ret : err
     * msg : 用户名已注册
     * data : {"regid":"5","username":"18737192723","password":"123456","email":"","mobile":"18737192723","truename":"ces","company":"ces","passport":"18737192723","cpassword":"123456"}
     * code : -402
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
         * regid : 5
         * username : 18737192723
         * password : 123456
         * email :
         * mobile : 18737192723
         * truename : ces
         * company : ces
         * passport : 18737192723
         * cpassword : 123456
         */

        private String regid;
        private String username;
        private String password;
        private String mobile;
        private String truename;
        private String company;
        private String passport;
        private String cpassword;

        public String getRegid() {
            return regid;
        }

        public void setRegid(String regid) {
            this.regid = regid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPassport() {
            return passport;
        }

        public void setPassport(String passport) {
            this.passport = passport;
        }

        public String getCpassword() {
            return cpassword;
        }

        public void setCpassword(String cpassword) {
            this.cpassword = cpassword;
        }
    }
}

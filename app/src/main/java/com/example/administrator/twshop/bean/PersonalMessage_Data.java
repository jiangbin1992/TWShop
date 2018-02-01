package com.example.administrator.twshop.bean;

/**
 * Created by bj on 2017/3/13.
 */

public class PersonalMessage_Data {

    /**
     * data : {"pid":"0","portrait":"","token":"94BBDFB7B686825163A24AE954063F0F31","type":"2","username":"18737192723"}
     * msg : ok
     * ret : ok
     * totalpage : 1
     */

    private DataBean data;
    private String msg;
    private String ret;
    private int totalpage;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public static class DataBean {
        /**
         * pid : 0
         * portrait :
         * token : 94BBDFB7B686825163A24AE954063F0F31
         * type : 2
         * username : 18737192723
         */

        private String pid;
        private String portrait;
        private String token;
        private String type;
        private String username;
        private String real_name;

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}

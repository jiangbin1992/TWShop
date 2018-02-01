package com.example.administrator.twshop;

/**
 * 接口地址
 * Created by Administrator on 2017/3/1.
 */

public class Path {

    /**
     * 域名
     */
    //测试服
    //public static final String NAME = "http://xzg.4gxt.cn";

    //正式服
     public static final String NAME = "http://www.sugepay.com";

    /**
     * 接口
     */

    //获取验证码的接口
    public static final String GETMSG_PATH = NAME + "/index.php/Api/Phonecode/getphonecode";

    //找回密码的接口
    public static final String FINDPASSWORD_PATH = NAME + "/index.php/Api/Findpassword/resetpassword";


    //更换密码的接口
    public static final String CHANGEPASSWORD_PATH = NAME + "/index.php/Api/Findpassword/changepassword";


    //注册账号的接口
    public static final String REGISTER_PATH = NAME + "/index.php/Api/Registered/registered";

    //登录的接口
    public static final String LOGIN_PATH = NAME + "/index.php/Api/User/login";

    //首页轮播图的接口
    public static final String HOME_CIRCLE_PATH = NAME + "/index.php/Api/Index/cate_ad";

    //首页奖励的接口
    public static final String HOME_Reward_PATH = NAME + "/index.php/Api/Index/reward";

    //收款消息列表
    public static final String Message_Receiving_PATH = NAME + "/index.php/Api/Message/pay";

    //收款消息详情
    public static final String Message_Receiving__Detail_PATH = NAME + "/index.php/Api/Message/pay_info";

    //收款消息详情的头像
    public static final String Message_Receiving_photo = NAME + "/Upload/avatar/user1.jpg";

    //系统消息的接口
    public static final String Message_System_PATH = NAME + "/index.php/Api/Message/system";

    //系统消息详情的接口
    public static final String Message_System_Detail_PATH = NAME + "/index.php/Api/Message/sy_info";

    //首页底部的gridview
    public static final String HOME_GRIDVIEW_PATH = NAME + "/index.php/Api/Index/index_down";

    //意见反馈
    public static final String FEEDBACK_PATH = NAME + "/index.php/Api/Feedback/saveeedback";

    //收款页面的接口
    public static final String COLLECTION_PATH = NAME + "/index.php/Api/Pay/page";

    //扫码加急收款页面的接口
    public static final String URGENT_PATH = NAME + "/index.php/Api/pay/barcode?";

    //银行卡加急收款页面的接口
    public static final String CARD_URGENT_PATH = NAME + "/index.php/Api/pay/bank";

    //退出登录
    public static final String LOGINOUT_PATH = NAME + "/index.php/Api/User/logout";


    //收款记录的接口
    public static final String GATHERING_PATH = NAME + "/index.php/Api/User/trans";

    //钱包的接口
    public static final String WALLET_PATH = NAME + "/index.php/Api/money/index";

    //添加银行卡的接口///////////////里边还有接口没给 暂时代替
    public static final String ADDCARD_PATH = NAME + "/api/index.php?n=userset&f=addcard";

    //钱包分润提现的接口
    public static final String WITHDRAWL_PATH = NAME + "/index.php/Api/money/deposit";

    //个人的接口
    public static final String PERSONAL_PATH = NAME + "/index.php/Api/User/userinfo";

    //设置默认银行卡的接口
    public static final String CHOOSE_CARD_PATH = NAME + "/index.php/Api/User/userinfo";

    //上传银行卡的接口
    public static final String UP_BANK_PATH = NAME + "/index.php/Api/User/image";

    //交易记录收款记录的接口
    public static final String PAYMENT_RECORDS_PATH = NAME + "/index.php/Api/User/trans";


    //交易提现收款记录的接口
    public static final String WITHDRAWAL_RECORDS_PATH = NAME + "/index.php/Api/User/deposit";

    //上传头像的接口
    public static final String UP_HEAD_PATH = NAME + "/api/index.php?n=wendy&f=update_portrait";

    //银行卡列表的接口
    public static final String BANK_LIST_PATH = NAME + "/index.php/Api/index/banklist";

    //实名认证身份证正面的接口
    public static final String UP_ID_PATH = NAME + "/index.php/Api/User/idcard";

    //实名认证银行卡正面照的接口
    public static final String UP_BANKONE_PATH = NAME + "/index.php/Api/User/bank";

    //实名认证总提交的接口
    public static final String REAL_NAME_PATH = NAME + "/index.php/Api/User/confirm";

    //分享挣钱的接口
    public static final String SHARE_MONEY_PATH = NAME + "/index.php/Api/Share/index";

    //我的人脉的接口
    public static final String MY_CONTACTS_PATH = NAME + "/index.php/Api/Share/man";


    //银行卡登记列表的接口
    public static final String BANK_REGISTER_PATH = NAME + "/index.php/Api/Index/bankinfo";

    //添加银行卡页面返回的接口
    public static final String ADD_BANK_DETAIL_PATH = NAME + "/index.php/Api/Bank/mydata";

    //添加银行卡的接口
    public static final String ADD_BANK_PATH = NAME + "/index.php/Api/Bank/add";


    //添加银行卡的接口
    public static final String UP_MEMBER_PATH = NAME + "/index.php/Api/User/req";

    //个人信息中我的银行卡的接口
    public static final String MYBANK_CARD_PATH = NAME + "/index.php/Api/Bank/info";

    //修改我的银行卡的接口
    public static final String CHANGE_MYBANK_CARD_PATH = NAME + "/index.php/Api/Bank/modify";

    //分润提现的接口
    public static final String FENRUNWITHDRAWL_PATH = NAME + "/index.php/Api/Share/withdrawals";

    //分润收入的接口
    public static final String FENRUNGATHERING_PATH = NAME + "/index.php/Api/Share/income";

    //升级身份选择类型接口
    public static final String UPMEMBERTYPE_PATH = NAME + "/index.php/Api/User/usertype";

    //添加店铺分类型接口
    public static final String SHOPTYPE_PATH = NAME + "/index.php/Api/Store/classinfo";

    //添加店铺分类型接口
    public static final String ADDSHOP_PATH = NAME + "/index.php/Api/Store/setinfo";

    //判断店铺是否存在接口
    public static final String JUDGESHOP_PATH = NAME + "/index.php/Api/Store/isexist";

    //请求客服电话接口
    public static final String TEL_PATH = NAME + "/index.php/Api/Index/tel";

    //提交审核但是未通过再次审核的请求数据接口
    public static final String CONFIRM_AGAIN_PATH = NAME + "/index.php/Api/User/get_confirm";

    //支付提交确认收款的接口
    public static final String SUBMINT_PATH = NAME + "/index.php/Api/pay/confirm";

    //判断银行卡的类型的接口
    public static final String DOCARD_PATH = NAME + "/index.php/Api/reapal/step1";


    //快捷支付短信验证码的接口
    public static final String GETCODE_PATH = NAME + "/index.php/Api/reapal/send_sms";

    //快捷支付的接口
    public static final String PAYNEXT_PATH = NAME + "/index.php/Api/reapal/comfirm";


    /**
     * JS交互
     */
    //使用说明
    public static final String INSTRUCTION_PATH = NAME + "/wap/instruction.html";

    //关于我们
    public static final String ABOUTUS_PATH = NAME + "/wap/about_us.html";

    //限额说明
    public static final String LIMINT_PATH = NAME + "/wap/xiane.html";

    //认证说明
    public static final String CONFREM_PATH = NAME + "/wap/approve.html ";

    //分享介绍
    public static final String SHAREABOUT_PATH = NAME + "/wap/share.html  ";

    //添加店铺条款介绍
    public static final String READINGTERMS_PATH = NAME + "/wap/clause.html";


}

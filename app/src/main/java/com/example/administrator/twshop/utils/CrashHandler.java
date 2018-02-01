package com.example.administrator.twshop.utils;

/**
 * Created by Administrator on 2017/10/18.
 */

import android.content.Context;

/**
 * 自定义的 异常处理类 , 实现了 UncaughtExceptionHandler接口
 * <p>
 * 在做android项目开发时，大家都知道如果程序出错了，会弹出来一个强制退出的弹出框，这个本身没什么问题，但是这个UI实在是太丑了，别说用户接受不了，就连我们自己本身可能都接受不了。虽然我们在发布程序时总会经过仔细的测试，但是难免会碰到预料不到的错误。
 * <p>
 * 今天就来自定义一个程序出错时的处理，类似iphone的闪退。(虽然闪退也是用户不愿意看到的，但是在用户体验上明显比那个原生的弹窗好多了)
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    // 需求是 整个应用程序 只有一个 MyCrash-Handler
    private static CrashHandler INSTANCE;
    private Context context;

    //1.私有化构造方法
    private CrashHandler() {

    }

    public static synchronized CrashHandler getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CrashHandler();
        return INSTANCE;
    }

    public void init(Context context) {
        this.context = context;
    }


    public void uncaughtException(Thread arg0, Throwable arg1) {
        System.out.println("程序挂掉了 ");
        // 在此可以把用户手机的一些信息以及异常信息捕获并上传,由于UMeng在这方面有很程序的api接口来调用，故没有考虑

        //干掉当前的程序
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
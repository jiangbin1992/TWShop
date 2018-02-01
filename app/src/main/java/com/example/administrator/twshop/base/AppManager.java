package com.example.administrator.twshop.base;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * @author Luki
 * @version 1.0
 * @created 2012-3-21
 */
public class AppManager {
	
	private Stack<Activity> activityStack;
	private static AppManager instance;
	
	private AppManager(){}
	/**
	 * 单一实例
	 */
	public static AppManager getAppManager(){
		if(instance==null){
			instance=new AppManager();
		}
		return instance;
	}
	
	public int size(){
		if(activityStack==null){
			activityStack=new Stack<Activity>();
		}
		return activityStack.size();
	}
	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity){
		if(activityStack==null){
			activityStack=new Stack<Activity>();
		}
		activityStack.add(activity);
	}
	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity(){
		Activity activity=activityStack.lastElement();
		return activity;
	}
	/**
	 * 获取堆栈中所有Activity
	 * @return
	 */
	public List<Activity> GetAllActivity(){
		List<Activity> list_activity = new ArrayList<Activity>();
		for (int i = 0, size = activityStack.size(); i < size; i++){
			list_activity.add(activityStack.get(i));
	    }	
		return list_activity;
	}
	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity(){
		Activity activity=activityStack.lastElement();
		finishActivity(activity);
	}
	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity){
		if(activity!=null){
			activityStack.remove(activity);
			activity.finish();
			activity=null;
		}
	}
	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls){
		for (Activity activity : activityStack) {
			if(activity.getClass().equals(cls) ){
				finishActivity(activity);
			}
		}
	}
	/**
	 * 结束全部Activity
	 */
	public void finishAllActivity(){
		for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
            	activityStack.get(i).finish();
            }
	    }
		activityStack.clear();
	}
	/**
	 * 结束除此之外其它全部 Activity
	 */
	public void finishAllOtherActivity(Activity activity){
		for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i) && !activityStack.get(i).getClass().getName().equals(activity.getClass().getName())){
            	activityStack.get(i).finish();
            }
	    }	
	}
	
	/**
	 * 结束除此之外其它全部 Activity
	 */
	@SuppressWarnings("rawtypes")
	public void finishAllOtherActivity(Class clazz){
		for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i) && !activityStack.get(i).getClass().getName().equals(clazz.getName())){
            	activityStack.get(i).finish();
            }
	    }	
	}
	/**
	 *退出应用程序
	 */
	@SuppressWarnings("deprecation")
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e) {	}
	}
	
	/**
	 * 退出应用程序
	 */
	@SuppressWarnings("deprecation")
	public void AppExit() {
		try {
			finishAllActivity();
			ActivityManager activityMgr= (ActivityManager) instance.currentActivity().getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(instance.currentActivity().getPackageName());
			System.exit(0);
		} catch (Exception e) {	}
	}
}
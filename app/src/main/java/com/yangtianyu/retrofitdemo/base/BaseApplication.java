package com.yangtianyu.retrofitdemo.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.view.WindowManager;


import java.util.Iterator;
import java.util.List;


/**
 * Created by yangtianyu on 2016/10/20.
 */

public class BaseApplication extends Application {
    static Context context;

    public static String doctorHeadImgUrl = "";
    public static int screenHeight = 0;
    public static int screenWidth = 0;

    public static boolean openLiving = false;
//    public static ArrayList<IconImageChannel> mlist  =  new ArrayList<>();

    public static boolean isOpenLiving() {
        return openLiving;
    }

//    public static ArrayList<IconImageChannel> getIconImangeChannelList(){
//        return mlist ;
//    }


    public static void setOpenLiving(boolean openLiving) {
        BaseApplication.openLiving = openLiving;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        TIMManager.getInstance().init(this);
//        CrashHandler.getInstance().init(this);
        //获取屏幕宽高
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        screenHeight = dm.heightPixels;
        screenWidth = dm.widthPixels;

//        JPushInterface.setDebugMode(false); // 设置开启日志,发布时请关闭日志
//        JPushInterface.init(this);  //初始化 JPush


//        Ntalker.getInstance().initSDK(getApplicationContext(),"kf_9176","F42EA682-0FE6-4B18-ADAA-C83CE650DEFC");// 初始化SDK
//         Ntalker.getInstance().enableDebug(false);// 选择debug模式

      //  getAllIconImageList();
    }

//    private void getAllIconImageList() {
//        BaseCallback callback = new BaseCallback<IconImageChannelList>() {
//            @Override
//            public void onFailure(Request request, Exception e) {
//
//            }
//
//            @Override
//            public void onSuccess(Response response, IconImageChannelList iconImageChannelList) {
//                Log.d("jjjjjjjjjjjj",mlist.size()+"");
//
//                if (iconImageChannelList.getIconImageList() != null && iconImageChannelList.getIconImageList().size() >= 0) {
//                    mlist.clear();
//                    for (int i = 0; i < iconImageChannelList.getIconImageList().size(); i++) {
//                        if(iconImageChannelList.getIconImageList().get(i).getIconCode()==201){
//                            mlist.add(0,iconImageChannelList.getIconImageList().get(i));
//                        }else if(iconImageChannelList.getIconImageList().get(i).getIconCode()==202){
//                            mlist.add(1,iconImageChannelList.getIconImageList().get(i));
//                        }
//                    }
//                    Log.d("jjjjjjjjjjjj",mlist.size()+"");
//                }
//            }
//        };
//        NetRequestApi.getAllIconImageList(context, callback);
//
//    }

    public static Context getContext() {
        return context;
    }


    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(int pid) {
        String processName = null;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pid) {
                    // 根据进程的信息获取当前进程的名字
                    processName = info.processName;
                    // 返回当前进程名
                    return processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 没有匹配的项，返回为null
        return null;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

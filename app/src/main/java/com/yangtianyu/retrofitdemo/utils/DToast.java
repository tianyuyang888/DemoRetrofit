package com.yangtianyu.retrofitdemo.utils;

import android.os.Handler;
import android.widget.Toast;

import com.yangtianyu.retrofitdemo.base.BaseApplication;


/** 延迟作用的taost，保证一定时间内只会显示一次
 * Created by zhangsong25 on 16/3/5.
 */
public class DToast {
    private static Handler handler = new Handler();
    private static int DELAY = 0X657;
    private static int INTERVAL = 2000;


    public static void makeToast(String msg) {
        if(!handler.hasMessages(DELAY)){
            Toast.makeText(BaseApplication.getContext(),msg, Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessageDelayed(DELAY,INTERVAL);
        }
    }

    public static void makeToast(int StrRes) {
        if(!handler.hasMessages(DELAY)){
            Toast.makeText(BaseApplication.getContext(),StrRes, Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessageDelayed(DELAY,INTERVAL);
        }
    }
}

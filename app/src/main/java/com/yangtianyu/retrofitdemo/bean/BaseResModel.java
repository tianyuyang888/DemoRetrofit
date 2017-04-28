package com.yangtianyu.retrofitdemo.bean;


import retrofit2.Callback;

/**
 * 网络返回基类 支持泛型
 * Created by Tamic on 2016-06-06.
 */
public class BaseResModel<T>{

    private static final int CODE_SUCCESS = 200;

//    {
//        "code": 200,
//            "data": {},
//        "msg": "登录成功!"
//    }
    public int code;
    public String msg;
    public T data;

    public boolean isSuccess(){
        return CODE_SUCCESS == code;
    }
}

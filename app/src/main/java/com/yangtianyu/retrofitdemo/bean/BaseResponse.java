package com.yangtianyu.retrofitdemo.bean;

/**
 * Created by yangtianyu on 2017/4/12.
 */

public class BaseResponse<T> {
    public int code;
    public String msg = "";
    public T data;
}

package com.yangtianyu.retrofitdemo.network;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.yangtianyu.retrofitdemo.bean.BaseResponse;
import com.yangtianyu.retrofitdemo.utils.DToast;
import com.yangtianyu.retrofitdemo.utils.Log;


import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yangtianyu on 2017/4/12.
 */

public abstract class MyCallback<T extends BaseResponse> implements Callback<T> {
    private static final int TOKEN_OK = 200;
    private static final int TOKEN_ERROR = 600; //token过期

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.raw().code() == 200){
            switch (response.body().code) {
                case TOKEN_OK:
                    onResponseSuccess(response.body().data);
                    break;
                case TOKEN_ERROR:
                    failure(response.body().msg);
                    break;
                case 608:
                    failure(response.body().msg);
                    break;
                default:
                    failure(response.body().msg);
                    break;
            }
        }else {
            onFailure(call,new RuntimeException("response error,detail = " + response.raw().toString()));
        }

    }

    private void failure(String msg) {
        DToast.makeToast("msg:"+msg);
    }

    protected abstract void onResponseSuccess(Object body);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof SocketTimeoutException){
            onNetError();
        }else if (t instanceof ConnectException){
            onNetError();
        }else if (t instanceof RuntimeException){
            onSysError();
        }
    }

//    网络错误
    private void onSysError() {
        DToast.makeToast("网络错误");
    }

//    系统错误
    private void onNetError() {
        DToast.makeToast("系统错误");
    }


}

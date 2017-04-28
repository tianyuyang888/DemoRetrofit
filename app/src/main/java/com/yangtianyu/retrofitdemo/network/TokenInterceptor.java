package com.yangtianyu.retrofitdemo.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by yangtianyu on 2017/4/27.
 */

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}

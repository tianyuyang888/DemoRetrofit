package com.yangtianyu.retrofitdemo.network;

import com.yangtianyu.retrofitdemo.base.LocalConstant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangtianyu on 2017/4/27.
 */

public class ApiManger {
    private volatile static ApiManger apiManger;
    private static QMApi qmApi;
    private static QMApi qmApi2;
    private static OkHttpClient client = new OkHttpClient.Builder()
            // Log信息拦截器
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            //设置超时
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            //错误重连
            .retryOnConnectionFailure(true).build();

    private ApiManger(){}

    /**
     * Description: 获取ApiManger实例
     * Date: 2017/4/27 17:55
     */
    public static ApiManger getInstance(){
        if (apiManger == null){
            synchronized (ApiManger.class){
                if (apiManger == null) apiManger = new ApiManger();
            }
        }
        return apiManger;
    }

    /**
     * Description: 获取QMApi实例
     * Date: 2017/4/27 18:10
     */
    public QMApi getQMApi(){
        if (qmApi == null){
            synchronized (ApiManger.class){
                if (qmApi == null){
                    qmApi = new Retrofit.Builder()
                            .baseUrl(LocalConstant.BASE_URL2)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(QMApi.class);
                }
            }
        }
        return qmApi;
    }

    /**
     * Description: 传入baseUrl获取QMApi实例
     * Date: 2017/4/28 10:07
     * @param url retrofit的baseUrl
     */
    public QMApi getQMApi(String url){
        qmApi2 = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(QMApi.class);
        return  qmApi2;
    }



}

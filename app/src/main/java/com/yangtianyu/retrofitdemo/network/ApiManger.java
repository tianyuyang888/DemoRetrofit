package com.yangtianyu.retrofitdemo.network;

import com.yangtianyu.retrofitdemo.base.LocalConstant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangtianyu on 2017/4/27.
 */

public class ApiManger {
    private volatile static ApiManger apiManger;
    private static QMApi qmApi;
    private static TokenInterceptor tokenInterceptor;
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(tokenInterceptor)
            .build();

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
                            .baseUrl(LocalConstant.BASE_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(QMApi.class);
                }
            }
        }
        return qmApi;
    }

}

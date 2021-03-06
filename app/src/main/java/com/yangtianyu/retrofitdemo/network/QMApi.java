package com.yangtianyu.retrofitdemo.network;

import com.yangtianyu.retrofitdemo.bean.AddressEntity;
import com.yangtianyu.retrofitdemo.bean.BaseResModel;
import com.yangtianyu.retrofitdemo.bean.UserInfoEntity;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by yangtianyu on 2017/4/10.
 */

public interface QMApi {
    @GET("10001/getProvinceCityAreaList")
    Call<BaseResModel<AddressEntity>> getProvinceCityAreaList(@Query("localVersion") String localVersion);

    @POST("10001/qmLogin")
    Call<BaseResModel<UserInfoEntity>> login(@Body RequestBody jsonBody);
}

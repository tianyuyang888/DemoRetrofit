package com.yangtianyu.retrofitdemo.network;

import com.yangtianyu.retrofitdemo.bean.AddressEntity;
import com.yangtianyu.retrofitdemo.bean.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yangtianyu on 2017/4/10.
 */

public interface QMService {
    @GET("10001/getProvinceCityAreaList")
    Call<BaseResponse<AddressEntity>> repos(@Query("localVersion") String localVersion);
}

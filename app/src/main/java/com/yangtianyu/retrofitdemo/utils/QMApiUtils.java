package com.yangtianyu.retrofitdemo.utils;

import com.yangtianyu.retrofitdemo.base.LocalConstant;
import com.yangtianyu.retrofitdemo.bean.AddressEntity;
import com.yangtianyu.retrofitdemo.bean.BaseResModel;
import com.yangtianyu.retrofitdemo.bean.UserInfoEntity;
import com.yangtianyu.retrofitdemo.network.ApiManger;
import com.yangtianyu.retrofitdemo.network.RetrofitCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by yangtianyu on 2017/4/28.
 */

public class QMApiUtils {

    /**
     * Description: 获取省市县地址列表
     * Date: 2017/4/28 16:16
     */
    public static void getAddress(String localVersion, RetrofitCallback<BaseResModel<AddressEntity>,AddressEntity> callBack){
        Call<BaseResModel<AddressEntity>> call = ApiManger.getInstance().getQMApi(LocalConstant.BASE_URL).getProvinceCityAreaList(localVersion);
        call.enqueue(callBack);
    }

    /**
     * Description: 登录
     * Date: 2017/4/28 16:40
     */
    public static void login(Map<String,String> params,RetrofitCallback<BaseResModel<UserInfoEntity>,UserInfoEntity> callback){
        Call<BaseResModel<UserInfoEntity>> login = ApiManger.getInstance().getQMApi().login(getRequestBody(params));
        login.enqueue(callback);
    }

    /**
     * @param params 请求的参数集合
     * @return 获取requestBody
     */
    private static RequestBody getRequestBody(Map<String, String> params) {
        String json = getJson(params);
        Log.d(json);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(JSON, json);
    }

    /**
     * @param params 请求的参数集合
     * @return 获取加密后的json字符串
     */
    private static String getJson(Map<String, String> params) {
        JSONObject obj = new JSONObject();
        try {
            for (String value : params.keySet()) {
                obj.put(value,params.get(value));
            }
            obj.put("sign", Md5Util.getSign((HashMap) params));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
}

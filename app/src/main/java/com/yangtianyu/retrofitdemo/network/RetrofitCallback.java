package com.yangtianyu.retrofitdemo.network;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.yangtianyu.retrofitdemo.bean.BaseResModel;
import com.yangtianyu.retrofitdemo.utils.DToast;
import com.yangtianyu.retrofitdemo.utils.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yangtianyu on 2017/4/28.
 */

public abstract class RetrofitCallback<T,F> implements Callback<T> {

    private Context mContext;
    private Handler mHandler = new Handler();

    public RetrofitCallback(Context context){
        this.mContext = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()){
//            String res = response.body().toString();
            if (((BaseResModel)response.body()).code == 200){
                if (((BaseResModel) response.body()).data != null){
                    onSuccess((F) ((BaseResModel) response.body()).data);
                }else {
                    DToast.makeToast("返回数据为null");
                }
            }else {
                failure(((BaseResModel) response.body()).code,((BaseResModel) response.body()).msg);
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        failure(0,"网络错误");
    }

    private void failure(final int code, final String msg) {
        if (!TextUtils.isEmpty(msg))
        Log.d("报错的信息：" + msg);
        onFailure();
    }


    public abstract void onSuccess(F data);

    public abstract void onFailure();
}

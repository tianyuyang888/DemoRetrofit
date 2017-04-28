package com.yangtianyu.retrofitdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yangtianyu.retrofitdemo.fragment.RxjavaFragment;
import com.yangtianyu.retrofitdemo.utils.Md5Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity {

    private TextView mTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.tv_test);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                login();
                getAddress();
            }
        });
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null){
            fragment = new RxjavaFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,fragment);
//                    .commit();
        }


    }

    private void login() {
//        Map<String, String> params = new LinkedHashMap<>();
//        params.put("password","111111");
//        params.put("phoneNumber","18862966889");
//        String json = getJson(params);
//        Log.i("json---->" + json);
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, json);
//        Call<ResponseBody> login = mService.login(body);
//        login.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
//                if (response.isSuccessful())
//                    Log.d(response.code()+"aaaaaaaaaaaaaaaaa");
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
    }


    private void getAddress() {

    }


    private String getJson(Map<String, String> params) {
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

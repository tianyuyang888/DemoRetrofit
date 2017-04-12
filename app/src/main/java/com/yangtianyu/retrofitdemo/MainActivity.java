package com.yangtianyu.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yangtianyu.retrofitdemo.base.LocalConstant;
import com.yangtianyu.retrofitdemo.bean.AddressEntity;
import com.yangtianyu.retrofitdemo.bean.BaseResponse;
import com.yangtianyu.retrofitdemo.network.MyCallback;
import com.yangtianyu.retrofitdemo.network.QMService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.tv_test);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LocalConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QMService service = retrofit.create(QMService.class);
        Call<BaseResponse<AddressEntity>> repos = service.repos("0");
        repos.enqueue(new MyCallback<BaseResponse<AddressEntity>>() {
            @Override
            protected void onResponseSuccess(Object body) {
                AddressEntity addressEntity = (AddressEntity) body;
                mTv.setText(addressEntity.provinceList.get(0).provinceName);
            }
        });
    }
}

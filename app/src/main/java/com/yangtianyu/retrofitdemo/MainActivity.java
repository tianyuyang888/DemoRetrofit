package com.yangtianyu.retrofitdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yangtianyu.retrofitdemo.bean.AddressEntity;
import com.yangtianyu.retrofitdemo.bean.BaseResModel;
import com.yangtianyu.retrofitdemo.bean.UserInfoEntity;
import com.yangtianyu.retrofitdemo.network.RetrofitCallback;
import com.yangtianyu.retrofitdemo.utils.DToast;
import com.yangtianyu.retrofitdemo.utils.QMApiUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @Bind(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @Bind(R.id.tv_test)
    TextView mTvTest;
    @Bind(R.id.tv_login)
    TextView mTvLogin;
    @Bind(R.id.tv_1)
    TextView mTv1;
    @Bind(R.id.tv_2)
    TextView mTv2;
    @Bind(R.id.activity_main)
    RelativeLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void getAddress() {
        QMApiUtils.getAddress("10", new RetrofitCallback<BaseResModel<AddressEntity>, AddressEntity>(MainActivity.this) {
            @Override
            public void onSuccess(AddressEntity data) {
                mTv1.setText(data.provinceList.get(0).provinceName);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    private void login() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("password", "111111");
        params.put("phoneNumber", "18862966889");
        QMApiUtils.login(params, new RetrofitCallback<BaseResModel<UserInfoEntity>, UserInfoEntity>(this) {
            @Override
            public void onSuccess(UserInfoEntity data) {
                mTv2.setText(data.nickname);
            }

            @Override
            public void onFailure() {

            }
        });
    }


    @OnClick({R.id.tv_test, R.id.tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_test:
                getAddress();
                break;
            case R.id.tv_login:
                login();
                break;
        }
    }
}

package com.yangtianyu.retrofitdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangtianyu.retrofitdemo.R;
import com.yangtianyu.retrofitdemo.utils.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by yangtianyu on 2017/4/18.
 */

public class RxjavaFragment extends Fragment{

    private View mInflate;
    private Disposable mDisposable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mInflate == null){
            mInflate = inflater.inflate(R.layout.fragment_rxjava, null);
            initData();
        }
        return mInflate;
    }

    private void initData() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d("emitter 1");
                emitter.onNext(1);
                Log.d("emitter 2");
                emitter.onNext(2);
                Log.d("emitter 3");
                emitter.onNext(3);
                Log.d("emitter onComplete");
                emitter.onComplete();
                emitter.onNext(4);
                Log.d("emitter 4");
                emitter.onNext(5);
                Log.d("emitter 5");
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("onSubscribe");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer value) {
                Log.d(value+"");
                if (value == 2) mDisposable.dispose();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("error");
            }

            @Override
            public void onComplete() {
                Log.d("onComplete");
            }
        });
    }

}

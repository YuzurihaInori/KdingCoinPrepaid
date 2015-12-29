package com.kding.kdingcoinprepaid.v.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.p.ILoading;
import com.kding.kdingcoinprepaid.p.impl.LoadingImpl;

public class LoadingActivity extends BaseCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ILoading loading = new LoadingImpl(this);
        loading.startApp();

    }
}

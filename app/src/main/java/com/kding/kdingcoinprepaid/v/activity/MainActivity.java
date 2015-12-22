package com.kding.kdingcoinprepaid.v.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kding.kdingcoinprepaid.R;
import com.socks.library.KLog;

public class MainActivity extends BaseCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    public void regClick(View view) {
        KLog.i("regClick");

        startActivity(new Intent(mContext,RegisterActivity.class));

        finish();

    }
    public void loginClick(View view) {
        KLog.i("loginClick");

        startActivity(new Intent(mContext, LoginActivity.class));

        finish();
    }
}

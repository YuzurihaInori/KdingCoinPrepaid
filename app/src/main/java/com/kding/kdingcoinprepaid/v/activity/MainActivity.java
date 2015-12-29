package com.kding.kdingcoinprepaid.v.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.consts.KeyConst;
import com.kding.kdingcoinprepaid.p.IMain;
import com.kding.kdingcoinprepaid.p.impl.MainImpl;
import com.socks.library.KLog;

public class MainActivity extends BaseCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void regClick(View view) {
        KLog.i("regClick");

        startActivityForResult(new Intent(mContext, RegisterActivity.class),KeyConst.MAIN_TO_REG_REQUEST_KEY);


    }
    public void loginClick(View view) {
        KLog.i("loginClick");

        startActivityForResult(new Intent(mContext, LoginActivity.class),KeyConst.MAIN_TO_LOGIN_REQUEST_KEY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            switch (requestCode){
                case KeyConst.MAIN_TO_REG_REQUEST_KEY:
                    finish();
                    break;
                case KeyConst.MAIN_TO_LOGIN_REQUEST_KEY:
                    finish();
                    break;
                default:
                    break;
            }
        }
    }
}

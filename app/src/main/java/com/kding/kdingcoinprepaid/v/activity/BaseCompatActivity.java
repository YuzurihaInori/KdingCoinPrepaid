package com.kding.kdingcoinprepaid.v.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Toast-pc on 2015/12/17.
 */
public class BaseCompatActivity extends AppCompatActivity{

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        mContext = this;
    }

    protected void setTooblBar(int toolbarId, String title,boolean showIcon,boolean showBack) {
        Toolbar mToolbar = (Toolbar) findViewById(toolbarId);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        toobarAsBackButton(mToolbar,showIcon,showBack);
    }

    protected void setTooblBar(int toolbarId, int titleId,boolean showIcon,boolean showBack) {
        setTooblBar(toolbarId, getString(titleId),showIcon,showBack);
    }

    public void toobarAsBackButton(Toolbar toolbar,boolean showIcon,boolean showBack) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(showIcon);
        getSupportActionBar().setDisplayShowHomeEnabled(showBack);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

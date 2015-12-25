package com.kding.kdingcoinprepaid.v.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.CoinToCashBean;
import com.kding.kdingcoinprepaid.p.callback.IResultCallBack;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.p.impl.CoinToCashImpl;
import com.kding.kdingcoinprepaid.utils.MToast;
import com.kding.kdingcoinprepaid.utils.StringUtils;

import java.util.List;

public class CoinToCashActivity extends BaseCompatActivity implements IResultCallBack<CoinToCashBean>,IUserInterfaceCallBack{

    private TextView coinToCash;
    private TextView coinToCashCard;
    private EditText coinToCashNum;
    private TextView coinToCashMin;
    private TextView coinToCashRemain;

    private int maxCash = 0;
    private int minCash = 0;
    private int remainTimes = 0;
    private CoinToCashImpl coinToCashImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_to_cash);

        initView();


        coinToCashImpl = new CoinToCashImpl(this,this);
        coinToCashImpl.getDataFromServer();
    }

    private void initView() {

        setTooblBar(R.id.common_toolbar,R.string.coin_to_cash_title,true,true);

        coinToCash = (TextView)findViewById(R.id.coin_to_cash);
        coinToCashCard = (TextView)findViewById(R.id.coin_to_cash_card);
        final TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        coinToCashNum = (EditText)findViewById(R.id.coin_to_cash_num);
        coinToCashMin = (TextView)findViewById(R.id.coin_to_cash_min);
        coinToCashRemain = (TextView)findViewById(R.id.coin_to_cash_remain);
        Button leftBtn = (Button) findViewById(R.id.coin_to_cash_record);
        Button rightBtn = (Button) findViewById(R.id.go_to_coin_to_cash_submit);

        coinToCashNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtils.toInt(coinToCashNum.getText().toString()) > maxCash) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("超过提现上限");
                } else if (StringUtils.toInt(coinToCashNum.getText().toString()) < minCash) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("低于提现下限");
                } else {
                    textInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,CoinToCashRecordActivity.class));
            }
        });

        
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = StringUtils.toInt(coinToCashNum.getText().toString());
                if (num < minCash){
                    MToast.showShort(mContext,"提现金额不能低于下限");
                    return;
                }
                if (num > maxCash){
                    MToast.showShort(mContext,"提现金额不能高于上限");
                    return;
                }
                if (remainTimes == 0){
                    MToast.showShort(mContext,"本月提现次数用完了");
                    return;
                }
                coinToCashImpl.postToServer();
            }
        });
    }


    @Override
    public void resultSuc(List<CoinToCashBean> result) {
        bindData(result.get(0));
    }

    @Override
    public void resultFail(String errorMsg) {

    }

    private void bindData(CoinToCashBean coinToCashBean) {

        minCash = coinToCashBean.coinToCashMin;
        maxCash = coinToCashBean.coinToCashMax;
        remainTimes = coinToCashBean.coinToCashRemain;
        coinToCash.setText(String.format(this.getResources().getString(R.string.coin_to_cash),coinToCashBean.coinToCashMax));
        coinToCashCard.setText(String.format(this.getResources().getString(R.string.coin_to_cash_card),coinToCashBean.coinToCashCard));
        coinToCashMin.setText(String.format(this.getResources().getString(R.string.coin_to_cash_min),coinToCashBean.coinToCashMin));
        coinToCashRemain.setText(String.format(this.getResources().getString(R.string.coin_to_cash_remain),coinToCashBean.coinToCashRemain));
    }

    @Override
    public void operationSuc() {
        MToast.showShort(mContext,"提交成功");
    }

    @Override
    public void operationFail(String errorMsg) {
        MToast.showShort(mContext,errorMsg);

    }
}

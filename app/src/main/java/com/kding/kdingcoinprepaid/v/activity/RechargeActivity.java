package com.kding.kdingcoinprepaid.v.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.p.impl.RechargeImpl;
import com.kding.kdingcoinprepaid.utils.MToast;

public class RechargeActivity extends BaseCompatActivity implements IUserInterfaceCallBack{

    private TextView rechargeRmb;
    private TextView rechargeDiscount;
    private TextView rechargeCoin;
    private ActionProcessButton rechargeSubmit;
    private RechargeImpl rechargeImpl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        rechargeImpl = new RechargeImpl(this);

        Intent intent = getIntent();

        if (intent !=null && intent.getStringExtra(ConstantTag.CURRENT_DISCOUNT_KEY)!=null){
            initView(intent.getStringExtra(ConstantTag.CURRENT_DISCOUNT_KEY));
        }
    }

    private void initView(final String discount) {

        setTooblBar(R.id.common_toolbar,R.string.recharge_title,true,true);

        rechargeRmb = (TextView)findViewById(R.id.recharge_rmb_edit);
        rechargeDiscount = (TextView)findViewById(R.id.recharge_discount);
        rechargeCoin = (TextView)findViewById(R.id.recharge_coin_num);
        rechargeSubmit = (ActionProcessButton)findViewById(R.id.recharge_submit);

        rechargeCoin.setText("0");
        rechargeDiscount.setText(discount);

        rechargeRmb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s !=null &&!s.toString().equals("") && Integer.valueOf(s.toString()) != 0) {
                    rechargeCoin.setText(String.valueOf(rechargeImpl.getCoin(s.toString(), discount)));
                }else {
                    rechargeCoin.setText("0");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rechargeSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rmb = rechargeRmb.getText().toString();
                if (rmb.equals("")) {
                    MToast.showShort(mContext, "输入金额不能为空");
                }else if (Integer.valueOf(rmb) == 0){
                    MToast.showShort(mContext, "充值金额不能为零");
                }else {
                    rechargeImpl.post(rmb);
                    rechargeSubmit.setProgress(50);
                    rechargeSubmit.setClickable(false);
                }
            }
        });
    }

    @Override
    public void operationSuc() {
        rechargeSubmit.setProgress(100);
        rechargeSubmit.setClickable(true);
        MToast.showShort(mContext, "充值成功");

        finish();
    }

    @Override
    public void operationFail(String errorMsg) {
        rechargeSubmit.setProgress(-1);
        MToast.showShort(mContext,errorMsg);
        rechargeSubmit.setClickable(true);
    }
}

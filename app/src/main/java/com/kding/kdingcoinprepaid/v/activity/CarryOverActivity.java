package com.kding.kdingcoinprepaid.v.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.p.impl.CarryOverImpl;
import com.kding.kdingcoinprepaid.utils.MToast;

public class CarryOverActivity extends BaseCompatActivity implements IUserInterfaceCallBack {

    private CarryOverImpl carryOverImpl;
    private ActionProcessButton carryOverSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carry_over);
        
        carryOverImpl = new CarryOverImpl(this);

        initView();
    }

    private void initView() {

        setTooblBar(R.id.common_toolbar,R.string.carry_over_title,true,true);

        final TextInputLayout textInputTo = (TextInputLayout) findViewById(R.id.textInputLayout_to_accout);
        final EditText toAccout = (EditText)findViewById(R.id.carray_over_to_accout);
        final TextInputLayout textInputToConfirm = (TextInputLayout) findViewById(R.id.textInputLayout_to_accout_confirm);
        final EditText toAccoutConfirm = (EditText)findViewById(R.id.carray_over_to_accout_confirm);
        final EditText coinNum  = (EditText)findViewById(R.id.carray_over_coin_num);
        carryOverSubmit = (ActionProcessButton)findViewById(R.id.carray_over_submit);

        toAccout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==0){
                    textInputTo.setErrorEnabled(true);
                    textInputTo.setError("账号不能为空");
                }else {
                    textInputTo.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        toAccoutConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!toAccoutConfirm.getText().toString().equals(toAccout.getText().toString())){
                    textInputToConfirm.setErrorEnabled(true);
                    textInputToConfirm.setError("两次账号不一致");
                }
                else {
                    textInputToConfirm.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        carryOverSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toAccoutString = toAccout.getText().toString();
                String toAccoutConfirmString = toAccoutConfirm.getText().toString();
                String coinNumString = coinNum.getText().toString();

                if ("".equals(toAccoutString)|| "".equals(toAccoutConfirmString)){
                    MToast.showShort(mContext,"账号不能为空");
                    return;
                }
                if (!toAccoutString.equals(toAccoutConfirmString)){
                    MToast.showShort(mContext,"两次账号不一致");
                    return;
                }

                if (coinNumString.equals("")){
                    MToast.showShort(mContext,"划转金额不能为空");
                    return;
                }
                if (Integer.valueOf(coinNumString)==0){
                    MToast.showShort(mContext,"划转金额不能为零");
                    return;
                }

                carryOverImpl.post(toAccoutString,coinNumString);
                carryOverSubmit.setProgress(50);
                carryOverSubmit.setClickable(false);
            }
        });
    }

    @Override
    public void operationSuc() {
        carryOverSubmit.setProgress(100);
        carryOverSubmit.setClickable(true);
        MToast.showShort(mContext,"划转成功");
    }

    @Override
    public void operationFail(String errorMsg) {
        carryOverSubmit.setProgress(-1);
        carryOverSubmit.setClickable(true);
        MToast.showShort(mContext,"errorMsg");
    }
}

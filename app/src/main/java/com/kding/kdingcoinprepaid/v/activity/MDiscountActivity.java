package com.kding.kdingcoinprepaid.v.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.p.impl.MDiscountImpl;
import com.kding.kdingcoinprepaid.utils.MToast;

public class MDiscountActivity extends BaseCompatActivity implements IUserInterfaceCallBack{

    private ActionProcessButton discountSubmit;
    private MDiscountImpl mDiscountImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdiscount);

        mDiscountImpl = new MDiscountImpl(this);

        Intent intent = getIntent();
        if (intent != null && intent.getStringExtra(ConstantTag.CURRENT_DISCOUNT_KEY)!=null){
            initView(intent.getStringExtra(ConstantTag.CURRENT_DISCOUNT_KEY));
        }


    }

    private void initView(final String discount) {

        setTooblBar(R.id.common_toolbar,R.string.m_discount_title,true,true);

        final int discountInt = Integer.valueOf(discount.replace("%", ""));
        final TextInputLayout textInputLayoutDiscount = (TextInputLayout) findViewById(R.id.textInputLayout_discount);
        final EditText discountEdit = (EditText) findViewById(R.id.m_discount_edit);
        TextView defultDiscount = (TextView) findViewById(R.id.m_discount_discount);
        discountSubmit = (ActionProcessButton)findViewById(R.id.m_discount_submit);

        defultDiscount.setText(discount);

        discountEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String mDiscount = discountEdit.getText().toString();

                if (mDiscount.equals("")) {
                    textInputLayoutDiscount.setErrorEnabled(true);
                    textInputLayoutDiscount.setError("折扣不能为空");
                } else {
                    if (Integer.valueOf(mDiscount) < discountInt) {
                        textInputLayoutDiscount.setErrorEnabled(true);
                        textInputLayoutDiscount.setError("折扣不能低于最低折扣");
                    } else {
                        textInputLayoutDiscount.setErrorEnabled(false);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        discountSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textInputLayoutDiscount.isErrorEnabled()){
                    MToast.showShort(mContext,textInputLayoutDiscount.getError());
                    return;
                }
                mDiscountImpl.post(discountEdit.getText().toString());
                discountSubmit.setProgress(50);
                discountSubmit.setClickable(false);
            }
        });

    }

    @Override
    public void operationSuc() {
        discountSubmit.setProgress(100);
        discountSubmit.setClickable(true);
        MToast.showShort(mContext, "折扣设置成功");
    }

    @Override
    public void operationFail(String errorMsg) {
        discountSubmit.setProgress(-1);
        MToast.showShort(mContext, errorMsg);
        discountSubmit.setClickable(true);
    }
}

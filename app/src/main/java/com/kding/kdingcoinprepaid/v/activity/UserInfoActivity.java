package com.kding.kdingcoinprepaid.v.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.consts.UserInterfaceType;
import com.kding.kdingcoinprepaid.p.IUserInfo4Card;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.p.impl.UserInfo4CardImpl;
import com.kding.kdingcoinprepaid.utils.MToast;

public class UserInfoActivity extends BaseCompatActivity implements IUserInterfaceCallBack{

    private EditText userEdit;
    private EditText cardInfoEdit;
    private EditText cardNumEdit;
    private ImageView img1;
    private ImageView img2;

    private String cardImgPath1;
    private String cardImgPath2;
    private ActionProcessButton submit;
    private UserInfo4CardImpl userInfo4CardImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        
        initView();

        userInfo4CardImpl = new UserInfo4CardImpl(this);
    }

    private void initView() {

        setTooblBar(R.id.common_toolbar,R.string.user_info_title,true,true);

        final TextInputLayout textInputLayoutUser = (TextInputLayout) findViewById(R.id.textInputLayout_name);
        userEdit=(EditText)findViewById(R.id.editText_name);
        cardInfoEdit=(EditText)findViewById(R.id.editText_card_info);
        final TextInputLayout textInputLayoutCardInfo = (TextInputLayout) findViewById(R.id.textInputLayout_card_info);
        cardNumEdit=(EditText)findViewById(R.id.editText_card_num);
        final TextInputLayout textInputLayoutCardNum = (TextInputLayout) findViewById(R.id.textInputLayout_card_num);
        img1 = (ImageView)findViewById(R.id.user_info_card_img_1);
        img2 = (ImageView)findViewById(R.id.user_info_card_img_2);
        submit = (ActionProcessButton)findViewById(R.id.user_info_submit);

        userEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (userEdit.getText().toString().equals("")){
                    textInputLayoutUser.setErrorEnabled(true);
                    textInputLayoutUser.setError("姓名不可为空");
                }else {
                    textInputLayoutUser.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cardInfoEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (cardInfoEdit.getText().toString().equals("")){
                    textInputLayoutCardInfo.setErrorEnabled(true);
                    textInputLayoutCardInfo.setError("开户行不可为空");
                }else {
                    textInputLayoutCardInfo.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cardNumEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (cardNumEdit.getText().toString().equals("")){
                    textInputLayoutCardNum.setErrorEnabled(true);
                    textInputLayoutCardNum.setError("卡号不可为空");
                }else {
                    textInputLayoutCardNum.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2015/12/25

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2015/12/25

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textInputLayoutUser.isErrorEnabled()){
                    MToast.showShort(mContext,textInputLayoutUser.getError());
                    return;
                }
                if (textInputLayoutCardInfo.isErrorEnabled()){
                    MToast.showShort(mContext,textInputLayoutCardInfo.getError());
                    return;
                }
                if (textInputLayoutCardNum.isErrorEnabled()){
                    MToast.showShort(mContext,textInputLayoutCardNum.getError());
                    return;
                }
                if (cardImgPath1 == null || cardImgPath2 == null){
                    MToast.showShort(mContext,"未上传身份证照片");
                    return;
                }

                userInfo4CardImpl.postToServer(userEdit.getText().toString(), cardInfoEdit.getText().toString(),
                        cardNumEdit.getText().toString(), cardImgPath1, cardImgPath2);
                submit.setProgress(50);
                submit.setClickable(false);
            }
        });
    }

    @Override
    public void operationSuc() {
        submit.setProgress(100);
        submit.setClickable(true);
    }

    @Override
    public void operationFail(String errorMsg) {
        submit.setProgress(-1);
        MToast.showShort(mContext, errorMsg);
        submit.setClickable(true);
    }
}

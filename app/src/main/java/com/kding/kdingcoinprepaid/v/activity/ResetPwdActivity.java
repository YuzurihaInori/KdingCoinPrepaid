package com.kding.kdingcoinprepaid.v.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.consts.UserInterfaceType;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.p.impl.UserInterfaceImpl;
import com.kding.kdingcoinprepaid.utils.MToast;

public class ResetPwdActivity extends BaseCompatActivity implements IUserInterfaceCallBack {

    private TextInputLayout textInputLayoutpwd;
    private EditText editTextpwd;
    private TextInputLayout textInputLayoutpwdC;
    private EditText editTextpwdC;
    private ActionProcessButton buttonReset;
    private UserInterfaceImpl resetImpl;
    private String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);

        resetImpl = new UserInterfaceImpl(this,this);

        Intent intent= getIntent();
        if (intent !=null && intent.getStringExtra(ConstantTag.USERNAME_KEY)!=null){
            userName = intent.getStringExtra(ConstantTag.USERNAME_KEY);
        }

        initView();

    }

    private void initView() {

        textInputLayoutpwd = (TextInputLayout)findViewById(R.id.textInputLayout_password);
        editTextpwd = (EditText)findViewById(R.id.editText_pwd);
        textInputLayoutpwdC = (TextInputLayout)findViewById(R.id.textInputLayout_password_confirm);
        editTextpwdC = (EditText)findViewById(R.id.editText_pwd_confirm);
        buttonReset = (ActionProcessButton)findViewById(R.id.button_reset);


        setTooblBar(R.id.common_toolbar, R.string.reset_pwd_title,true,true);



        editTextpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()<8){
                    textInputLayoutpwd.setErrorEnabled(true);
                    textInputLayoutpwd.setError("密码太短啦");
                }else {
                    textInputLayoutpwd.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextpwdC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editTextpwd.getText().toString())){
                    textInputLayoutpwdC.setErrorEnabled(true);
                    textInputLayoutpwdC.setError("两次密码不一致");
                }
                else {
                    textInputLayoutpwdC.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pwd = editTextpwd.getText().toString();
                String pwdConfirm = editTextpwdC.getText().toString();

                if (userName.equals("")){
                    MToast.showShort(mContext,"未知错误");
                    return;
                }

                if (pwd.length() < 8) {
                    MToast.showShort(mContext, "密码太短");
                    return;
                }
                if (!pwdConfirm.equals(pwd)) {
                    MToast.showShort(mContext, "两次密码不一致");
                    return;
                }

                resetImpl.operation(UserInterfaceType.ResetPwd, userName, pwd);
                buttonReset.setProgress(50);
                buttonReset.setClickable(false);
            }
        });
    }

    @Override
    public void operationSuc() {
        buttonReset.setProgress(100);
        buttonReset.setClickable(true);
    }

    @Override
    public void operationFail(String errorMsg) {
        buttonReset.setProgress(-1);
        MToast.showShort(mContext,errorMsg);
        buttonReset.setClickable(true);
    }
}

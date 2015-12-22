package com.kding.kdingcoinprepaid.v.activity;

import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.kding.kdingcoinprepaid.consts.UserInterfaceType;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.p.impl.UserInterfaceImpl;
import com.kding.kdingcoinprepaid.utils.MToast;

public class RegisterActivity extends BaseCompatActivity implements IUserInterfaceCallBack {

    private TextInputLayout textInputLayoutUser;
    private EditText editTextUser;
    private TextInputLayout textInputLayoutpwd;
    private EditText editTextpwd;
    private TextInputLayout textInputLayoutpwdC;
    private EditText editTextpwdC;
    private ActionProcessButton buttonReg;
    private UserInterfaceImpl regImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regImpl = new UserInterfaceImpl(this);

        initView();

    }

    private void initView() {
        textInputLayoutUser = (TextInputLayout)findViewById(R.id.textInputLayout_user);
        editTextUser = (EditText)findViewById(R.id.editText_user);
        textInputLayoutpwd = (TextInputLayout)findViewById(R.id.textInputLayout_password);
        editTextpwd = (EditText)findViewById(R.id.editText_pwd);
        textInputLayoutpwdC = (TextInputLayout)findViewById(R.id.textInputLayout_password_confirm);
        editTextpwdC = (EditText)findViewById(R.id.editText_pwd_confirm);
        buttonReg = (ActionProcessButton)findViewById(R.id.button_reg);


        setTooblBar(R.id.common_toolbar, R.string.reg_normal,true,true);

        editTextUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()<6){
                    textInputLayoutUser.setErrorEnabled(true);
                    textInputLayoutUser.setError("用户名太短啦");
                }else if (charSequence.length()>20){
                    textInputLayoutUser.setErrorEnabled(true);
                    textInputLayoutUser.setError("用户名太长啦");
                }
                else {
                    textInputLayoutUser.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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


        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String userName = editTextUser.getText().toString();
               String pwd = editTextpwd.getText().toString();
               String pwdConfirm = editTextpwdC.getText().toString();

                if (userName.length()<6){
                    MToast.showShort(mContext, "用户名太短");
                    return;
                }
                if (pwd.length()<8){
                    MToast.showShort(mContext,"密码太短");
                    return;
                }
                if (!pwdConfirm.equals(pwd)){
                    MToast.showShort(mContext,"两次密码不一致");
                    return;
                }

                regImpl.operation(UserInterfaceType.Reg,userName, pwd);
                buttonReg.setProgress(50);
                buttonReg.setClickable(false);
            }
        });
    }

    @Override
    public void operationSuc() {
        buttonReg.setProgress(100);
        buttonReg.setClickable(true);
    }

    @Override
    public void operationFail(String errorMsg) {
        buttonReg.setProgress(-1);
        MToast.showShort(mContext,errorMsg);
        buttonReg.setClickable(true);
    }
}

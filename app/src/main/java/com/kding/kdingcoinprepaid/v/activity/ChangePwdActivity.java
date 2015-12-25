package com.kding.kdingcoinprepaid.v.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
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

public class ChangePwdActivity extends BaseCompatActivity implements IUserInterfaceCallBack {

    private EditText editTextOldPwd;
    private TextInputLayout textInputLayoutNewPwd;
    private EditText editTextNewPwd;
    private TextInputLayout textInputLayoutNewPwdC;
    private EditText editTextNewPwdC;
    private ActionProcessButton buttonSubmit;
    private UserInterfaceImpl userInterfaceImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);

        userInterfaceImpl = new UserInterfaceImpl(this);

        initView();

    }

    private void initView() {
        editTextOldPwd = (EditText)findViewById(R.id.editText_old_pwd);
        textInputLayoutNewPwd = (TextInputLayout)findViewById(R.id.textInputLayout_new_password);
        editTextNewPwd = (EditText)findViewById(R.id.editText_new_pwd);
        textInputLayoutNewPwdC = (TextInputLayout)findViewById(R.id.textInputLayout_new_password_confirm);
        editTextNewPwdC = (EditText)findViewById(R.id.editText_new_pwd_confirm);
        buttonSubmit = (ActionProcessButton)findViewById(R.id.button_change_pwd);


        setTooblBar(R.id.common_toolbar, R.string.change_pwd_title,true,true);

        editTextNewPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 8) {
                    textInputLayoutNewPwd.setErrorEnabled(true);
                    textInputLayoutNewPwd.setError("密码太短啦");
                } else {
                    textInputLayoutNewPwd.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editTextNewPwdC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals(editTextNewPwd.getText().toString())) {
                    textInputLayoutNewPwdC.setErrorEnabled(true);
                    textInputLayoutNewPwdC.setError("两次密码不一致");
                } else {
                    textInputLayoutNewPwdC.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editTextOldPwd.getText().toString();
                String pwd = editTextNewPwd.getText().toString();
                String pwdConfirm = editTextNewPwdC.getText().toString();

                if (pwd.length() < 8) {
                    MToast.showShort(mContext, "密码太短");
                    return;
                }
                if (!pwdConfirm.equals(pwd)) {
                    MToast.showShort(mContext, "两次密码不一致");
                    return;
                }

                userInterfaceImpl.operation(UserInterfaceType.ChangePwd,userName, pwd);
                buttonSubmit.setProgress(50);
                buttonSubmit.setClickable(false);
            }
        });
    }

    @Override
    public void operationSuc() {
        buttonSubmit.setProgress(100);
        buttonSubmit.setClickable(true);
    }

    @Override
    public void operationFail(String errorMsg) {
        buttonSubmit.setProgress(-1);
        MToast.showShort(mContext,errorMsg);
        buttonSubmit.setClickable(true);
    }
}

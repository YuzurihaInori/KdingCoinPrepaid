package com.kding.kdingcoinprepaid.v.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.consts.UserInterfaceType;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.p.impl.UserInterfaceImpl;
import com.kding.kdingcoinprepaid.utils.MToast;

public class LoginActivity extends BaseCompatActivity implements IUserInterfaceCallBack {

    private EditText editTextUser;
    private EditText editTextpwd;
    private ActionProcessButton buttonLogin;
    private UserInterfaceImpl loginImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginImpl = new UserInterfaceImpl(this);

        initView();

    }

    private void initView() {
        editTextUser = (EditText)findViewById(R.id.editText_user);
        editTextpwd = (EditText)findViewById(R.id.editText_pwd);
        buttonLogin = (ActionProcessButton)findViewById(R.id.button_login);
        TextView buttonForgetPwd = (TextView)findViewById(R.id.button_forget_pwd);

        setTooblBar(R.id.common_toolbar, R.string.login_normal,true,true);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editTextUser.getText().toString();
                String pwd = editTextpwd.getText().toString();

                loginImpl.operation(UserInterfaceType.Login, userName, pwd);
                buttonLogin.setProgress(50);
                buttonLogin.setClickable(false);
            }
        });

        buttonForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,RetrievePwdActivity.class);
                String userName = editTextUser.getText().toString();
                intent.putExtra(ConstantTag.USERNAME_KEY,userName);
                startActivity(intent);
            }
        });
    }

    @Override
    public void operationSuc() {
        buttonLogin.setProgress(100);
        buttonLogin.setClickable(true);

        startActivity(new Intent(mContext,SelectOrgActivity.class));
    }

    @Override
    public void operationFail(String errorMsg) {
        buttonLogin.setProgress(-1);
        MToast.showShort(mContext,errorMsg);
        buttonLogin.setClickable(true);
    }
}

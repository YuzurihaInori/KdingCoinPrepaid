package com.kding.kdingcoinprepaid.v.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.LoginInfoBean;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.consts.KeyConst;
import com.kding.kdingcoinprepaid.consts.UserInterfaceType;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.p.impl.UserInfoImpl;
import com.kding.kdingcoinprepaid.p.impl.UserInterfaceImpl;
import com.kding.kdingcoinprepaid.utils.LoginUtil;
import com.kding.kdingcoinprepaid.utils.MToast;
import com.socks.library.KLog;

public class LoginActivity extends BaseCompatActivity implements IUserInterfaceCallBack {

    private EditText editTextUser;
    private EditText editTextpwd;
    private ActionProcessButton buttonLogin;
    private UserInterfaceImpl loginImpl;
    private UserInfoImpl userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginImpl = new UserInterfaceImpl(this, this);
        userInfo = UserInfoImpl.getInstance();
        initView();

        final String userName = getIntent().getStringExtra(KeyConst.REG_USERNAME_KEY);
        final String pwd = getIntent().getStringExtra(KeyConst.REG_PWD_KEY);

        if (userName != null && pwd != null) {

            editTextUser.setText(userName);
            editTextpwd.setText(pwd);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    autoLogin(userName, pwd);
                }
            }, 1000);
            return;
        }

        LoginInfoBean loginInfo = LoginUtil.getLoginInfo(this);
        if (loginInfo != null) {

            final String userName2 = loginInfo.userName;
            final String pwd2 = loginInfo.passWord;

            if (userName2 != null && pwd2 != null) {

                editTextUser.setText(userName2);
                editTextpwd.setText(pwd2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        autoLogin(userName2, pwd2);
                    }
                }, 1000);
            }
        }

    }

    private void initView() {
        editTextUser = (EditText) findViewById(R.id.editText_user);
        editTextpwd = (EditText) findViewById(R.id.editText_pwd);
        buttonLogin = (ActionProcessButton) findViewById(R.id.button_login);
        TextView buttonForgetPwd = (TextView) findViewById(R.id.button_forget_pwd);

        setTooblBar(R.id.common_toolbar, R.string.login_normal, true, true);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editTextUser.getText().toString();
                String pwd = editTextpwd.getText().toString();

                autoLogin(userName, pwd);
            }
        });

        buttonForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RetrievePwdActivity.class);
                String userName = editTextUser.getText().toString();
                intent.putExtra(ConstantTag.USERNAME_KEY, userName);
                startActivity(intent);
            }
        });
    }


    public void autoLogin(@NonNull String userName, @NonNull String pwd) {
        loginImpl.operation(UserInterfaceType.Login, userName, pwd);
        buttonLogin.setProgress(50);
        buttonLogin.setClickable(false);
    }

    @Override
    public void operationSuc() {

        setResult(RESULT_OK);

        buttonLogin.setProgress(100);
        buttonLogin.setClickable(true);

        startActivity(new Intent(mContext, SelectOrgActivity.class));

        finish();
    }

    @Override
    public void operationFail(String errorMsg) {
        buttonLogin.setProgress(-1);
        MToast.showShort(mContext, errorMsg);
        buttonLogin.setClickable(true);
    }
}

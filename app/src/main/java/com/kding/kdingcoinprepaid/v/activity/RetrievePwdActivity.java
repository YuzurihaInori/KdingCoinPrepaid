package com.kding.kdingcoinprepaid.v.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.consts.UserInterfaceType;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.p.impl.UserInterfaceImpl;
import com.kding.kdingcoinprepaid.p.impl.UserInterfaceImpl4Code;
import com.kding.kdingcoinprepaid.utils.MToast;
import com.socks.library.KLog;

public class RetrievePwdActivity extends BaseCompatActivity implements IUserInterfaceCallBack {

    private EditText editTextUser;
    private EditText editTextCode;
    private ActionProcessButton buttonRetrieve;
    private UserInterfaceImpl userInterfaceImpl;
    private String userName="";
    private String userNameFromLogin;
    private Button buttonSendCode;
    private UserInterfaceImpl4Code userInterfaceImpl4Code;

    private Handler handler = new Handler();
    private int count = 60;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (count == 0){
                count =60;
                buttonSendCode.setText(R.string.retrieve_pwd_send_code);
                buttonSendCode.setClickable(true);
                handler.removeCallbacks(runnable);
            }else {
                handler.postDelayed(runnable,1000);
                buttonSendCode.setText("再次发送（"+count+"）");
                count--;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_pwd);

        userInterfaceImpl = new UserInterfaceImpl(this,this);


        if (getIntent()!=null){
            userNameFromLogin = getIntent().getStringExtra(ConstantTag.USERNAME_KEY);
        }

        initView();
    }

    private void initView() {
        editTextUser = (EditText)findViewById(R.id.editText_user);
        editTextCode = (EditText)findViewById(R.id.editText_code);
        buttonSendCode = (Button)findViewById(R.id.button_send_code);
        buttonRetrieve = (ActionProcessButton)findViewById(R.id.button_retrieve);
        setTooblBar(R.id.common_toolbar, R.string.retrieve_pwd_title,true,true);

        if (userNameFromLogin !=null){
            editTextUser.setText(userNameFromLogin);
        }

        buttonSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = editTextUser.getText().toString();

                if (userName.equals("")){
                    MToast.showShort(mContext,"账号不可为空");
                    return;
                }
                if (userInterfaceImpl4Code == null){
                    userInterfaceImpl4Code = new UserInterfaceImpl4Code(new IUserInterfaceCallBack() {
                        @Override
                        public void operationSuc() {
                            KLog.i("请求验证码成功");
                        }

                        @Override
                        public void operationFail(String errorMsg) {
                            KLog.i("请求验证码失败");
                            MToast.showShort(mContext,errorMsg);

                            buttonSendCode.setText(R.string.retrieve_pwd_send_code);
                            buttonSendCode.setClickable(true);
                        }
                    });
                }else {
                    userInterfaceImpl4Code.operation(UserInterfaceType.Retrieve4Code, userName, "");
                }

                buttonSendCode.setClickable(false);
                handler.postDelayed(runnable,1000);
            }
        });

        buttonRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editTextCode.getText().toString();

                if (code.equals("")){
                    MToast.showShort(mContext,"验证码不可为空");
                    return;
                }

                userInterfaceImpl.operation(UserInterfaceType.Retrieve,userName,code);
                buttonRetrieve.setProgress(50);
                buttonRetrieve.setClickable(false);
            }
        });
    }

    @Override
    public void operationSuc() {
        buttonRetrieve.setProgress(100);
        buttonRetrieve.setClickable(true);

        Intent intent = new Intent(mContext,ResetPwdActivity.class);
        intent.putExtra(ConstantTag.USERNAME_KEY,userName);
        startActivity(intent);

        finish();
    }

    @Override
    public void operationFail(String errorMsg) {
        buttonRetrieve.setProgress(-1);
        MToast.showShort(mContext,errorMsg);
        buttonRetrieve.setClickable(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}

package com.kding.kdingcoinprepaid.v.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.p.callback.IBindInitCallBack;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.p.impl.BindPhoneImpl;
import com.kding.kdingcoinprepaid.utils.MToast;

public class BindPhoneActivity extends BaseCompatActivity implements IBindInitCallBack,IUserInterfaceCallBack{

    private BindPhoneImpl bindPhoneImpl;
    private EditText editTextPhone;
    private TextView textviewPhone;
    private EditText editTextCode;
    private Button sendCode;
    private ActionProcessButton submitBtn;

    private Handler handler = new Handler();
    private int count = 60;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (count == 0){
                count =60;
                sendCode.setText(R.string.retrieve_pwd_send_code);
                sendCode.setClickable(true);
                handler.removeCallbacks(runnable);
            }else {
                handler.postDelayed(runnable,1000);
                sendCode.setText("再次发送（"+count+"）");
                count--;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);

        initView();

        bindPhoneImpl = new BindPhoneImpl(this,this,this);
        bindPhoneImpl.initView();
        

    }

    private void initView() {

        setTooblBar(R.id.common_toolbar,R.string.bind_phone_title,true,true);

        editTextPhone= (EditText)findViewById(R.id.editText_phone);
        textviewPhone= (TextView)findViewById(R.id.textview_phone_num);
        editTextCode= (EditText)findViewById(R.id.editText_code);
        sendCode= (Button)findViewById(R.id.button_send_code);
        submitBtn= (ActionProcessButton)findViewById(R.id.textInputLayout_password_code);
    }

    @Override
    public void initView4Bind() {

        editTextPhone.setVisibility(View.VISIBLE);
        textviewPhone.setVisibility(View.GONE);

        bindWidget();
    }

    @Override
    public void initView4Unbind(int bindNum) {
        editTextPhone.setVisibility(View.GONE);
        textviewPhone.setVisibility(View.VISIBLE);
        textviewPhone.setText(String.format(getResources().getString(R.string.bind_phone_num_tip), bindNum));

        submitBtn.setText(getResources().getString(R.string.bind_phone_unbind_submit));
        submitBtn.setLoadingText(getResources().getString(R.string.bind_phone_unbind_submit_loading));
        submitBtn.setErrorText(getResources().getString(R.string.bind_phone_unbind_submit_fail));
        submitBtn.setCompleteText(getResources().getString(R.string.bind_phone_unbind_submit_suc));

        bindWidget(bindNum);
    }

    private void bindWidget() {

        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPhone.getText().toString().equals("")) {
                    MToast.showShort(mContext, "手机号不能为空");
                } else if (editTextPhone.getText().toString().length() != 11) {
                    MToast.showShort(mContext, "请输入正确的手机号");
                } else {
                    bindPhoneImpl.sendCode(Integer.parseInt(editTextPhone.getText().toString()));
                    handler.postDelayed(runnable, 1000);
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextCode.getText().toString().equals("")) {
                    MToast.showShort(mContext, "验证码不能为空");
                } else {
                    bindPhoneImpl.bindPhone(editTextCode.getText().toString());
                    submitBtn.setProgress(50);
                    submitBtn.setClickable(false);
                }
            }
        });

    }

    private void bindWidget(final int bindNum) {

        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bindNum==0){
                    MToast.showShort(mContext,"未知错误");
                }else {
                    bindPhoneImpl.sendCode(bindNum);
                    handler.postDelayed(runnable, 1000);
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextCode.getText().toString().equals("")){
                    MToast.showShort(mContext,"验证码不能为空");
                }else {
                    bindPhoneImpl.bindPhone(editTextCode.getText().toString());
                    submitBtn.setProgress(50);
                    submitBtn.setClickable(false);
                }
            }
        });
    }


    @Override
    public void operationSuc() {
        submitBtn.setProgress(100);
        submitBtn.setClickable(true);
    }

    @Override
    public void operationFail(String errorMsg) {
        submitBtn.setProgress(-1);
        submitBtn.setClickable(true);
        MToast.showShort(mContext, errorMsg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}

package com.kding.kdingcoinprepaid.p.impl;

import android.os.Handler;

import com.kding.kdingcoinprepaid.bean.UserInfoBean;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.consts.UserInterfaceType;
import com.kding.kdingcoinprepaid.bean.UserInterfaceBean;
import com.kding.kdingcoinprepaid.p.IUserInterface;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.socks.library.KLog;

/**
 * Created by Toast-pc on 2015/12/17.
 */
public class UserInterfaceImpl implements IUserInterface {

    private final IUserInterfaceCallBack iCallBack;

    public UserInterfaceImpl(IUserInterfaceCallBack iCallBack) {
        this.iCallBack = iCallBack;
    }

    @Override
    public void operation(UserInterfaceType type,String userName, String passWord) {
        if (type == UserInterfaceType.Reg){

            reg(userName,passWord);
        }else if (type == UserInterfaceType.Login){
            login(userName, passWord);
        }else if (type == UserInterfaceType.ChangePwd){
            changePwd(userName, passWord);
        }else if (type == UserInterfaceType.Retrieve){
            retrieve(userName, passWord);
        }
        else if (type == UserInterfaceType.ResetPwd){
            resetPwd(userName, passWord);
        }else{
            KLog.i("error");
        }
    }
    private void reg(String userName, String passWord){
        UserInterfaceBean regResult = new UserInterfaceBean();
        regResult.code = 1;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iCallBack.operationSuc();
            }
        },4000);
    }

    private void login(String userName, String passWord){
        UserInterfaceBean regResult = new UserInterfaceBean();
        regResult.code = 1;


        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.userType = ConstantTag.USER_TYPE_PRESIDENT;

        UserInfoImpl.getInstance().setUserInfo(userInfoBean);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iCallBack.operationSuc();
            }
        },4000);
    }

    private void changePwd(String userName, String passWord){
        UserInterfaceBean regResult = new UserInterfaceBean();
        regResult.code = 1;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iCallBack.operationSuc();
            }
        },4000);
    }

    private void retrieve(String userName, String code) {
        UserInterfaceBean regResult = new UserInterfaceBean();
        regResult.code = 1;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iCallBack.operationSuc();
            }
        },4000);
    }

    private void resetPwd(String userName, String passWord){
        UserInterfaceBean regResult = new UserInterfaceBean();
        regResult.code = 1;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iCallBack.operationSuc();
            }
        },4000);
    }


}

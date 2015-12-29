package com.kding.kdingcoinprepaid.p.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.kding.kdingcoinprepaid.bean.LoginInfoBean;
import com.kding.kdingcoinprepaid.bean.UserInfoBean;
import com.kding.kdingcoinprepaid.bean.result.LoginBean;
import com.kding.kdingcoinprepaid.consts.UserInterfaceType;
import com.kding.kdingcoinprepaid.consts.UserType;
import com.kding.kdingcoinprepaid.p.ILoading;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.utils.LoginUtil;
import com.kding.kdingcoinprepaid.utils.MToast;
import com.kding.kdingcoinprepaid.v.activity.GamesActivity;
import com.kding.kdingcoinprepaid.v.activity.LoginActivity;
import com.kding.kdingcoinprepaid.v.activity.MainActivity;
import com.kding.kdingcoinprepaid.v.activity.SelectOrgActivity;
import com.kding.kdingcoinprepaid.v.activity.UnionActivity;

/**
 * Created by Toast-pc on 2015/12/29.
 */
public class LoadingImpl implements ILoading,IUserInterfaceCallBack{

    private final Activity context;
    private final UserInterfaceImpl interfaceImpl;

    public LoadingImpl(Activity context) {
        this.context = context;

        interfaceImpl = new UserInterfaceImpl(context,this);
    }

    @Override
    public void startApp() {

        LoginInfoBean loginInfo = LoginUtil.getLoginInfo(context);
        //没有账号
        if (loginInfo == null){
            context.startActivity(new Intent(context, MainActivity.class));
        }else {
            //有账号，直接登录
            startLogin(loginInfo.userName,loginInfo.passWord);
        }
    }

    private void startLogin(String userName,String password) {
        interfaceImpl.operation(UserInterfaceType.Login,userName,password);
    }

    @Override
    public void operationSuc() {    //登录成功 跳转下一步

        UserInfoBean userInfo = UserInfoImpl.getInstance().getUserInfo();

        switch (userInfo.getUsertype()){
            case UserType.UNBIND_MEMBER:

                if (userInfo.isjoindata()){
                    context.startActivity(new Intent(context, GamesActivity.class));
                }else {
                    context.startActivity(new Intent(context, SelectOrgActivity.class));
                }
                break;
            case UserType.BIND_MEMBER:
                context.startActivity(new Intent(context, UnionActivity.class));
                break;
            case UserType.UNBIND_PRESIDENT:
                context.startActivity(new Intent(context, SelectOrgActivity.class));
                break;
            case UserType.BIND_PRESIDENT:
                context.startActivity(new Intent(context, UnionActivity.class));
                break;
            default:
                break;
        }
        context.finish();
    }

    @Override
    public void operationFail(String errorMsg) {    //登录失败 跳转登录
        context.startActivity(new Intent(context, LoginActivity.class));
        MToast.showShort(context, errorMsg);
        context.finish();
    }
}

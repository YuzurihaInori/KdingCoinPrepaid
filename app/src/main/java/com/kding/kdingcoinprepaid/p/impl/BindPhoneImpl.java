package com.kding.kdingcoinprepaid.p.impl;

import android.content.Context;

import com.kding.kdingcoinprepaid.bean.UserInfoBean;
import com.kding.kdingcoinprepaid.p.IBindPhone;
import com.kding.kdingcoinprepaid.p.callback.IBindInitCallBack;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;

/**
 * Created by Toast-pc on 2015/12/28.
 */
public class BindPhoneImpl implements IBindPhone{

    private final IBindInitCallBack callBack;
    private final UserInfoBean userInfo;
    private final IUserInterfaceCallBack userCallBack;

    public BindPhoneImpl(Context context,IBindInitCallBack callBack,IUserInterfaceCallBack userCallBack) {
        this.callBack = callBack;
        this.userCallBack= userCallBack;
        userInfo= UserInfoImpl.getInstance().getUserInfo();
    }

    @Override
    public void initView() {

        if (userInfo.iscellphone()){
            callBack.initView4Bind();
        }else {
            callBack.initView4Unbind(userInfo.getCellphone());
        }
    }

    @Override
    public void sendCode(int phoneNum) {

    }

    @Override
    public void bindPhone(String code) {
        userCallBack.operationSuc();
    }
}

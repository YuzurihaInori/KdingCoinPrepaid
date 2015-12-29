package com.kding.kdingcoinprepaid.p.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import com.kding.kdingcoinprepaid.bean.UserInfoBean;
import com.kding.kdingcoinprepaid.consts.KeyConst;
import com.kding.kdingcoinprepaid.p.IUserInfo;
import com.kding.kdingcoinprepaid.utils.PreferenceUtils;
import com.socks.library.KLog;

/**
 * Created by Toast-pc on 2015/12/18.
 */
public class UserInfoImpl implements IUserInfo {

    private static UserInfoImpl instance;
    private UserInfoBean userInfoBean;

    private UserInfoImpl() {}

    public static synchronized UserInfoImpl getInstance() {
        if (instance == null) {
            instance = new UserInfoImpl();
        }
        return instance;
    }

    @Override
    public UserInfoBean getUserInfo(){
        return this.userInfoBean;
    }

    @Override
    public void setUserInfo(@NonNull UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }
}

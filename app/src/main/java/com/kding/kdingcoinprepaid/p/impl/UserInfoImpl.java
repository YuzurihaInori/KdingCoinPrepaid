package com.kding.kdingcoinprepaid.p.impl;

import com.kding.kdingcoinprepaid.bean.UserInfoBean;
import com.kding.kdingcoinprepaid.p.IUserInfo;

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
    public UserInfoBean getUserInfo() {
        return this.userInfoBean;
    }

    @Override
    public void setUserInfo(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }
}

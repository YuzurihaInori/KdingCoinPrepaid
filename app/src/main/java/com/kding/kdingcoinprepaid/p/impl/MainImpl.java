package com.kding.kdingcoinprepaid.p.impl;

import android.content.Context;

import com.kding.kdingcoinprepaid.bean.UserInfoBean;
import com.kding.kdingcoinprepaid.consts.KeyConst;
import com.kding.kdingcoinprepaid.p.IMain;
import com.kding.kdingcoinprepaid.utils.PreferenceUtils;

/**
 * Created by Toast-pc on 2015/12/29.
 */
public class MainImpl implements IMain{

    private final Context context;
    private final UserInfoImpl userInfoImpl;

    public MainImpl(Context context) {
        this.context = context;

        userInfoImpl = UserInfoImpl.getInstance();
    }

    @Override
    public boolean init() {

       return userInfoImpl.getUserInfo()!=null;

    }
}

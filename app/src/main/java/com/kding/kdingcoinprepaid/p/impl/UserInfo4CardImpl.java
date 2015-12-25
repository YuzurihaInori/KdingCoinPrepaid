package com.kding.kdingcoinprepaid.p.impl;

import com.kding.kdingcoinprepaid.p.IUserInfo4Card;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;

/**
 * Created by Toast-pc on 2015/12/25.
 */
public class UserInfo4CardImpl implements IUserInfo4Card{

    private final IUserInterfaceCallBack callBack;

    public UserInfo4CardImpl(IUserInterfaceCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void postToServer(String... params) {

        callBack.operationSuc();
    }
}

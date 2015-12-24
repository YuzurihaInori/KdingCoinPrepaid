package com.kding.kdingcoinprepaid.p.impl;

import android.os.Handler;

import com.kding.kdingcoinprepaid.bean.UserInterfaceBean;
import com.kding.kdingcoinprepaid.p.ICarryOver;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;

/**
 * Created by Toast-pc on 2015/12/23.
 */
public class CarryOverImpl implements ICarryOver{

    private final IUserInterfaceCallBack callback;

    public CarryOverImpl(IUserInterfaceCallBack callBack) {
        this.callback = callBack;
    }

    @Override
    public void post(String toAccout, String coinNum) {
        UserInterfaceBean regResult = new UserInterfaceBean();
        regResult.code = 1;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.operationSuc();
            }
        },4000);
    }
}

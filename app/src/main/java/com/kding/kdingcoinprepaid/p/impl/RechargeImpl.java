package com.kding.kdingcoinprepaid.p.impl;

import android.os.Handler;

import com.kding.kdingcoinprepaid.bean.UserInterfaceBean;
import com.kding.kdingcoinprepaid.p.IRecharge;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;

/**
 * Created by Toast-pc on 2015/12/23.
 */
public class RechargeImpl implements IRecharge{


    private final IUserInterfaceCallBack callback;

    public RechargeImpl(IUserInterfaceCallBack callback) {
        this.callback = callback;
    }

    @Override
    public void post(String rmb) {
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

    @Override
    public int getCoin(String rmb, String discount) {


        Integer rmbInt = Integer.valueOf(rmb);
        Integer discountInt = Integer.valueOf(discount.replace("%", ""));
        return rmbInt * 10 * discountInt / 100;
    }
}

package com.kding.kdingcoinprepaid.p.impl;

import android.os.Handler;

import com.kding.kdingcoinprepaid.bean.UserInterfaceBean;
import com.kding.kdingcoinprepaid.consts.UserInterfaceType;
import com.kding.kdingcoinprepaid.p.IUserInterface;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;

/**
 * Created by Toast-pc on 2015/12/18.
 */
public class UserInterfaceImpl4Code implements IUserInterface{


    private final IUserInterfaceCallBack iCallBack;

    public UserInterfaceImpl4Code(IUserInterfaceCallBack iCallBack) {
        this.iCallBack = iCallBack;
    }

    @Override
    public void operation(UserInterfaceType type, String userName, String passWord) {
        if (type == UserInterfaceType.Retrieve4Code){
            retrieve4Code(userName, passWord);
        }
    }

    private void retrieve4Code(String userName, String passWord) {
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

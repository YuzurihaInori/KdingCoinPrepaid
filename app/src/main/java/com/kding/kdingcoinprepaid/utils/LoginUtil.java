package com.kding.kdingcoinprepaid.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.kding.kdingcoinprepaid.bean.LoginInfoBean;
import com.kding.kdingcoinprepaid.consts.KeyConst;

/**
 * Created by Toast-pc on 2015/12/29.
 */
public class LoginUtil {

    public static LoginInfoBean getLoginInfo(Context context){

        LoginInfoBean loginBean = new LoginInfoBean();
        loginBean.userName = PreferenceUtils.getPrefString(context, KeyConst.SAVE_USERNAME_KEY, null);
        loginBean.passWord = PreferenceUtils.getPrefString(context, KeyConst.SAVE_PWD_KEY, null);

        if (loginBean.userName == null || loginBean.passWord == null){
            return null;
        }else {
            return loginBean;
        }
    }

    public static void saveLoginInfo(Context context,@NonNull String userName,@NonNull String passWord){
        PreferenceUtils.setPrefString(context, KeyConst.SAVE_USERNAME_KEY, userName);
        PreferenceUtils.setPrefString(context, KeyConst.SAVE_PWD_KEY, passWord);
    }
}

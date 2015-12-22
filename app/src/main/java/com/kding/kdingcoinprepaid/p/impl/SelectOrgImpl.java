package com.kding.kdingcoinprepaid.p.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.SelectBean;
import com.kding.kdingcoinprepaid.bean.UserInfoBean;
import com.kding.kdingcoinprepaid.bean.UserInterfaceBean;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.p.ISelectOrg;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.v.activity.SelectOrgListActivity;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toast-pc on 2015/12/18.
 */
public class SelectOrgImpl implements ISelectOrg{

    private final IUserInterfaceCallBack callBack;
    private final UserInfoBean userInfoBean;
    private final Context context;
    private ArrayList<SelectBean> resultList;

    public SelectOrgImpl(Context context,IUserInterfaceCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        userInfoBean =UserInfoImpl.getInstance().getUserInfo();;
    }

    @Override
    public String getGameEditTextText() {

        String gameText = "";

        switch (userInfoBean.userType){
            case ConstantTag.USER_TYPE_PRESIDENT :
                gameText =context.getString(R.string.select_org_game_hint);
                break;
            case ConstantTag.USER_TYPE_MEMBER :
                gameText =context.getString(R.string.select_org_game4member_hint);
                break;
            default:
                gameText ="未知错误";
                break;

        }
        return gameText;
    }

    @Override
    public Intent getGameEditTextIntent() {
        Intent gameIntent = new Intent(context, SelectOrgListActivity.class);
        gameIntent.putExtra(ConstantTag.SELECT_ORG_KEY,ConstantTag.GAME_LIST_KEY);
        return gameIntent;
    }

    @Override
    public String getUnionEditTextText() {
        String unionText = "";

        switch (userInfoBean.userType){
            case ConstantTag.USER_TYPE_PRESIDENT :
                unionText =context.getString(R.string.select_org_org_hint);
                break;
            case ConstantTag.USER_TYPE_MEMBER :
                unionText =context.getString(R.string.select_org_org4member_hint);
                break;
            default:
                unionText ="未知错误";
                break;

        }
        return unionText;
    }

    @Override
    public Intent getUnionEditTextIntent() {
        Intent unionIntent = new Intent(context, SelectOrgListActivity.class);
        unionIntent.putExtra(ConstantTag.SELECT_ORG_KEY,ConstantTag.UNION_LIST_KEY);
        return unionIntent;
    }

    @Override
    public boolean getUnionEditTextClickable() {
        boolean clickable = false;
        switch (userInfoBean.userType){
            case ConstantTag.USER_TYPE_PRESIDENT :
                clickable = true;
                break;
            case ConstantTag.USER_TYPE_MEMBER :
                clickable = false;
                break;
            default:
                clickable = false;
                break;
        }
        return clickable;
    }

    @Override
    public void postSelectInfo() {

        // TODO: 2015/12/21
        UserInterfaceBean regResult = new UserInterfaceBean();
        regResult.code = 1;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callBack.operationSuc();
            }
        },4000);
    }

    public void setPostData(int requestCode, int resultCode, Intent data) {

        if (requestCode == ConstantTag.SELECT_REQUESTCODE && resultCode == ConstantTag.SELECT_RESULTCODE){
            if (data != null){
               resultList = data.getParcelableArrayListExtra(ConstantTag.SELECT_RESULT_KEY);

                if (resultList !=null){
                    KLog.e(resultList.toString());
                }

            }
        }

    }
}

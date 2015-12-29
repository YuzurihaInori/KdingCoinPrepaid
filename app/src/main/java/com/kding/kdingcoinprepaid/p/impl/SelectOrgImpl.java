package com.kding.kdingcoinprepaid.p.impl;

import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kding.kdingcoinprepaid.R;
import com.kding.kdingcoinprepaid.bean.SelectBean;
import com.kding.kdingcoinprepaid.bean.UserInfoBean;
import com.kding.kdingcoinprepaid.bean.result.SelectOrgBean;
import com.kding.kdingcoinprepaid.consts.ConstantTag;
import com.kding.kdingcoinprepaid.consts.HttpUrl;
import com.kding.kdingcoinprepaid.consts.UserType;
import com.kding.kdingcoinprepaid.p.ISelectOrg;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.v.activity.SelectOrgListActivity;
import com.socks.library.KLog;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Toast-pc on 2015/12/18.
 */
public class SelectOrgImpl implements ISelectOrg {

    private final IUserInterfaceCallBack callBack;
    private final UserInfoBean userInfoBean;
    private final Context context;
    private ArrayList<SelectBean> resultList;
    private SelectBean unionName;

    public SelectOrgImpl(Context context, IUserInterfaceCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        userInfoBean = UserInfoImpl.getInstance().getUserInfo();
        ;
    }

    @Override
    public String getGameEditTextText() {

        String gameText = "";

        switch (userInfoBean.getUsertype()) {
            case UserType.UNBIND_PRESIDENT:
                gameText = context.getString(R.string.select_org_game_hint);
                break;
            case UserType.UNBIND_MEMBER:
                gameText = context.getString(R.string.select_org_game4member_hint);
                break;
            default:
                gameText = "未知错误";
                break;

        }
        return gameText;
    }

    @Override
    public Intent getGameEditTextIntent() {
        Intent gameIntent = new Intent(context, SelectOrgListActivity.class);
        gameIntent.putExtra(ConstantTag.SELECT_ORG_KEY, ConstantTag.GAME_LIST_KEY);
        gameIntent.putExtra(ConstantTag.SELECT_GAME_ID_KEY, "");
        return gameIntent;
    }

    @Override
    public String getUnionEditTextText() {
        String unionText = "";

        switch (userInfoBean.getUsertype()) {
            case UserType.UNBIND_PRESIDENT:
                unionText = context.getString(R.string.select_org_org_hint);
                break;
            case UserType.UNBIND_MEMBER:
                unionText = context.getString(R.string.select_org_org4member_hint);
                break;
            default:
                unionText = "未知错误";
                break;

        }
        return unionText;
    }

    @Override
    public Intent getUnionEditTextIntent() {
        Intent unionIntent = new Intent(context, SelectOrgListActivity.class);
        unionIntent.putExtra(ConstantTag.SELECT_ORG_KEY, ConstantTag.UNION_LIST_KEY);
        unionIntent.putExtra(ConstantTag.SELECT_GAME_ID_KEY, resultList.get(0).id);
        return unionIntent;
    }

    @Override
    public boolean getUnionEditTextClickable() {
        boolean clickable = false;
        switch (userInfoBean.getUsertype()) {
            case UserType.UNBIND_PRESIDENT:
                clickable = true;
                break;
            case UserType.UNBIND_MEMBER:
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

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        StringRequest jsonObjectRequest = new StringRequest(
                Request.Method.POST,
                HttpUrl.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        KLog.e(response);

                        SelectOrgBean selectOrgBean = JSON.parseObject(response, SelectOrgBean.class);

                        if (selectOrgBean.getError() == 1) {
                            callBack.operationSuc();
                        } else {
                            callBack.operationFail(selectOrgBean.getMsg());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.operationFail("连接服务器出错");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                JSONArray appids = new JSONArray();
                for (SelectBean bean : resultList){
                    appids.put(bean.name);
                }
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("uid", String.valueOf(UserInfoImpl.getInstance().getUserInfo().getUid()));
                params.put("guildname", unionName.name);
                params.put("guildid", unionName.id);
                params.put("appids", appids.toString());

                KLog.d(params);
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void setPostData(int requestCode, int resultCode, Intent data) {

        if (resultCode == ConstantTag.SELECT_RESULTCODE) {
            if (data != null) {
                ArrayList<SelectBean> mData = data.getParcelableArrayListExtra(ConstantTag.SELECT_RESULT_KEY);
                if (mData != null) {
                    if (requestCode == ConstantTag.SELECT_GAME_REQUESTCODE) {
                        resultList = mData;
                        KLog.e(resultList.toString());
                    } else {
                        unionName = mData.get(0);
                    }

                }
            }
        }

    }
}

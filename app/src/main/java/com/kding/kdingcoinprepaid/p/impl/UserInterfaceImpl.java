package com.kding.kdingcoinprepaid.p.impl;

import android.content.Context;
import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kding.kdingcoinprepaid.bean.result.LoginBean;
import com.kding.kdingcoinprepaid.bean.result.RegBean;
import com.kding.kdingcoinprepaid.consts.HttpUrl;
import com.kding.kdingcoinprepaid.consts.UserInterfaceType;
import com.kding.kdingcoinprepaid.bean.UserInterfaceBean;
import com.kding.kdingcoinprepaid.p.IUserInterface;
import com.kding.kdingcoinprepaid.p.callback.IUserInterfaceCallBack;
import com.kding.kdingcoinprepaid.utils.LoginUtil;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Toast-pc on 2015/12/17.
 */
public class UserInterfaceImpl implements IUserInterface {

    private final IUserInterfaceCallBack iCallBack;
    private final Context context;

    public UserInterfaceImpl(Context context, IUserInterfaceCallBack iCallBack) {
        this.context = context;
        this.iCallBack = iCallBack;
    }

    @Override
    public void operation(UserInterfaceType type, String userName, String passWord) {
        if (type == UserInterfaceType.Reg) {
            reg(userName, passWord);
        } else if (type == UserInterfaceType.Login) {
            login(userName, passWord);
        } else if (type == UserInterfaceType.ChangePwd) {
            changePwd(userName, passWord);
        } else if (type == UserInterfaceType.Retrieve) {
            retrieve(userName, passWord);
        } else if (type == UserInterfaceType.ResetPwd) {
            resetPwd(userName, passWord);
        } else {
            KLog.i("error");
        }
    }

    private void reg(final String userName, final String passWord) {

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        StringRequest jsonObjectRequest = new StringRequest(
                Request.Method.POST,
                HttpUrl.URL_REG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        RegBean regBean = (RegBean) JSON.parseObject(response, RegBean.class);

                        KLog.e(regBean.toString());
                        if (regBean.getError() == 1) {
                            iCallBack.operationSuc();
                        } else {
                            iCallBack.operationFail(regBean.getMsg());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iCallBack.operationFail("连接服务器出错");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("username", userName);
                params.put("password", passWord);
                params.put("device", android.os.Build.BRAND);
                params.put("dtype", android.os.Build.MODEL);
                params.put("os", android.os.Build.VERSION.RELEASE);
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    private void login(final String userName, final String passWord) {

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        StringRequest jsonObjectRequest = new StringRequest(
                Request.Method.POST,
                HttpUrl.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        KLog.e(response);

                        LoginBean loginBean = JSON.parseObject(response, LoginBean.class);

                        if (loginBean.getError() == 1) {
                            KLog.d(loginBean.getData().toString());
                            LoginUtil.saveLoginInfo(context, userName, passWord);
                            UserInfoImpl.getInstance().setUserInfo(loginBean.getData());
                            iCallBack.operationSuc();
                        } else {
                            iCallBack.operationFail(loginBean.getMsg());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        iCallBack.operationFail("连接服务器出错");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("username", userName);
                params.put("password", passWord);
                return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    private void changePwd(String userName, String passWord) {
        UserInterfaceBean regResult = new UserInterfaceBean();
        regResult.code = 1;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iCallBack.operationSuc();
            }
        }, 4000);
    }

    private void retrieve(String userName, String code) {
        UserInterfaceBean regResult = new UserInterfaceBean();
        regResult.code = 1;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iCallBack.operationSuc();
            }
        }, 4000);
    }

    private void resetPwd(String userName, String passWord) {
        UserInterfaceBean regResult = new UserInterfaceBean();
        regResult.code = 1;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iCallBack.operationSuc();
            }
        }, 4000);
    }


}
